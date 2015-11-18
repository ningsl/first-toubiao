package toubiao.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;








import java.util.Set;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

import nsl.utils.EasyUI133Utils;
import nsl.utils.file.FileUtils;
import nsl.utils.zip.ZipCompressor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

















































import antlr.debug.DebuggingCharScanner;

import com.alibaba.fastjson.JSON;

import toubiao.constant.Web;
import toubiao.dao.DepartmentDaoI;
import toubiao.dao.DesignerDaoI;
import toubiao.dao.ProjectDaoI;
import toubiao.dao.ProjectPhotoDaoI;
import toubiao.dao.ResourceDaoI;
import toubiao.dao.ResourceTypeDaoI;
import toubiao.dao.UserDaoI;
import toubiao.model.Tcontact;
import toubiao.model.Tcontract;
import toubiao.model.Tdepartment;
import toubiao.model.Tdesigner;
import toubiao.model.Tproject;
import toubiao.model.TprojectPhoto;
import toubiao.pageModel.Contract;
import toubiao.pageModel.DataGrid;
import toubiao.pageModel.Department;
import toubiao.pageModel.Designer;
import toubiao.pageModel.FilterCondition;
import toubiao.pageModel.Json;
import toubiao.pageModel.PageHelper;
import toubiao.pageModel.Photo;
import toubiao.pageModel.Project;
import toubiao.pageModel.ProjectPhoto;
import toubiao.pageModel.User;
import toubiao.utils.FilterHqlFactory;
import toubiao.utils.excel.ExcelUtils;

@Service
public class ProjectServiceImpl {
	
	static Logger logger = LogManager.getLogger(ProjectServiceImpl.class.getName());
	
	@Autowired
	private ProjectDaoI projectDao;
	
	@Autowired
	private ResourceDaoI resourceDao;

	@Autowired
	private ResourceTypeDaoI resourceTypeDao;

	@Autowired
	private UserDaoI userDao;
	
	@Autowired
	private DepartmentDaoI departmentDao;
	
	@Autowired
	private DesignerDaoI designerDao;
	
	@Autowired
	private ProjectPhotoDaoI projectPhotoDao;
	
	
	public DataGrid dataGrid(Project project, PageHelper ph) {
		DataGrid dg = new DataGrid();
		List<Project> projectList = new ArrayList<Project>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tproject project join fetch project.designerSet disigner where 1=1";
		List<Tproject> l = projectDao.find(hql, params, ph.getPage(), ph.getRows());
		if (l != null && l.size() > 0) {
			for (Tproject t : l) {
				Project p = new Project();
				BeanUtils.copyProperties(t, p);
				
				projectList.add(p);
			}
		}
		dg.setRows(projectList);
		dg.setTotal(projectDao.count("select count(*) from Tproject" ));
		return dg;
	}
	
	/*public DataGrid dataGridByConditions(List<FilterCondition> conditionList, PageHelper ph) {
		DataGrid dg = new DataGrid();
		String conditionHql=FilterHqlFactory.buildHqlByList(conditionList);
		String ordHql=EasyUI133Utils.buildOrderHql("project", ph, "projectName", "asc");
		System.out.println(conditionHql);
		String hql = "select distinct project from Tproject project left join fetch project.designerSet designer left join fetch project.departmentSet department where 1=1";//查询list
		String countHql="select count(*) from Tproject project left join project.departmentSet department where 1=1";//查询数量
		if(conditionHql!=null  &&  !conditionHql.equals("")){
			hql=hql+" and "+conditionHql;
			countHql+=" and "+conditionHql;
		}
		
		hql+=ordHql;
		
		System.out.println(hql);
		
		List<Tproject> tpList = projectDao.find(hql, ph.getPage(), ph.getRows());
		List<Project> projectList=new ArrayList<>();
		
		String[] igorFields={"designerSet","projectSet","department","designerList","departmentSet"};//beanutils.copyProperties时的排除列表，project:designerSet;   designer:projectSet
		
		if (tpList != null && tpList.size() > 0) {
			for (Tproject t : tpList) {
				Project p = new Project();
						
				BeanUtils.copyProperties(t, p, igorFields);
					
				
				//复制designerSet
				if(t.getDesignerSet().size()>0){
					String ids="",names="";
					for(Tdesigner d:t.getDesignerSet()){
						
						Designer designer=new Designer();
						
						BeanUtils.copyProperties(d, designer, igorFields);
						
						Tdepartment departmentSrc=d.getDepartment();
						Department departmentDes=new Department();
						BeanUtils.copyProperties(departmentSrc, departmentDes, igorFields);
						
						designer.setDepartment(departmentDes);
						
						
						if(ids.equals("")){
							ids=d.getId()+"";
						}else {
							ids+=";"+d.getId();
						}
						
						if(names.equals("")){
							names=d.getName()+"";
						}else {
							names+=";"+d.getName();
						}
						
						p.getDesignerSet().add(designer);
						p.setDesignerIds(ids);
						p.setDesignerNames(names);
						
						logger.info(ids);
						logger.info(names);
					}
				}
				
				
				//复制departmentList
				if(t.getDepartmentSet().size()>0){
					for(Tdepartment departmentSrc:t.getDepartmentSet()){
						Department departmentDes=new Department();
						BeanUtils.copyProperties(departmentSrc, departmentDes, igorFields);
						p.getDepartmentSet().add(departmentDes);
					}
				}
				
				projectList.add(p);
			}
		}
		dg.setRows(projectList);

		dg.setTotal(projectDao.count(countHql));
		
		String jsonString=JSON.toJSONString(dg);
		System.out.println(jsonString);
		
		return dg;
	}
*/
	
