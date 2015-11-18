package toubiao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toubiao.dao.DepartmentDaoI;
import toubiao.model.Tdepartment;
import toubiao.model.Tdesigner;
import toubiao.pageModel.Department;
import toubiao.pageModel.Designer;
import toubiao.pageModel.Tree;
import toubiao.pageModel.TreeIdL;
import toubiao.service.DepartmentServiceI;

@Service
public class DepartmentServiceImpl implements DepartmentServiceI{
	
	@Autowired
	private DepartmentDaoI departmentDao;
	
	public List<Department> getDepartmentList(){
		List<Tdepartment> list=departmentDao.find("from Tdepartment");
		String[] ignoreProperties={"designerList","projectSet"};
		
		List<Department> departments=new ArrayList<Department>();
		
		if(list!=null){
			for(Tdepartment t:list){
				Department d=new Department();
				BeanUtils.copyProperties(t, d, ignoreProperties);
				departments.add(d);
			}
		}
		return departments;
	}
	
	
	public List<Tree> getDepartmentTree(String checkIds){
		List<Long> idList=new ArrayList<Long>(0);
		if(checkIds!=null){
			String[] idArray=checkIds.split(";");
			for(String id:idArray){
				Long idLong=Long.parseLong(id);
				idList.add(idLong);
			}
		}
		
		List<Tree> treeList=new ArrayList<>();
		List<Department> departmentList=getDepartmentList();
		for(Department d:departmentList){
			Tree tree=new Tree();
			tree.setId("department"+d.getId());
			tree.setText(d.getName());
			if(idList.contains(d.getId())){
				tree.setChecked(true);
			}
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("id", d.getId());
			attr.put("name", d.getName());
			attr.put("treeId",tree.getId());
			tree.setAttributes(attr);
			treeList.add(tree);
		}
		return treeList;
	}
	
	public List<Department> getDepartmentListWithDesigner(){
		List<Tdepartment> list=departmentDao.find("select distinct d from Tdepartment d join fetch d.designerList order by d.seq  asc");
		String[] ignoreProperties={"designerList","projectSet","department"};
		
		List<Department> departments=new ArrayList<Department>();
		
		
		
		if(list!=null){
			for(Tdepartment t:list){
				Department d=new Department();
				BeanUtils.copyProperties(t, d, ignoreProperties);
				
				List<Tdesigner> designerList=t.getDesignerList();
				for(Tdesigner designer:designerList){
					Designer designerPage=new Designer();
					
					BeanUtils.copyProperties(designer, designerPage,ignoreProperties);
					
					Department department=new Department();
					department.setId(designer.getDepartment().getId());
					department.setName(designer.getDepartment().getName());
					
					designerPage.setDepartmentName(department.getName());
					
					d.getDesignerList().add(designerPage);
				}
				departments.add(d);
			}
		}
		return departments;
	}
	
	
	public List<Tree> getDepartmentTreeWithDesigner(String idsString){
		List<Long> idList=new ArrayList<Long>(0);
		if(idsString!=null){
			String[] idArray=idsString.split(";");
			for(String id:idArray){
				Long idLong=Long.parseLong(id);
				idList.add(idLong);
			}
		}
		List<Tree> treeList=new ArrayList<>();
		List<Department> departmentList=getDepartmentListWithDesigner();
		for(Department d:departmentList){
			Tree tree=new Tree();
			tree.setId("department"+d.getId());
			tree.setText(d.getName());
			tree.setState("closed");
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("id", d.getId());
			attr.put("name", d.getName());
			
			tree.setAttributes(attr);
			treeList.add(tree);
			for(Designer designer:d.getDesignerList()){
				Tree t=new Tree();
				t.setId("designer"+designer.getId());
				t.setText(designer.getName());
				t.setPid("department"+d.getId());
				
				if(idList.contains(designer.getId())){
					t.setChecked(true);
				}
				Map<String, Object> attr2 = new HashMap<String, Object>();
				attr2.put("id", designer.getId());
				attr2.put("name", designer.getName());
				attr2.put("department", designer.getDepartmentName());
				attr2.put("phone", designer.getPhone());
				attr2.put("treeId", t.getId());
				t.setAttributes(attr2);
				treeList.add(t);
			}
		}
		return treeList;
	}
}
