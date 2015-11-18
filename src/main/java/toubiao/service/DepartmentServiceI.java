package toubiao.service;

import java.util.List;

import toubiao.pageModel.Tree;
import toubiao.pageModel.TreeIdL;

public interface DepartmentServiceI {

	/**
	 * 获得带有desinger name id 的department tree
	 * @param idsString 
	 * @return
	 */
	public List<Tree> getDepartmentTreeWithDesigner(String idsString);
	
	public List<Tree> getDepartmentTree(String checkIds);
}