	public Project getProjectModel() {
		// TODO Auto-generated method stub
		String hql="select distinct p from Tproject p "
				+ " left join fetch p.designerSet designer"
				+ " left join fetch p.contact contact"
				+ " left join fetch p.contractSet contract"
				+ " left join fetch p.departmentSet department"
				+ " left join fetch p.projectPhotoSet photo"
				+ " where p.id=1295"
				+ " group by designer,contact,contract,department,photo";
		/*String hql="from Tproject p ";*/
		List<Tproject> list=projectDao.find(hql);
		System.out.println(list.size());
		Tproject t=list.get(0);

		Project p = new Project();
						
		BeanUtils.copyProperties(t, p);
		//复制designerSet 
		if(t.getDesignerSet().size()>0){
			String ids="",names="";
			for(Tdesigner d:t.getDesignerSet()){
				if(ids.equals("")){
					ids=d.getId()+"";
				}else {
					ids+=";"+d.getId();
				}
				
				if(names.equals("")){
					names=d.getName()+"";
				}else {
					names+=";"+d.getName();
				}
				p.setDesignerIds(ids);
				p.setDesignerNames(names);
				logger.info(ids);
				logger.info(names);
			}
		}
		
		//复制departmentSet
		if(t.getDepartmentSet().size()>0){
			String ids="",names="";
			for(Tdepartment d:t.getDepartmentSet()){
				if(ids.equals("")){
					ids=d.getId()+"";
				}else {
					ids+=";"+d.getId();
				}
				
				if(names.equals("")){
					names=d.getName()+"";
				}else {
					names+=";"+d.getName();
				}
				
				p.setDepartmentIds(ids);
				p.setDepartmentNames(names);
				logger.info(ids);
				logger.info(names);
			}
		}
		
		//赋值contact
		Tcontact contact=t.getContact();
		if(contact!=null){
			p.setContactName(contact.getName());
			p.setContactId(contact.getId());
			p.setContactPhone(contact.getTelephone());
		}
		
		return p;
	}
	
	
	
	
	
