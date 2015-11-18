package poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.ast.statement.SQLWithSubqueryClause.Entry;
import com.alibaba.druid.sql.dialect.postgresql.ast.stmt.PGSelectQueryBlock.ForClause;

import toubiao.dao.DepartmentDaoI;
import toubiao.dao.DesignerDaoI;
import toubiao.dao.ProjectDaoI;
import toubiao.model.Tdepartment;
import toubiao.model.Tdesigner;
import toubiao.model.Tproject;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ReadFile2Database {
	
	public static final String B_STRING=getPath();
	
	static private String getPath(){
		return "a";
	}
	
	
	@Autowired
	private ProjectDaoI projectDao;
	
	@Autowired
	private DepartmentDaoI departmentDao;
	
	@Autowired
	private DesignerDaoI designerDao;
	
	@Test
	public void getClassPath(){
		URL url=ReadFile2Database.class.getClassLoader().getResource("");
		
		String pathString=url.getPath();
		pathString=pathString.substring(1, pathString.length()-1);
		File file=new File(pathString);
		pathString=file.getParentFile().getParent();
		url.getFile();
		System.out.println(pathString);
		System.out.println(B_STRING);
		String partPathString="/target/toubiao.war";
		pathString=pathString+partPathString;
		System.out.println(pathString);
		System.out.println(new File(pathString).length());
		
			
	}
	
	@Test
	public void readDesignerFile() {
		/*InputStream inp = new FileInputStream("11-14年项目汇总.xlsx");*/
		try {
			InputStream inp =ReadFile2Database.class.getClassLoader().getResourceAsStream("人员总表(usingInTest).xlsx");
			InputStream excelHead =ReadFile2Database.class.getClassLoader().getResourceAsStream("designer.properties");
			
			Properties prop = new Properties();
			prop.load(excelHead);
			excelHead.close();
			System.out.println(prop.toString());
			Map<Integer,String> fieldMap=new HashMap<Integer,String>();//记录field域对应表中的column
			
			 Workbook wb = WorkbookFactory.create(inp);
			    Sheet sheet = wb.getSheetAt(0);
			    Row row = sheet.getRow(0);
			    for(Cell cell:row){
			    	 CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
			            System.out.print(cellRef.formatAsString());
			            System.out.print(" - ");

			           String cellValue=getValue(cell);
			           System.out.println(cellValue);
			           
			           String fieldName=(String) prop.get(cellValue);
			           if(fieldName!=null){
			        	   fieldMap.put(cell.getColumnIndex(),fieldName);
			        	   System.out.println(fieldName+"="+cell.getColumnIndex());
			           }
			    }
			   System.out.println(sheet.getLastRowNum());     
			   
			   for(int i=1;i<=sheet.getLastRowNum();i++){
				   Row r=sheet.getRow(i);
				   Tdesigner designer=new Tdesigner();
				   Map<Integer, String> map=new HashMap<>();
				   map.put(Cell.CELL_TYPE_NUMERIC, "number");
				   map.put(Cell.CELL_TYPE_STRING, "string");
				   for(Map.Entry<Integer, String> entry:fieldMap.entrySet()){
					   int columnIndex=entry.getKey();
					   Cell cell=r.getCell(columnIndex);
					  
					   if(cell !=null){
						   String cellValue=getValue(cell);
						   if(entry.getValue().equalsIgnoreCase("department")){
							   if(cellValue!=null && !cellValue.equals("")){
								  Tdepartment department= departmentDao.get("from Tdepartment d where d.name like '%"+cellValue+"%'");
								  if(department!=null){
									  designer.setDepartment(department);
								  }
							   }
							   continue;
						   }
						   
						
						   
						   System.out.println(map.get(cell.getCellType())+": "+cellValue);
						   if(cellValue!=null && !cellValue.equals("")){
							   BeanUtils.setProperty(designer,entry.getValue(),cellValue);
						   }
					   }
				   }
				   designerDao.save(designer);
				   inp.close();
			   }
		} catch (Exception e){
			e.printStackTrace();
		}
		  
	}
	

	@Test
	public void readProjectFile() {
		/*InputStream inp = new FileInputStream("11-14年项目汇总.xlsx");*/
		try {
			InputStream inp =ReadFile2Database.class.getClassLoader().getResourceAsStream("11-14年项目汇总.xlsx");
			InputStream excelHead =ReadFile2Database.class.getClassLoader().getResourceAsStream("project.properties");
			Properties prop = new Properties();
			prop.load(excelHead);
			
			System.out.println(prop.toString());
			Map<Integer,String> fieldMap=new HashMap<Integer,String>();//记录field域对应表中的column
			
			 Workbook wb = WorkbookFactory.create(inp);
			    Sheet sheet = wb.getSheetAt(0);
			    Row row = sheet.getRow(0);
			    for(Cell cell:row){
			    	 CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
			            System.out.print(cellRef.formatAsString());
			            System.out.print(" - ");

			           String cellValue=getValue(cell);
			           System.out.println(cellValue);
			           System.out.println();
			           String fieldName=(String) prop.get(cellValue);
			           if(fieldName!=null){
			        	   fieldMap.put(cell.getColumnIndex(),fieldName);
			        	   System.out.println(fieldName+"="+cell.getColumnIndex());
			           }
			    }
			   System.out.println(sheet.getLastRowNum());     
			   
			   for(int i=1;i<=sheet.getLastRowNum();i++){
				   Row r=sheet.getRow(i);
				   Tproject project=new Tproject();
				   for(Map.Entry<Integer, String> entry:fieldMap.entrySet()){
					   int columnIndex=entry.getKey();
					   Cell cell=r.getCell(columnIndex);
					   if(cell !=null){
						   String cellValue=getValue(cell);
						   if(entry.getValue().equalsIgnoreCase("department")){
							   if(cellValue!=null && !cellValue.equals("")){
								  Tdepartment department= departmentDao.get("from Tdepartment d where d.name like '%"+cellValue+"%'");
								  System.out.println(cellValue);
								  if(department!=null)
									  System.out.println(department.getName());
								  if(department!=null){
									  project.getDepartmentSet().add(department);
								  }
							   }
							   continue;
						   }
					   
					   
					   BeanUtils.setProperty(project,entry.getValue(),cellValue);
					   }
				   }
				  projectDao.save(project);
				  /* 
				   for(Cell cell:row){
					   String cellValue=getValue(cell);
					   if(cellValue !=null && !cellValue.trim().equals("")){
						   Tproject p=new Tproject();
						   Set fieldSet=fieldMap.keySet();
						   if(fieldSet!=null){
							   for(Object object:fieldSet){
							   		String field=(String) object;
							   		}
						   	}
					   }
					   	
				   }*/
			   }
		} catch (Exception e){
			e.printStackTrace();
		}
		  
	}
	
	// convert cell value of different type to string 
	private String getValue(Cell cell){
		switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
           /* System.out.println(cell.getRichStringCellValue().getString());*/
            return cell.getRichStringCellValue().getString();
            /*     break;*/
        case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
            	Date date=cell.getDateCellValue();
                return String.valueOf(date);
            } else {
            	double number=cell.getNumericCellValue();
	             return String.valueOf(number);           
            	/*System.out.println(number);*/
            }
       /*     break;*/
        case Cell.CELL_TYPE_BOOLEAN:
           /* System.out.println(cell.getBooleanCellValue());
            break;*/
        	return String.valueOf(cell.getBooleanCellValue());
        case Cell.CELL_TYPE_FORMULA:
          /*  System.out.println(cell.getCellFormula());
            break;*/
        	return String.valueOf(cell.getCellFormula());
        default:
           return "";
		}
	}
	
	@Test
	public void readProp() throws Exception{
		InputStream in=ReadFile2Database.class.getClassLoader().getResourceAsStream("designer.properties");
		/*InputStream in=ReadFile2Database.class.getClassLoader().getResourceAsStream("project.properties");*/
		Properties properties=new Properties();
		properties.load(in);
		Enumeration em=properties.keys();
		while(em.hasMoreElements()){
			System.out.println(em.nextElement());
		}
	}
	
	
	@Test
	public void buildProjectAndDesingerFriendship() throws Exception{
		List<Tproject> pList=projectDao.find("from Tproject");
		List<Tdesigner> dList=designerDao.find("from Tdesigner");
		
		for(int i=0;i<pList.size();i++){
			Tproject p=pList.get(i);
			Tdesigner d1=dList.get(i%5);
			Tdesigner d2=dList.get((i+1)%10);
			Set<Tdesigner> desingers=new HashSet<>();
			desingers.add(d1);
			if(i%25==0){
				desingers.add(d2);
			}
			p.setDesignerSet(desingers);
			
			projectDao.update(p);
		}
		
		System.out.println(pList.size());
		System.out.println(dList.size());
	}
	
	// 部门入库
	@Test
	public void department2Database(){
		String[] departments={"一分","二分","三分","华北","山西","华中","湖北","江苏","云南","西藏","建筑",
							  "网优","软信","招标代理","市场部","生产部","综合部","人力部","财务部",
							  "无线","传输","交换","规划","西南","华南","华东" };
		int i=1;
		for(String name:departments ){
			Tdepartment department=new Tdepartment();
			department.setName(name);
			department.setSeq(i++);
			departmentDao.save(department);
		}
		
	}
	
	//部门和项目关联
	@Test
	public void buildProjectAndDepartmentFriendship(){
		String hql="from Tproject";
		List<Tproject> list=projectDao.find(hql);
		Set<Tdepartment> departmentSet;
		int i=1;
		for(Tproject p:list){
			Long id=(long) (i%10)+1;
			System.out.println(id);
			Tdepartment d=departmentDao.get(Tdepartment.class, id);
			Tdepartment d2=departmentDao.get(Tdepartment.class, id);
			System.out.println(d.getName());
			System.out.println(d2.getName());
			departmentSet=new LinkedHashSet<Tdepartment>();
			departmentSet.add(d);
			if(p.getId()%8==0){
				departmentSet.add(d2);
			}
			p.setDepartmentSet(departmentSet);
			projectDao.saveOrUpdate(p);
			i++;
		}
	}
	
	
}
