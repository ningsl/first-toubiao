package poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import toubiao.model.Tproject;
import toubiao.pageModel.Project;
import toubiao.service.impl.ProjectServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
public class CreateWorkBook {

	@Autowired
	private ProjectServiceImpl service;
		
	public void createTest() throws IOException{
		Workbook wb = new XSSFWorkbook();
	    FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
	    Sheet sheet1 = wb.createSheet("new sheet");
	    Sheet sheet2 = wb.createSheet("second sheet");
	    Row headRow = sheet1.createRow((short)0);
	    
	    List<Project> projectList=service.getAllPojrect();
	    
	    
	    wb.write(fileOut);
	    fileOut.close();
	}
}