	public Json updateProjectDetail(Project project){
		Json json=new Json();
		
		if(project==null)
		{
			logger.debug("project is null!");
			json.setMsg("保存/更新 失败!");
			json.setSuccess(false);
			return json;
		}
		logger.debug(project.getId());
		logger.debug(project.getDesignCode());
		//判断designCode是否为空  和 唯一
		String designCode=project.getDesignCode();
		if(designCode==null || designCode.trim().equals("")){
			logger.debug("designCode is null or 空字符串!");
			json.setMsg("设计编号不能为空!");
			json.setSuccess(false);
			return json;
		}
		Long designCodeTimes=getDesignCodeRepeatTimes(designCode);
		if(designCodeTimes>1){
			logger.debug("designCode is not unique!");
			json.setMsg("设计编号重复!");
			json.setSuccess(false);
			return json;
		}
		
		json.setMsg("更新成功");
		
		
		//if the project is new, a.set web path field b.create serious dirs 
		/*if(project.getId()==null){
			json.setMsg("保存成功");
			logger.debug("create new");
			//1.partA 为空或"",放入其他文件夹下  2.partA 不为空，放入相应文件下
			String webPath="/resource";
			String partA=project.getPartA();
			
			if(partA==null || partA.equals("")){
				webPath=webPath+"/其它/"+designCode.trim();
			}else{
				webPath=webPath+"/"+partA.trim()+"/"+designCode.trim();
			}
			project.setWebPath(webPath);
			logger.debug(webPath);
			
			File dir1=new File(Web.LOCAL_ABS_PATH+webPath+"/合同照片");
			File dir2=new File(Web.LOCAL_ABS_PATH+webPath+"/获奖照片");
			logger.debug(dir1.getAbsolutePath());
			dir1.mkdirs();
			dir2.mkdirs();
		}*/
		
		String[] igorFields={"designerSet","rewardSet","contractSet","contact","departmentSet"};//beanutils.copyProperties时的排除列表，project:designerSet;   designer:projectSet
		
		Tproject tproject=new Tproject();
		
		BeanUtils.copyProperties(project, tproject,igorFields);
		
		//save designerSet
		if(project.getDesignerIds()!=null && !project.getDesignerIds().trim().equals("")){
			System.out.println(project.getDesignerIds());
			Set<Tdesigner> designerSet=new HashSet<>();
			String[] ids=project.getDesignerIds().split(";");
			/*List<String> departmentIdList=Arrays.asList(project.getDepartmentIds());*/
			for(String id :ids){
				/*System.out.println(id+"***");*/
				Long idLong=Long.parseLong(id);
				Tdesigner d=designerDao.get(Tdesigner.class, idLong);
				designerSet.add(d);
			}
			tproject.setDesignerSet(designerSet);
		}
		
		//save department set
		if(project.getDepartmentIds()!=null && !project.getDepartmentIds().trim().equals("")){
			System.out.println(project.getDepartmentIds());
			Set<Tdepartment> departmentSet=new HashSet<>();
			String[] ids=project.getDepartmentIds().split(";");
			for(String id :ids){
				/*System.out.println(id+"***");*/
				Long idLong=Long.parseLong(id);
				Tdepartment d=departmentDao.get(Tdepartment.class, idLong);
				departmentSet.add(d);
			}
			tproject.setDepartmentSet(departmentSet);
		}
		
		/*projectDao.saveOrUpdate(tproject);*/
		projectDao.update(tproject);
		json.setSuccess(true);
		return json;
	}
	
	public Project getProjectWithProjectPhotos(Long id){
		Tproject p=null;
		if(id!=null){
			p=projectDao.get(Tproject.class, id);
		}
		Project project=new Project();
		
		logger.debug(p.getProjectPhotoSet().size());
		
		for(TprojectPhoto photoSrc:p.getProjectPhotoSet()){
			ProjectPhoto photoDes=new ProjectPhoto();
			BeanUtils.copyProperties(photoSrc, photoDes);
			photoDes.setProjectId(id);
			/*project.getProjectPhotoSet().add(photoDes);*/
		}
		
		return project;
	}

	public void updateDescription(Project project) {
		// TODO Auto-generated method stub
		Long id=project.getId();
		String description=project.getDescription();
		
		/*Map<String, Object> params = new HashMap<String, Object>();
		if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 查自己有权限的角色
			l = roleDao.find("select distinct t from Trole t join fetch t.tusers user where user.id = :userId order by t.seq", params);
		} else {
			l = roleDao.find("from Trole t order by t.seq");
		}*/
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("description", description);
		String hql="update Tproject p set p.description= :description where p.id = :id";//"update Student s set s.sage=? where s.sname=?",params);
		projectDao.executeHql(hql, params);
	}
	
	
	public DataGrid dataGridByConditions(List<FilterCondition> conditionList, PageHelper ph) {
		DataGrid dg = new DataGrid();
		String conditionHql=FilterHqlFactory.buildHqlByList(conditionList);
		String ordHql=EasyUI133Utils.buildOrderHql(ph);
		System.out.println(conditionHql);
		String hql = "select distinct project from Tproject project left join fetch project.designerSet designer left join fetch project.departmentSet department where 1=1";//查询list
		String countHql="select count(*) from Tproject project left join project.departmentSet department where 1=1";//查询数量
		if(conditionHql!=null  &&  !conditionHql.equals("")){
			hql=hql+" and "+conditionHql;
			countHql+=" and "+conditionHql;
		}
		
		hql+=ordHql;
		
		System.out.println(hql);
		
		List<Tproject> tpList = projectDao.find(hql, ph.getPage(), ph.getRows());
		List<Project> projectList=new ArrayList<>();
		/*String[] igorFields={"contact"};*/
		
		if (tpList != null && tpList.size() > 0) {
			for (Tproject project : tpList) {
				Long id=(Long) project.getId();
				projectDao.getCurrentSession().evict(project);
				Tproject t=projectDao.get(Tproject.class, id);
				projectList.add(tproject2project(t));
				
				
			}
		}
		dg.setRows(projectList);

		dg.setTotal(projectDao.count(countHql));
		
		String jsonString=JSON.toJSONString(dg);
		System.out.println(jsonString);
		
		return dg;
	}

