package toubiao.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
