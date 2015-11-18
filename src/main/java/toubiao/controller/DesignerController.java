package toubiao.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import toubiao.controller.untils.ControllerUtils;
import toubiao.model.TcertificatePhoto;
import toubiao.pageModel.CertificatePhoto;
import toubiao.pageModel.DataGrid;
import toubiao.pageModel.Designer;
import toubiao.pageModel.FilterCondition;
import toubiao.pageModel.Json;
import toubiao.pageModel.PageHelper;
import toubiao.pageModel.Project;
import toubiao.pageModel.ProjectPhoto;
import toubiao.service.impl.DesignerServiceImpl;

import com.alibaba.fastjson.JSON;

/**
 * @author nsl
 *
 */
@Controller
@RequestMapping("/designerController")
public class DesignerController {
	
	static Logger logger = LogManager.getLogger(DesignerController.class.getName());
	
	@Autowired
	DesignerServiceImpl designerService;
	
	/**
	 * 跳转到/DesignerController/designerGridPage.jsp页面
	 * @return
	 */
	@RequestMapping("/designerGridPage")
	public String projectGridPage(){
		return "/designer/designerGridPage";
	}
	
	/**
	 * project detail part test 
	 * @return project page model
	 */
	@RequestMapping("/designerDetail")
	public String projectDetail(HttpServletRequest request){
		String id=request.getParameter("designerId");
		if(id==null){
			logger.debug("designerId is null");
			return "error";
		}
		
		Long idLong=Long.parseLong(id);
		Designer designer=designerService.getDesignerDetail(idLong);
		List<CertificatePhoto> certificatePhotoList=designerService.getCertificatePhotoList(designer.getId());
		request.setAttribute("designer", designer);
		request.setAttribute("certificatePhotoList", certificatePhotoList);
		return "/designerDetail";
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
		
		return designerService.dataGridByConditions(conditionList, ph);
	}
	
	/** 删除Designer，根据id
	 * @param id
	 * @return success
	 */
	@RequestMapping("/deleteDesigner")
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
		designerService.deleteDesignerById(idLong);
		json.setMsg("删除成功");
		json.setSuccess(true);
		return json;
	}
	
	
	/**新增 designer
	 * @return
	 */
	@RequestMapping("/addDesigner")
	public String addDesigner(){
		return "/addDesigner";
	}
	
	//下载Excle all
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadExcelAll")
	public void downloadExcelAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
  
        File downloadFile=new File(designerService.downloadeExcelOfAll());
        SimpleDateFormat format=new SimpleDateFormat("yyMMdd");
        String dateString=format.format(new Date());
        String saveName="所有设计人员("+dateString+").xlsx";
  
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
		String fileAbsPath=designerService.downloadeExcelChecked(ids);
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
		fileAbsPath = designerService.downloadExcelFiltered(conditionList);
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
	  
	        File downloadFile=new File(designerService.downloadeDocxOfAll());
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
			String fileAbsPath=designerService.downloadDocxChecked(ids);
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
			fileAbsPath = designerService.downloadDocxFiltered(conditionList);
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