	public Project getProjectDetail(Long idLong) {
		// TODO Auto-generated method stub
		String hql="select distinct p from Tproject p "
				+ " left join fetch p.designerSet designer"
				+ " left join fetch p.contact contact"
				+ " left join fetch p.contractSet contract"
				+ " left join fetch p.departmentSet department"
				+ " left join fetch p.projectPhotoSet photo"
				+ " where p.id="+idLong
				+ " group by designer,contact,contract,department,photo";
		/*String hql="from Tproject p ";*/
		List<Tproject> list=projectDao.find(hql);
		System.out.println(list.size());
		Tproject t=list.get(0);

		Project p = tproject2project(t);
						
		return p;
	}

	public void delete(Long idLong) {
		// 1.删除相应文件夹  2.删除ProjectPhoto 3.删除 Reward 4.删除project
		
		// 1.删除相应文件夹：待补充
		
		Tproject project=projectDao.get(Tproject.class, idLong);
		if(project==null){
			logger.debug(idLong+ " project is null");
			return ;
		}
		//2.删除ProjectPhoto
		String deletePhotoHql="delete from TprojectPhoto p where p.project.id="+idLong;
		int count=projectPhotoDao.executeHql(deletePhotoHql);
		logger.debug(idLong+" delete photos number:"+count);
		
		//3.删除 Reward 
		String deleteRewardHql="delete from Treward r where r.project.id="+idLong;
		
		/*count=projectPhotoDao.executeHql(deletePhotoHql);*/
		logger.debug(idLong+"  delete reward number:"+count);
		
		//4.删除
		projectDao.delete(project);
		
	    logger.debug(idLong+"  delete success");
	}

	public void batchDelete(List<Long> idList) {
		// TODO Auto-generated method stub
		// 1.删除相应文件夹  2.删除ProjectPhoto 3.删除 Reward 4.删除project
		if(idList==null){
			return ;
		}
		for(Long id :idList){
			delete(id);
		}
	}
	
	public Long getDesignCodeRepeatTimes(String designCode){
		Long times;
		String hql="select count(p) from Tproject p where p.designCode='"+designCode+"'";
		times=projectDao.count(hql);
		return times;
	}

