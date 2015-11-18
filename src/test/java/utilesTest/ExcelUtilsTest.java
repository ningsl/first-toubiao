package utilesTest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.dao.ProjectDaoI;
import toubiao.model.Tproject;
import toubiao.pageModel.Project;
import toubiao.service.impl.ProjectServiceImpl;
import toubiao.utils.excel.FieldInfo;
import toubiao.utils.excel.ExcelUtils;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ExcelUtilsTest {
	
	@Autowired
	private ProjectServiceImpl projectService;
	
	@Test
	public void getFiledMapTest(){
		Map<String, List<FieldInfo>> convertMap=ExcelUtils.getConvertMap();
		System.out.println(convertMap.size());
		List<FieldInfo> projectFields=convertMap.get("project");
		for(FieldInfo field:projectFields){
			System.out.println(field.getSeq());
		}
	}
	
	@Test
	public void list2File() throws IOException{
		DateFormat format=new SimpleDateFormat("yyyy-MM-ddhh-mm-ss");
		String fileName=format.format(new Date())+".xlsx";
		String saveAbsPath="e:/"+fileName;
		List<Project> projectList=projectService.getAllPojrect();
		ExcelUtils.list2Excle(projectList, saveAbsPath);
	}
}
