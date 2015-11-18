package serviceTest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.model.Tresource;
import toubiao.pageModel.Resource;
import toubiao.pageModel.SessionInfo;
import toubiao.service.ResourceServiceI;
import toubiao.service.impl.ResourceServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
public class ResourceServiceTest {

	static Logger logger = LogManager.getLogger(ResourceServiceTest.class.getName());
	
	@Autowired
	private ResourceServiceI service;
	
	@Test
	public void getModuleTest(){
		SessionInfo sessionInfo=new SessionInfo();
		sessionInfo.setId("0");
		Resource r=service.get("achievement");
		System.out.println(r.getName());
	}

}
