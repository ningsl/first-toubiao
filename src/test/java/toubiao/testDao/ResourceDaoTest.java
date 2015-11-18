package toubiao.testDao;


import java.util.List;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;












import toubiao.dao.ModuleDaoI;
import toubiao.dao.impl.ResourceDaoImpl;
import toubiao.dao.impl.ResourceTypeDaoImpl;
import toubiao.model.Tmodule;
import toubiao.model.Tresource;
import toubiao.model.Tresourcetype;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ResourceDaoTest {
	static Logger logger = LogManager.getLogger(ResourceDaoTest.class.getName());

	@Autowired	
	private	ResourceDaoImpl resourceDao;
	
	@Autowired	
	private	 ResourceTypeDaoImpl resourceTypeDaoImpl;
	
	@Autowired
	private ModuleDaoI moduleDao;
	
	@Test
	public void testFind() {
		String hql="from Tresource";
		List list=resourceDao.find(hql);
		Assert.assertNotNull(list);
		for(Object object:list){
			Tresource resource=(Tresource) object;
			logger.info(resource.getId());
		}
		
		
	}
	
	@Test
	public void testSave(){
		/*Session session=resourceDao.getCurrentSession();
		SessionFactory factory=resourceDao.getSessionFactory();
		if(factory==null){
			System.out.println("null");
		}else {
			System.out.println("ok");
		}*/
		Tresource resource=new Tresource();
		Session session=resourceDao.getCurrentSession();
		
		Assert.assertNotNull(session);  
		
		Tresourcetype menuType=resourceTypeDaoImpl.get(Tresourcetype.class, "0");
		
		
		
		System.out.println("test");
		resource.setTresourcetype(menuType);
		resource.setId("test1");
		resource.setName("testName");
		
		resourceDao.save(resource);
		
	}
	
	@Test
	public void testSaveWithModule(){
		Tresourcetype t = new Tresourcetype();
		t.setId("0");
		t.setName("菜单");
		resourceTypeDaoImpl.saveOrUpdate(t);

		Tresourcetype t2 = new Tresourcetype();
		t2.setId("1");
		t2.setName("功能");
		resourceTypeDaoImpl.saveOrUpdate(t2);
		
		Tresourcetype t3 = new Tresourcetype();
		t3.setId("2");
		t3.setName("模块");
		resourceTypeDaoImpl.saveOrUpdate(t3);
		
		Tresourcetype menuType = resourceTypeDaoImpl.get(Tresourcetype.class, "0");// 菜单类型
		Tresourcetype funType = resourceTypeDaoImpl.get(Tresourcetype.class, "1");// 功能类型
		Tresourcetype moduleType = resourceTypeDaoImpl.get(Tresourcetype.class, "2");// 模块类型
		
		Tresource achievementModule=new Tresource();
		achievementModule.setId("achievement");
		achievementModule.setName("业绩模块");
		achievementModule.setTresourcetype(moduleType);
		resourceDao.saveOrUpdate(achievementModule);
		
		Tresource employeeModule=new Tresource();
		employeeModule.setId("employee");
		employeeModule.setName("人员模块");
		employeeModule.setTresourcetype(moduleType);
		resourceDao.saveOrUpdate(employeeModule);
		
		Tresource systemModule=new Tresource();
		systemModule.setId("system");
		systemModule.setName("系统管理");
		systemModule.setTresourcetype(moduleType);
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
		
	}
	
}
