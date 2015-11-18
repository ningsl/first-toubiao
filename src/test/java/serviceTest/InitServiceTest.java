package serviceTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ehcache.search.expression.And;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import toubiao.dao.ResourceDaoI;
import toubiao.dao.ResourceTypeDaoI;
import toubiao.model.Tresource;
import toubiao.model.Tresourcetype;
import toubiao.pageModel.Resource;
import toubiao.service.InitServiceI;
import toubiao.service.ResourceServiceI;
import toubiao.service.impl.InitServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
public class InitServiceTest {
	
	@Autowired
	private ResourceDaoI resourceDao;
	
	@Autowired
	private ResourceTypeDaoI resourceTypeDao;

	@Autowired
	private InitServiceI initService;
	
	@Autowired
	private ResourceServiceI ResourceService;
	
	
	
	
	
	@Test
	public void testInitService(){
		/*initService.initResourceType();
		initService.initResource();
		initService.updateModule();*/
		initService.init();
		//System.out.println(ResourceService.get("zygl").getName());
	}
	 
	
	@Test
	public void showResource(){
		/*Resource r=resourceDao.get(Resource.class, "zygl");*/
		/*System.out.println("========================"+r.getChildrenSet().size());*/
	}
	
	@Test
	public void testInitProjectPhoto(){
		String webAbsPath="Y:\\EclipseProject\\RecentlyProject\\toubiao\\src\\main\\webapp\\";
		initService.initProjectPhoto(webAbsPath);
	}
	
