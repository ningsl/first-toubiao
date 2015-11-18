package toubiao.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import toubiao.pageModel.Project;
import toubiao.pageModel.Tree;
import toubiao.pageModel.TreeIdL;
import toubiao.service.DepartmentServiceI;
import toubiao.service.impl.ProjectServiceImpl;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private  DepartmentServiceI departmentService;
	
	/**
	 * 
	 * @return department tree
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> getDepartmentTree(HttpServletRequest request){
		String checkIds=request.getParameter("checkIds");
		return departmentService.getDepartmentTree(checkIds);
	}
	
	
	
	/**
	 * department detail 
	 * @return departmentTree with designer
	 */
	@RequestMapping("/treeWithDesigner")
	@ResponseBody
	public List<Tree> getDepartmentTreeWithDesigner(HttpServletRequest request){
		String idsString=request.getParameter("checkedIds");
		return departmentService.getDepartmentTreeWithDesigner(idsString);
	}
}