	public Json saveProjectDetail(Project project) {
		Json json=new Json();
		
		if(project==null)
		{
			logger.debug("project is null!");
			json.setMsg("保存失败!");
			json.setSuccess(false);
			return json;
		}
		logger.debug(project.getId());
		logger.debug(project.getDesignCode());
		//判断designCode是否为空  和 唯一
		String designCode=project.getDesignCode();
		if(designCode==null || designCode.trim().equals("")){
			logger.debug("designCode is null or 空字符串!");
			json.setMsg("设计编号不能为空!");
			json.setSuccess(false);
			return json;
		}
		Long designCodeTimes=getDesignCodeRepeatTimes(designCode);
		if(designCodeTimes>1){
			logger.debug("designCode is not unique!");
			json.setMsg("设计编号重复!");
			json.setSuccess(false);
			return json;
		}
		
		json.setMsg("更新成功");
		
		
		//if the project is new, a.set web path field b.create serious dirs 
		if(project.getId()==null){
			json.setMsg("保存成功");
			logger.debug("create new");
			//1.partA 为空或"",放入其他文件夹下  2.partA 不为空，放入相应文件下
			String webPath="/resource";
			String partA=project.getPartA();
			
			if(partA==null || partA.equals("")){
				webPath=webPath+"/其它/"+designCode.trim();
			}else{
				webPath=webPath+"/"+partA.trim()+"/"+designCode.trim();
			}
			project.setWebPath(webPath);
			logger.debug(webPath);
			
			File dir1=new File(Web.WEB_ABS_PATH+webPath+"/合同照片");
			File dir2=new File(Web.WEB_ABS_PATH+webPath+"/获奖照片");
			logger.debug(dir1.getAbsolutePath());
			if(!dir1.exists())
				dir1.mkdirs();
			if(!dir2.exists())
				dir2.mkdirs();
		}
		
		String[] igorFields={"designerSet","rewardSet","contractSet","contact","departmentSet"};//beanutils.copyProperties时的排除列表，project:designerSet;   designer:projectSet
		
		Tproject tproject=new Tproject();
		
		BeanUtils.copyProperties(project, tproject,igorFields);
		
		//save designerSet
		if(project.getDesignerIds()!=null && !project.getDesignerIds().trim().equals("")){
			System.out.println(project.getDesignerIds());
			Set<Tdesigner> designerSet=new HashSet<>();
			String[] ids=project.getDesignerIds().split(";");
			/*List<String> departmentIdList=Arrays.asList(project.getDepartmentIds());*/
			for(String id :ids){
				/*System.out.println(id+"***");*/
				Long idLong=Long.parseLong(id);
				Tdesigner d=designerDao.get(Tdesigner.class, idLong);
				designerSet.add(d);
			}
			tproject.setDesignerSet(designerSet);
		}
		
		//save department set
		if(project.getDepartmentIds()!=null && !project.getDepartmentIds().trim().equals("")){
			System.out.println(project.getDepartmentIds());
			Set<Tdepartment> departmentSet=new HashSet<>();
			String[] ids=project.getDepartmentIds().split(";");
			for(String id :ids){
				/*System.out.println(id+"***");*/
				Long idLong=Long.parseLong(id);
				Tdepartment d=departmentDao.get(Tdepartment.class, idLong);
				departmentSet.add(d);
			}
			tproject.setDepartmentSet(departmentSet);
		}
		
		/*projectDao.saveOrUpdate(tproject);*/
		projectDao.saveOrUpdate(tproject);
		json.setSuccess(true);
		return json;
	}

	public void saveDescription(Project project) {
		// TODO Auto-generated method stub
		if(project.getId()!=null){
			Tproject p=projectDao.get(Tproject.class, project.getId());
			p.setDescription(project.getDescription());
			
		}
	}