	@Test
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void testCleanAndInit(){
		Tresourcetype menuType = new Tresourcetype();
		menuType.setId("0");
		menuType.setName("菜单");
		resourceTypeDao.save(menuType);// 菜单类型
		
		Tresourcetype funType = new Tresourcetype();
		funType.setId("1");
		funType.setName("菜单");
		resourceTypeDao.save(funType);// 菜单类型
		
		Tresourcetype moduleType = new Tresourcetype();
		moduleType.setId("2");
		moduleType.setName("菜单");
		resourceTypeDao.save(moduleType);// 菜单类型
		
		
		Tresource achievementModule=new Tresource();
		achievementModule.setId("achievement");
		achievementModule.setName("业绩模块");
		achievementModule.setTresourcetype(moduleType);
		achievementModule.setModule(achievementModule);
		resourceDao.saveOrUpdate(achievementModule);
		
		Tresource employeeModule=new Tresource();
		employeeModule.setId("employee");
		employeeModule.setName("人员模块");
		employeeModule.setTresourcetype(moduleType);
		employeeModule.setModule(employeeModule);
		resourceDao.saveOrUpdate(employeeModule);
		
		Tresource systemModule=new Tresource();
		systemModule.setId("system");
		systemModule.setName("系统管理");
		systemModule.setTresourcetype(moduleType);
		systemModule.setModule(systemModule);
		resourceDao.saveOrUpdate(systemModule);
		
		Tresource achievementInfo = new Tresource();
		achievementInfo.setId("achievementInfo");
		achievementInfo.setName("业绩信息");
		achievementInfo.setTresourcetype(menuType);
		achievementInfo.setSeq(0);
		achievementInfo.setIcon("plugin");
		achievementInfo.setModule(achievementModule);
		resourceDao.saveOrUpdate(achievementInfo);
		
		Tresource achievementGropu = new Tresource();
		achievementGropu.setId("achievementGropu");
		achievementGropu.setName("业绩分组");
		achievementGropu.setTresourcetype(menuType);
		achievementGropu.setSeq(0);
		achievementGropu.setIcon("plugin");
		achievementGropu.setModule(achievementModule);
		resourceDao.saveOrUpdate(achievementGropu);
		
		Tresource achievementGropu1 = new Tresource();
		achievementGropu1.setId("achievementGropu1");
		achievementGropu1.setName("自定义分组1");
		achievementGropu1.setTresourcetype(menuType);
		achievementGropu1.setSeq(0);
		achievementGropu1.setIcon("plugin");
		achievementGropu1.setParent(achievementGropu);
		resourceDao.saveOrUpdate(achievementGropu1);
		

		Tresource xtgl = new Tresource();
		xtgl.setId("xtgl");
		xtgl.setName("系统管理");
		xtgl.setTresourcetype(menuType);
		xtgl.setSeq(0);
		xtgl.setIcon("plugin");
		xtgl.setModule(systemModule);
		resourceDao.saveOrUpdate(xtgl);
		
		Tresource zygl = new Tresource();
		zygl.setId("zygl");
		zygl.setName("资源管理");
		zygl.setTresourcetype(menuType);
		zygl.setParent(xtgl);
		zygl.setSeq(1);
		zygl.setUrl("/resourceController/manager");
		zygl.setIcon("database_gear");
		zygl.setRemark("管理系统中所有的菜单或功能");
		resourceDao.saveOrUpdate(zygl);

		Tresource zyglTreeGrid = new Tresource();
		zyglTreeGrid.setId("zyglTreeGrid");
		zyglTreeGrid.setName("资源表格");
		zyglTreeGrid.setTresourcetype(funType);
		zyglTreeGrid.setParent(zygl);
		zyglTreeGrid.setSeq(1);
		zyglTreeGrid.setUrl("/resourceController/treeGrid");
		zyglTreeGrid.setIcon("wrench");
		zyglTreeGrid.setRemark("显示资源列表");
		resourceDao.saveOrUpdate(zyglTreeGrid);

		Tresource zyglMenu = new Tresource();
		zyglMenu.setId("zyglMenu");
		zyglMenu.setName("功能菜单");
		zyglMenu.setTresourcetype(funType);
		zyglMenu.setParent(zygl);
		zyglMenu.setSeq(2);
		zyglMenu.setUrl("/resourceController/tree");
		zyglMenu.setIcon("wrench");
		resourceDao.saveOrUpdate(zyglMenu);

		Tresource zyglAddPage = new Tresource();
		zyglAddPage.setId("zyglAddPage");
		zyglAddPage.setName("添加资源页面");
		zyglAddPage.setTresourcetype(funType);
		zyglAddPage.setParent(zygl);
		zyglAddPage.setSeq(3);
		zyglAddPage.setUrl("/resourceController/addPage");
		zyglAddPage.setIcon("wrench");
		resourceDao.saveOrUpdate(zyglAddPage);
		
		
		System.out.println("****************"+xtgl.getChildrenSet().size()+"********************");
	
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private void updateResourceModule(Tresource parent){
		
		Tresource  moduleTresource=parent.getModule();
		
		if(parent.getChildrenSet().size()>0 ){
			for(Object o:parent.getChildrenSet()){
				Tresource resource=(Tresource) o;
				resource.setModule(moduleTresource);
				resourceDao.saveOrUpdate(resource);
				updateResourceModule(resource);
			}
		}
	}
	
	@Test
	@Transactional
	public void updateModule() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resourceTypeId", "0");//菜单类型的资源
		/*if (sessionInfo != null) {
			params.put("userId", sessionInfo.getId());// 自查自己有权限的资源
			l = resourceDao.find("select distinct t from Tresource t join fetch t.tresourcetype type join fetch t.troles role join role.tusers user where type.id = :resourceTypeId and user.id = :userId order by t.seq", params);
		} else {
			l = resourceDao.find("select distinct t from Tresource t join fetch t.tresourcetype type where type.id = :resourceTypeId order by t.seq", params);
		}*/
		
		String hql="select t from Tresource t  join fetch t.tresourcetype type join fetch t.childrenSet where type.id=:resourceTypeId and t.parent is null ";
		List rList=resourceDao.find(hql,params);
		for(Object e:rList){
			Tresource r=(Tresource) e;
			System.out.println(r.getModule().getName());
			System.out.println(r.getChildrenSet().size());
			
			
			updateResourceModule(r);	
		}
		System.out.println(rList.size());
	}
}