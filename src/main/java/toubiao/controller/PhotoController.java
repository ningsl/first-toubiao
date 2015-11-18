package toubiao.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import toubiao.pageModel.Json;
import toubiao.pageModel.Photo;
import toubiao.pageModel.Project;
import toubiao.pageModel.ProjectPhoto;
import toubiao.service.impl.PhotoServiceImpl;
import toubiao.service.impl.ProjectServiceImpl;

@Controller
@RequestMapping("/photoController")
public class PhotoController {
	
	static Logger logger = LogManager.getLogger(PhotoController.class.getName());

	@Autowired
	private PhotoServiceImpl photoService;
	
	@Autowired
	private ProjectServiceImpl projectService;
	
	@RequestMapping("/projectPhotoEditPage")
	public String getPhotoById(HttpServletRequest request){
		System.out.println("in getPhotoById ");
		String photoId=request.getParameter("photoId");
		if(photoId==null){
			System.out.println("photoId is null");
			return "/404.jsp";
		}
		Long idLong=Long.parseLong(photoId);
		System.out.println(idLong);
		Photo photo=photoService.getPhotoById(idLong);
		request.setAttribute("photo", photo);
		return "/projectPhotoEdit";
	}
	
	@RequestMapping("/updateProjectPhoto")
	@ResponseBody
	public Json updatePhoto(Photo photo){
		System.out.println(photo.getTitle());
		System.out.println(photo.getId());
		System.out.println(photo.getDescription());
		photoService.updatePhoto(photo);
		Json json=new Json();
		json.setMsg("更新成功");
		json.setSuccess(true);
		
		return json;
	}
	
	@RequestMapping("/deleteProjectPhoto")
	@ResponseBody
	public Json deletePhoto(String id){
		System.out.println(id);
		Long idlLong=Long.parseLong(id);
		
		photoService.deletePhoto(idlLong);
		Json json=new Json();
		json.setMsg("删除成功");
		json.setSuccess(true);
		
		return json;
	}
	
	@RequestMapping("/uploadProjectPhoto")  
	@ResponseBody
    public Json upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
		logger.debug("in the beginning!!!");
        String id=request.getParameter("projectId");
        /*String id="1295";*/
		
        if(id==null){
        	throw new RuntimeException("projectId is null");
        }
        Long projectId=Long.parseLong(id);
        
        ProjectPhoto photo=null;
        
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String originalFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(originalFileName.trim() !=""){  
                        System.out.println(originalFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                       /* String path = "e:/" + fileName;  
                        File localFile = new File(path);  */
                        
                        photo=photoService.saveProjectPhotoFile(projectId, originalFileName);
                        
                        String absPath=request.getRealPath(photo.getWebPath());
                       // absPath=request.getSession().getServletContext().getRealPath(photo.getWebPath());
                        absPath="Y:/EclipseProject/RecentlyProject/toubiao/src/main/webapp/"+photo.getWebPath();
                        logger.debug(absPath);
                        file.transferTo(new File(absPath));  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
        Json json=new Json();
		json.setMsg("上传成功");
		json.setSuccess(true);
		json.setObj(photo);
		
		logger.debug("almost finished save file");
		
        return json;  
    }  
	
	@RequestMapping("/projectPhotoSavePage")
	public String projectPhotoSavePage(HttpServletRequest request,ProjectPhoto photo){
		logger.debug(photo.getWebPath());
		logger.debug(photo.getProjectId());
		request.setAttribute("photo", photo);
		
		return "projectPhotoSave";
	}
	
	@RequestMapping("/saveProjectPhoto")
	@ResponseBody
	public Json saveProjectPhoto(HttpServletRequest request,HttpServletResponse response,ProjectPhoto photo) throws ServletException, IOException{
		logger.debug(photo.getProjectId());
		
		//保存图片信息进database，上传时，图片文件已保存到相应文件夹内
		photoService.saveProjectPhoto(photo);
		
		//获得新的project photo list
		List<ProjectPhoto> projectPhotoList=photoService.getProjectPhotoList(photo.getProjectId());
		
		//如果保存成功，request存入 project对象，生成渲染好的jsp,以json形式返回
		RequestDispatcher rd=request.getRequestDispatcher("/projectPhotoPart.jsp");
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"));
        
        HttpServletResponse rep=new HttpServletResponseWrapper(response){
        	public PrintWriter getWriter() throws IOException {
                return pw;
            }
        };
		
		request.setAttribute("projectPhotoList", projectPhotoList);
		
		rd.include(request, rep);
		pw.flush();
		String htmlString=os.toString("UTF-8");
		/*logger.debug(htmlString);*/
		Json json=new Json();
		json.setMsg("保存成功");
		json.setSuccess(true);
		json.setObj(htmlString);
		
		pw.close();
		os.close();
		return json;
	}
	
	@RequestMapping("/uploadFile2")  
	@ResponseBody
    public Json upload3(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
		logger.debug("in the beginning!!!");
        
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String originalFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(originalFileName.trim() !=""){  
                        System.out.println(originalFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        String path = "e:/" + fileName;  
                        File localFile = new File(path);  
                        
                        file.transferTo(localFile);  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
        Json json=new Json();
		json.setMsg("上传成功");
		json.setSuccess(true);
		
		logger.debug("almost finished save file");
		
        return json;  
    }  
	
	
	
	@RequestMapping("/deleteProjectPhotoFile")
	@ResponseBody
	public Json deleteProjectPhotoFile(HttpServletRequest request,ProjectPhoto photo){
		logger.debug("in deleteProjectPhotoFile controller");
		logger.debug(photo.getWebPath());
		String absPath=request.getRealPath(photo.getWebPath());
		
		photoService.deleteProjectPhotoFile(absPath);
		
		Json json=new Json();
		json.setMsg("删除成功");
		json.setSuccess(true);
		return json;
		
	}
}