	public Json saveOrUpdateProjectDetail(Project project) {
		// TODO Auto-generated method stub
		Json json=new Json();
		logger.debug(project.getId());
		logger.debug(project.getDesignCode());
		//判断designCode是否为空  和 唯一
		String designCode=project.getDesignCode();
		if(designCode==null || designCode.trim().equals("")){
			logger.debug("designCode is null or 空字符串!");
			json.setMsg("设计编号不能为空!");
			json.setSuccess(false);
			return json;
		}
		Long designCodeTimes=getDesignCodeRepeatTimes(designCode);
		if(designCodeTimes>1){
			logger.debug("designCode is not unique!");
			json.setMsg("设计编号重复!");
			json.setSuccess(false);
			return json;
		}
		
		json.setMsg("更新成功");
		
		
		//if the project is new, a.set web path field b.create serious dirs 
		if(project.getId()==null){
			json.setMsg("保存成功");
			logger.debug("create new");
			//1.partA 为空或"",放入其他文件夹下  2.partA 不为空，放入相应文件下
			String webPath="/resource";
			String partA=project.getPartA();
			
			if(partA==null || partA.equals("")){
				webPath=webPath+"/其它/"+designCode.trim();
			}else{
				webPath=webPath+"/"+partA.trim()+"/"+designCode.trim();
			}
			project.setWebPath(webPath);
			logger.debug(webPath);
			
			File dir1=new File(Web.WEB_ABS_PATH+webPath+"/合同照片");
			File dir2=new File(Web.WEB_ABS_PATH+webPath+"/获奖照片");
			logger.debug(dir1.getAbsolutePath());
			if(!dir1.exists())
				dir1.mkdirs();
			if(!dir2.exists())
				dir2.mkdirs();
		}
		
		String[] igorFields={"designerSet","rewardSet","contractSet","contact","departmentSet"};//beanutils.copyProperties时的排除列表，project:designerSet;   designer:projectSet
		
		Tproject tproject=new Tproject();
		
		BeanUtils.copyProperties(project, tproject,igorFields);
		
		//save designerSet
		if(project.getDesignerIds()!=null && !project.getDesignerIds().trim().equals("")){
			System.out.println(project.getDesignerIds());
			Set<Tdesigner> designerSet=new HashSet<>();
			String[] ids=project.getDesignerIds().split(";");
			/*List<String> departmentIdList=Arrays.asList(project.getDepartmentIds());*/
			for(String id :ids){
				/*System.out.println(id+"***");*/
				Long idLong=Long.parseLong(id);
				Tdesigner d=designerDao.get(Tdesigner.class, idLong);
				designerSet.add(d);
			}
			tproject.setDesignerSet(designerSet);
		}
		
		//save department set
		if(project.getDepartmentIds()!=null && !project.getDepartmentIds().trim().equals("")){
			System.out.println(project.getDepartmentIds());
			Set<Tdepartment> departmentSet=new HashSet<>();
			String[] ids=project.getDepartmentIds().split(";");
			for(String id :ids){
				/*System.out.println(id+"***");*/
				Long idLong=Long.parseLong(id);
				Tdepartment d=departmentDao.get(Tdepartment.class, idLong);
				departmentSet.add(d);
			}
			tproject.setDepartmentSet(departmentSet);
		}
		
		/*projectDao.saveOrUpdate(tproject);*/
		projectDao.saveOrUpdate(tproject);
		
		json.setSuccess(true);
		
		Map<String,String> map=new HashMap<String, String>();
		map.put("id", tproject.getId()+"");
		json.setObj(map);
		
		/*json.setObj(obj);*/
		return json;
	}
	
	
	/**
	 * 转换
	 * @param projectSrc
	 * @return
	 */
	public Project tproject2project(Tproject projectScr){
		if(projectScr==null){
			throw new RuntimeException("the paramter of projectSrcis is null");
		}
		
		Project projectDes=new Project();
		
		BeanUtils.copyProperties(projectScr, projectDes);
	/*	logger.debug(projectDes.getPartA());*/
		//复制designerSet 
		if(projectScr.getDesignerSet().size()>0){
			String ids="",names="";
			for(Tdesigner d:projectScr.getDesignerSet()){
				if(ids.equals("")){
					ids=d.getId()+"";
				}else {
					ids+=";"+d.getId();
				}
				
				if(names.equals("")){
					names=d.getName()+"";
				}else {
					names+=";"+d.getName();
				}
				projectDes.setDesignerIds(ids);
				projectDes.setDesignerNames(names);
			/*	logger.info(ids);
				logger.info(names);*/
			}
		}
		
		//复制departmentSet
		if(projectScr.getDepartmentSet().size()>0){
			String ids="",names="";
			for(Tdepartment d:projectScr.getDepartmentSet()){
				if(ids.equals("")){
					ids=d.getId()+"";
				}else {
					ids+=";"+d.getId();
				}
				
				if(names.equals("")){
					names=d.getName()+"";
				}else {
					names+=";"+d.getName();
				}
				
				projectDes.setDepartmentIds(ids);
				projectDes.setDepartmentNames(names);
				/*logger.info(ids);
				logger.info(names);*/
			}
		}
		
		//赋值contact
		Tcontact contact=projectScr.getContact();
		if(contact!=null){
			projectDes.setContactName(contact.getName());
			projectDes.setContactId(contact.getId());
			projectDes.setContactPhone(contact.getTelephone());
		}
		
		return projectDes;
	}
	
	
	//获得所有project
	public List<Project>  getAllPojrect(){
		String hql="from Tproject";
		List<Tproject> tList=projectDao.find(hql);
		List<Project> pList=new ArrayList<Project>();
		for(Tproject t:tList){
			pList.add(tproject2project(t));
		}
		return pList;
	}
	
	//下载exlce including all project
	public String downloadeExcelOfAll() throws IOException{
		String fileName=UUID.randomUUID().toString()+".xlsx";
		String fileAbsPath=Web.WEB_ABS_PATH+"/web-inf/download/"+fileName;
		logger.debug(fileAbsPath);
		ExcelUtils.list2Excle(getAllPojrect(), fileAbsPath);
		
		return fileAbsPath;
	}

	
	//acrrording ids,create file of project 
	public String downloadeExcelChecked(String ids) throws IOException {
		// TODO Auto-generated method stub
		String[] idArray=ids.split(",");
		if(idArray==null || idArray.length==0){
			throw new RuntimeException("ids is error");
		}
		
		List<Long> idList=new ArrayList<Long>();
		for(String s:idArray){
			Long id=Long.parseLong(s);
			idList.add(id);
		}
		
		List<Project> projectList=new ArrayList<Project>();
		for(Long id:idList){
			Tproject tproject=projectDao.get(Tproject.class, id);
			Project project=tproject2project(tproject);
			projectList.add(project);
		}
		
		String fileName=UUID.randomUUID().toString()+".xlsx";
		String fileAbsPath=Web.WEB_DOWNLOAD_PATH+fileName;
		logger.debug(fileAbsPath);
		ExcelUtils.list2Excle(projectList, fileAbsPath);
		
		return fileAbsPath;
	}

