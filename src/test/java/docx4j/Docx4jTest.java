package docx4j;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import toubiao.pageModel.Project;
import toubiao.service.impl.ProjectServiceImpl;
import toubiao.utils.docx4j.DocxFactory;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
public class Docx4jTest {

	@Autowired
	private ProjectServiceImpl projectService;
	
	@Test
	public void createProjectDoc(){
		List<Project> pList=projectService.getAllPojrect();
		DocxFactory.createProjectDocx(pList);
	}
}
