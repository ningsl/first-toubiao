package serviceTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.dao.ProjectDaoI;
import toubiao.model.Tproject;
import toubiao.service.impl.ProjectServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
public class ProjectServiceTest {

	@Autowired
	private ProjectServiceImpl service;
	
	@Autowired
	private ProjectDaoI projectDao;
	
	@Test
	public void testProjectModeDetail(){
		service.getProjectModel();
	}
	
	@Test
	public void DesignCodeTimesTest(){
		String d1="11119.0";
		String d2="SSS";
		
		System.out.println(service.getDesignCodeRepeatTimes(d1));
		System.out.println(service.getDesignCodeRepeatTimes(d2));
	}
	
	@Test
	public void testCreateDocx(){
		String hqlString="from Tproject t where t.classByProfession like '%无线接入%'";
		List<Tproject> projectList=projectDao.find(hqlString);
		String absPath=service.createTempDirAndZipForDocxDownload(projectList);
		System.out.println(absPath);
	}
}