	//create excel file according filter condition
	public String downloadExcelFiltered(List<FilterCondition> conditionList) throws IOException{
		String conditionHql=FilterHqlFactory.buildHqlByList(conditionList);
		System.out.println(conditionHql);
		String hql = "select distinct project from Tproject project left join fetch project.designerSet designer left join fetch project.departmentSet department where 1=1";//查询list
		if(conditionHql!=null  &&  !conditionHql.equals("")){
			hql=hql+" and "+conditionHql;
		}
		List<Tproject> tList=projectDao.find(hql);
		List<Project> pList=tListConvertProjectList(tList);
		
		String fileName=UUID.randomUUID().toString()+".xlsx";
		String fileAbsPath=Web.WEB_DOWNLOAD_PATH+fileName;
		logger.debug(fileAbsPath);
		ExcelUtils.list2Excle(pList, fileAbsPath);
		
		return fileAbsPath;
		
	}
	
	public List<Project> tListConvertProjectList(List<Tproject> tList){
		List<Project> pList=new ArrayList<Project>(0);
		if(pList!=null){
			for(Tproject p:tList){
				Project project=tproject2project(p);
				pList.add(project);
			}
		}
		return pList;
	}
	
	
	/** 创建project docx 下载中的 保存table信息的txt文件
	 * @param list
	 * @param txtFile
	 */
	public boolean createDocxTableTxt(List<Project> list,File txtFile){
		StringBuffer sb=new StringBuffer();
		int i=1;
		for(Project p:list){
			sb.append(i+"\t");
			i++;
			sb.append(p.getProjectName()+"\t");
			
			Date beginDate=p.getBeginDate();
			if(beginDate==null){
				sb.append("null\t");//null + tab
			}else {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				sb.append(sdf.format(beginDate)+"年\t");
			}
			
			
			
			if(p.getInvestAmount()!=null){
				/*investAmount=investAmount/10000;*/
				Float investAmount=p.getInvestAmount();
				DecimalFormat df = new DecimalFormat("###0.00");
				sb.append(df.format(investAmount)+"万\t");
			}else{
				sb.append("null\tt");
			}
			
			if(p.getPartA()==null){
				sb.append("null\t");
			}else {
				sb.append(p.getPartA()+"\t");
			}
				
			if(p.getContactName()==null){
				sb.append("null\t");
			}else {
				sb.append(p.getContactName()+"\t");
			}
			
			if(p.getContactName()==null){
				sb.append("null\t");
			}else {
				sb.append(p.getContactPhone()+"\t");
			}
			
			sb.append("\n");
		}
		return FileUtils.string2File(sb.toString(), txtFile,"UTF-16LE");
	}
	
