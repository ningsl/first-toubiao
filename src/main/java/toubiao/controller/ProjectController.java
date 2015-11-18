package toubiao.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.apache.bcel.classfile.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;



























import toubiao.controller.untils.ControllerUtils;
import toubiao.pageModel.DataGrid;
import toubiao.pageModel.FilterCondition;
import toubiao.pageModel.Json;
import toubiao.pageModel.PageHelper;
import toubiao.pageModel.Photo;
import toubiao.pageModel.Project;
import toubiao.pageModel.ProjectPhoto;
import toubiao.pageModel.User;
import toubiao.service.impl.PhotoServiceImpl;
import toubiao.service.impl.ProjectServiceImpl;

@Controller
@RequestMapping("/projectController")
public class ProjectController extends BaseController {
	static Logger logger = LogManager.getLogger(ResourceController.class.getName());
	
	@Autowired
	private ProjectServiceImpl projectService;
	
	@Autowired
	private PhotoServiceImpl photoService;
	
	/**
	 * 跳转到project/projectGridPage.jsp页面
	 * @return
	 */
	@RequestMapping("/projectGridPage")
	public String projectGridPage(){
		return "/project/projectGridPage";
	}
	
	/**
	 * 保存projectDetailPart
	 * @return 
	 */
	@RequestMapping("/saveDetailPart2")
	public String saveDetailPart(HttpServletRequest request,Project project){
		if(project !=null){
			System.out.println("saveDetailPart="+project.getClassByProfession());
			System.out.println(project.getId());
			System.out.println(project.getDesignerIds());
			System.out.println(project.getDesignerNames());
		}
		projectService.updateProjectDetail(project);
		
		//待补充 ，如果保存失败，saveDetailPart方法抛异常，捕获后，返回错误消息，并返回detail页面
		
		
		//如果保存成功，request存入 project对象，返回
		request.setAttribute("project", project);
		
		System.out.println(project.getDesignerNames());
		
		return "/detailPart";
	}
	
	
	/**
	 * 更新projectDetailPart
	 * @return 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/updateDetailPart")
	@ResponseBody
	public Json updateDetailPartByJson(HttpServletRequest request,HttpServletResponse response, Project project) throws ServletException, IOException{
		if(project ==null){
			/*System.out.println("saveDetailPart="+project.getClassByProfession());
			System.out.println(project.getId());
			System.out.println(project.getDesignerIds());
			System.out.println(project.getDesignerNames());*/
		}
		Json json=projectService.updateProjectDetail(project);
		
		//待补充 ，如果保存失败，saveDetailPart方法抛异常，捕获后，返回错误消息，并返回detail页面
		
		
		//如果保存成功，request存入 project对象，返回
		RequestDispatcher rd=request.getRequestDispatcher("/detailPart.jsp");
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"));
        
        HttpServletResponse rep=new HttpServletResponseWrapper(response){
        	public PrintWriter getWriter() throws IOException {
                return pw;
            }
        };
		
		request.setAttribute("project", project);
		
		rd.include(request, rep);
		pw.flush();
		String htmlString=os.toString("UTF-8");
		/*System.out.println("html"+htmlString);*/
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("html", htmlString);
		json.setObj(htmlString);
		
		pw.close();
		os.close();
		
		return json;
	}
	
	
	/**
	 * 更新projectDetailPart
	 * @return 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/saveDetailPart")
	@ResponseBody
	public Json saveDetailPartByJson(HttpServletRequest request,HttpServletResponse response, Project project) throws ServletException, IOException{
		if(project ==null){
			/*System.out.println("saveDetailPart="+project.getClassByProfession());
			System.out.println(project.getId());
			System.out.println(project.getDesignerIds());
			System.out.println(project.getDesignerNames());*/
		}
		Json json=projectService.saveProjectDetail(project);
		
		//待补充 ，如果保存失败，saveDetailPart方法抛异常，捕获后，返回错误消息，并返回detail页面
		
		
		//如果保存成功，request存入 project对象，返回
		RequestDispatcher rd=request.getRequestDispatcher("/detailPart.jsp");
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"));
        
        HttpServletResponse rep=new HttpServletResponseWrapper(response){
        	public PrintWriter getWriter() throws IOException {
                return pw;
            }
        };
		
		request.setAttribute("project", project);
		
		rd.include(request, rep);
		pw.flush();
		String htmlString=os.toString("UTF-8");
		/*System.out.println("html"+htmlString);*/
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("html", htmlString);
		json.setObj(htmlString);
		
		pw.close();
		os.close();
		
		return json;
	}
	
	
	/**
	 * project detail part test 
	 * @return project page model
	 */
	@RequestMapping("/projectDetailPartTest")
	@ResponseBody
	public Project projectDetailPartTest(HttpServletRequest request){
		Project project=projectService.getProjectModel();
		System.out.println(project.getProjectName());
		
		Project p=new Project();
		p.setProjectName("test");
		return project;
	}
	
	
	/**
	 * project detail part test 
	 * @return project page model
	 */
	@RequestMapping("/projectDetailTest")
	public String projectDetailTest(HttpServletRequest request){
		Project project=projectService.getProjectModel();
		List<ProjectPhoto> projectPhotoList=photoService.getProjectPhotoList(project.getId());
		/*
		System.out.println("getProjectName=="+project.getProjectName());
		System.out.println("getDesignerNames=="+project.getDesignerNames());
		System.out.println("getProjectPhotoList()=="+project.getProjectPhotoSet().size());
		for(ProjectPhoto p:project.getProjectPhotoSet()){
			System.out.println(p.getFileName());
			System.out.println(p.getProjectId());
		}*/
		request.setAttribute("project", project);
		request.setAttribute("projectPhotoList", projectPhotoList);
		return "/projectDetail";
	}
	
	/**
	 * project detail part test 
	 * @return project page model
	 */
	@RequestMapping("/projectDetail")
	public String projectDetail(HttpServletRequest request){
		String id=request.getParameter("projectId");
		if(id==null){
			logger.debug("projectId is null");
			return "error";
		}
		
		Long idLong=Long.parseLong(id);
		Project project=projectService.getProjectDetail(idLong);
		List<ProjectPhoto> projectPhotoList=photoService.getProjectPhotoList(project.getId());
		/*
		System.out.println("getProjectName=="+project.getProjectName());
		System.out.println("getDesignerNames=="+project.getDesignerNames());
		System.out.println("getProjectPhotoList()=="+project.getProjectPhotoSet().size());
		for(ProjectPhoto p:project.getProjectPhotoSet()){
			System.out.println(p.getFileName());
			System.out.println(p.getProjectId());
		}*/
		request.setAttribute("project", project);
		request.setAttribute("projectPhotoList", projectPhotoList);
		return "/projectDetail2";
	}
	
	/**
	 * 刷新detail part部分
	 * @return project page model
	 */
	@RequestMapping("/detailPart")
	public String detailPart(HttpServletRequest request){
		Project project=projectService.getProjectModel();
		System.out.println(project.getProjectName());
		request.setAttribute("project", project);
		return "/detailPart";
	}
	
	/**
	 * project detail part test 
	 * @return project page model
	 */
	@RequestMapping("/detailEditPart")
	public String detailEditPart(HttpServletRequest request){
		Project project=projectService.getProjectModel();
		System.out.println(project.getProjectName());
		request.setAttribute("project", project);
		return "/detailEditPart";
	}

	/**
	 * 通过分页形式，获取项目信息，无过滤信息
	 * 
	 * @param project
	 * @param PageHelper
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGridByHqlCondition( PageHelper ph,HttpServletRequest request)  {
		String conditionsJson=request.getParameter("conditionsJson");
		if(conditionsJson==null || conditionsJson.equals("")){
			conditionsJson="[]";
		}
		logger.debug(conditionsJson);
		List<FilterCondition> conditionList = JSON.parseArray(conditionsJson,
				FilterCondition.class);
		logger.debug(conditionList.size());
		
		return projectService.dataGridByConditions(conditionList, ph);
	}
	/**
	 * 更新描述部分
	 * 
	 * @param project
	 * @param PageHelper
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/saveDescription")
	@ResponseBody
	public Json saveDescription(Project project){
		if(project==null){
			throw new RuntimeException("project is null");
		}
		
		logger.debug(project.getId());
		projectService.saveDescription(project);
		
		Json json=new Json();
		json.setMsg("更新成功");
		json.setSuccess(true);
		return json;
	}
	
	/**
	 * 更新描述部分
	 * 
	 * @param project
	 * @param PageHelper
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/updateDescription")
	@ResponseBody
	public Json updateDescription(Project project){
		if(project==null){
			throw new RuntimeException("project is null");
		}
		
		projectService.updateDescription(project);
		
		Json json=new Json();
		json.setMsg("更新成功");
		json.setSuccess(true);
		return json;
	}
	
	
	@RequestMapping("/deleteProject")
	@ResponseBody
	public Json delete(String id){
		logger.debug("in delete controller");
		Json json=new Json();
		json.setMsg("删除失败");
		json.setSuccess(false);
		if(id==null){
			return json;
		}
		Long idLong=Long.parseLong(id);
		projectService.delete(idLong);
		json.setMsg("删除成功");
		json.setSuccess(true);
		return json;
	}
	
	@RequestMapping("/batchDelete")
	@ResponseBody
	public Json batchDelete(String ids){
		logger.debug("in delete controller,ids="+ids);
		Json json=new Json();
		json.setMsg("删除失败");
		json.setSuccess(false);
		if(ids==null){
			return json;
		}
		String[] idArray=ids.split(",");
		List<Long> idList=new ArrayList<>();
		for(String id :idArray){
			Long idLong=Long.parseLong(id);
			idList.add(idLong);
		}
		projectService.batchDelete(idList);
		json.setMsg("批量删除成功");
		json.setSuccess(true);
		return json;
	}
	
	
	@RequestMapping("/addProject")
	public String addProject(){
		
		return "/addProject";
	}
	

	@RequestMapping("/saveOrUpdateDetailPart")
	@ResponseBody
	public Json saveOrUpdateDetailPart(HttpServletRequest request,HttpServletResponse response, Project project) throws IOException, ServletException{
		if(project ==null){
			Json json=new Json();
			json.setSuccess(false);
			json.setMsg("保存失败");
			logger.debug("project is null!");
		}
		Json json=projectService.saveOrUpdateProjectDetail(project);
		
		//待补充 ，如果保存失败，saveDetailPart方法抛异常，捕获后，返回错误消息，并返回detail页面
		
		
		//如果保存成功，request存入 project对象，返回
		RequestDispatcher rd=request.getRequestDispatcher("/detailPart2.jsp");
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os,"UTF-8"));
        
        HttpServletResponse rep=new HttpServletResponseWrapper(response){
        	public PrintWriter getWriter() throws IOException {
                return pw;
            }
        };
    	Map<String,String> map=(Map<String,String>)json.getObj();
    	project.setId(Long.parseLong(map.get("id")));
		request.setAttribute("project", project);
		
		rd.include(request, rep);
		pw.flush();
		String htmlString=os.toString("UTF-8");
		/*System.out.println("html"+htmlString);*/
	
		map.put("html", htmlString);
		pw.close();
		os.close();
		
		return json;
	}
	
	//下载Excle all
	@RequestMapping("/downloadExcelAll")
	public void downloadExcelAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
  
        File downloadFile=new File(projectService.downloadeExcelOfAll());
        SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
        String dateString=format.format(new Date());
        String saveName="所有项目("+dateString+").xlsx";
  
        long fileLength = downloadFile.length();  
  
       
        response.setHeader("Content-disposition", "attachment; filename="  
                + new String(saveName.getBytes("utf-8"), "ISO8859-1"));  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
  
        bis = new BufferedInputStream(new FileInputStream(downloadFile));  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close(); 
	}
	
	//下载Excel checked
	@RequestMapping("/downloadExcelChecked")
	public void downloadExcelChecked(HttpServletRequest request, HttpServletResponse response,String ids) throws Exception{
		if(ids==null){
			return ;
		}
		String fileAbsPath=projectService.downloadeExcelChecked(ids);
		SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
        String dateString=format.format(new Date());
        String savedName="勾选项目("+dateString+").xlsx";
        
        ControllerUtils.downloadFile(fileAbsPath, savedName, request, response);
	}
	
	//下载Excel checked
	@RequestMapping("/downloadExcelFiltered")
	public void downloadExcelFiltered(HttpServletRequest request, HttpServletResponse response) {
		String conditionsJson=request.getParameter("conditionsJson");
		if(conditionsJson==null || conditionsJson.equals("")){
			conditionsJson="[]";
		}
		logger.debug(conditionsJson);
		List<FilterCondition> conditionList = JSON.parseArray(conditionsJson,
				FilterCondition.class);
		logger.debug(conditionList.size());
		
		String fileAbsPath=null;
		try {
			fileAbsPath = projectService.downloadExcelFiltered(conditionList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("生成文件错误");
			e.printStackTrace();
		}
		logger.debug(fileAbsPath);
		SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
        String dateString=format.format(new Date());
        String savedName="筛选项目("+dateString+").xlsx";
        
        try {
			ControllerUtils.downloadFile(fileAbsPath, savedName, request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//下载Excle all
		@RequestMapping("/downloadDocxAll")
		public void downloadDocxAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
			response.setContentType("text/html;charset=UTF-8");  
	        request.setCharacterEncoding("UTF-8");  
	        BufferedInputStream bis = null;  
	        BufferedOutputStream bos = null;  
	  
	        File downloadFile=new File(projectService.downloadeDocxOfAll());
	        SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
	        String dateString=format.format(new Date());
	        String saveName="所有项目("+dateString+").zip";
	  
	        long fileLength = downloadFile.length();  
	  
	       
	        response.setHeader("Content-disposition", "attachment; filename="  
	                + new String(saveName.getBytes("utf-8"), "ISO8859-1"));  
	        response.setHeader("Content-Length", String.valueOf(fileLength));  
	  
	        bis = new BufferedInputStream(new FileInputStream(downloadFile));  
	        bos = new BufferedOutputStream(response.getOutputStream());  
	        byte[] buff = new byte[2048];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	            bos.write(buff, 0, bytesRead);  
	        }  
	        bis.close();  
	        bos.close(); 
		}
		
		//下载Excel checked
		@RequestMapping("/downloadDocxChecked")
		public void downloadDocxChecked(HttpServletRequest request, HttpServletResponse response,String ids) throws Exception{
			if(ids==null){
				return ;
			}
			String fileAbsPath=projectService.downloadDocxChecked(ids);
			SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
	        String dateString=format.format(new Date());
	        String savedName="勾选项目("+dateString+").zip";
	        
	        ControllerUtils.downloadFile(fileAbsPath, savedName, request, response);
		}
		
		//下载Excel checked
		@RequestMapping("/downloadDocxFiltered")
		public void downloadDocxFiltered(HttpServletRequest request, HttpServletResponse response) {
			String conditionsJson=request.getParameter("conditionsJson");
			if(conditionsJson==null || conditionsJson.equals("")){
				conditionsJson="[]";
			}
			logger.debug(conditionsJson);
			List<FilterCondition> conditionList = JSON.parseArray(conditionsJson,
					FilterCondition.class);
			logger.debug(conditionList.size());
			
			String fileAbsPath=null;
			fileAbsPath = projectService.downloadDocxFiltered(conditionList);
			logger.debug(fileAbsPath);
			SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
	        String dateString=format.format(new Date());
	        String savedName="筛选项目("+dateString+").zip";
	        
	        try {
				ControllerUtils.downloadFile(fileAbsPath, savedName, request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