	/** 创建project docx 下载中的 保存picture信息的txt文件
	 *  txt:   联通WCDMA项目-314992	pic/1.jpg	pic/2.jpg	pic/3.jpg
	 * @param list
	 * @param txtFilePath 
	 * @param 照片文件临时目录 
	 */
	public boolean createDocxPicTxt(List<Tproject> list,File txtFile,String picPath){
		StringBuffer sb=new StringBuffer();
		int i=1;
		for(Tproject p:list){
			sb.append(p.getProjectName()+"-"+p.getDesignCode()+"\t");//保存 项目信息
			
			for(TprojectPhoto photo:p.getProjectPhotoSet()){
				sb.append(picPath+"/"+photo.getFileName()+"\t");//保存路径
			}
			
			sb.append("\n");
		}
			
		return FileUtils.string2File(sb.toString(), txtFile,"UTF-16LE");
	}
	
	
	public  String createTempDirAndZipForDocxDownload(List<Tproject> projectList){
		String absPath=null;
		//String tempDir=UUID.randomUUID().toString();
		
		//创建临时目录  /pic /txt
		String tempDirAbsPathString=Web.WEB_DOWNLOAD_PATH+"/"+UUID.randomUUID().toString();
		String txtAbsPathString=tempDirAbsPathString+"/txt";//临时目录  存储txt文件 绝对 目录
		String picAbsPathString=tempDirAbsPathString+"/pic";//临时目录 下存储照片  绝对 目录
		
		String txtRelPathString="/txt";//临时目录 存储txt文件  相对 目录
		String picRelPathString="/pic";//临时目录 下存储照片   相对 目录
		
		File tempDir=new File(tempDirAbsPathString);
		File picDir=new File(picAbsPathString);
		File txtDir=new File(txtAbsPathString);
		File zipFile=new File(tempDirAbsPathString+".zip");
		//压缩文件已存在
		if(zipFile.exists()){
			logger.debug("生成docx时，压缩包文件 已存在");
			throw new RuntimeException("生成docx时，创建临时目录 已存在");
		}
		
		if(tempDir.exists()){
			logger.debug("生成docx时，创建临时目录 已存在");
			throw new RuntimeException("生成docx时，创建临时目录 已存在");
		}else {
			if(!tempDir.mkdirs() || !picDir.mkdir() || !txtDir.mkdir())
			{
				logger.debug("生成docx时，创建临时目录 失败");
				throw new RuntimeException("生成docx时，创建临时目录 失败");
			}
		}
		
		//复制照片文件到临时目录
		
		for(Tproject p:projectList){
			Set<TprojectPhoto> photoSet=p.getProjectPhotoSet();
			for(TprojectPhoto photo:photoSet){
				String photoSrc=Web.WEB_ABS_PATH+photo.getWebPath();
				String photoDes=picAbsPathString+"/"+photo.getFileName();
				FileUtils.copyFile(photoSrc, photoDes, false);
			}
		}
		
		
		
		//生成 保存 照片信息的 txt文件
		File picFile=new File(txtDir+"/pic.txt");
		createDocxPicTxt(projectList, picFile, picRelPathString);
		
		//生成 保存 table tr 的txt
		File trFile=new File(txtDir+"/tr.txt");
		List<Project> pList=tListConvertProjectList(projectList);
		createDocxTableTxt(pList, trFile);
		
		//复制模板文件到临时目录
		File templeDocxFile=new File(Web.WEB_ABS_PATH+"/resource/template/projectDoc/项目模板.docm");
		File copyFile=new File(tempDirAbsPathString+"/项目模板.docm");
		FileUtils.copyFile(templeDocxFile.getAbsolutePath(), copyFile.getAbsolutePath(), false);
		
		//压缩文件
		ZipCompressor zipCompressor=new ZipCompressor(tempDirAbsPathString+".zip");
		zipCompressor.compressExe(tempDirAbsPathString);
		
		
		return tempDirAbsPathString+".zip";
	}

	public String downloadeDocxOfAll() {
		// TODO Auto-generated method stub
		String hqlString="from Tproject";
		List<Tproject> tpList=projectDao.find(hqlString);
		String fileAbsPath=createTempDirAndZipForDocxDownload(tpList);
		return fileAbsPath;
	}

	public String downloadDocxChecked(String ids) {
		// TODO Auto-generated method stub
		String[] idArray=ids.split(",");
		if(idArray==null || idArray.length==0){
			throw new RuntimeException("ids is error");
		}
		
		List<Long> idList=new ArrayList<Long>();
		for(String s:idArray){
			Long id=Long.parseLong(s);
			idList.add(id);
		}
		
		List<Tproject> projectList=new ArrayList<Tproject>();
		for(Long id:idList){
			Tproject tproject=projectDao.get(Tproject.class, id);
			projectList.add(tproject);
		}
		String fileAbsPath=createTempDirAndZipForDocxDownload(projectList);
		return fileAbsPath;
	}

	public String downloadDocxFiltered(List<FilterCondition> conditionList) {
		// TODO Auto-generated method stub
		String conditionHql=FilterHqlFactory.buildHqlByList(conditionList);
		System.out.println(conditionHql);
		String hql = "select distinct project from Tproject project left join fetch project.designerSet designer left join fetch project.departmentSet department where 1=1";//查询list
		if(conditionHql!=null  &&  !conditionHql.equals("")){
			hql=hql+" and "+conditionHql;
		}
		List<Tproject> tList=projectDao.find(hql);
		String fileAbsPath=createTempDirAndZipForDocxDownload(tList);
		return fileAbsPath;
	}
	
}
