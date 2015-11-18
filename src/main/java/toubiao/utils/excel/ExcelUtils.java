package toubiao.utils.excel;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nsl.utils.BeanUtils;





















import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




















import toubiao.constant.Dictionary;
import toubiao.model.Tproject;
import toubiao.pageModel.Project;

public class ExcelUtils {

	//保存conver信息 <类名,类中fields的信息>
	static private Map<String, List<FieldInfo>>fieldInfoMap=createfieldInfoMap();
	
	static private Map<String, List<FieldInfo>> createfieldInfoMap(){
		Map<String, List<FieldInfo>>convertMap=new HashMap<String, List<FieldInfo>>();
		
		String rootPath=Dictionary.class.getResource("/").getPath();
		rootPath=rootPath.substring(1,rootPath.length());
		System.out.println(rootPath);
		SAXReader saxReader = new SAXReader();
		System.out.println(new File("/").getAbsolutePath());
		
	    Document document=null;
		try {
			document = saxReader.read(new File(rootPath+"excel-field-info.xml"));
		/*	document = saxReader.read(configXmlFile);*/
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	 // 获取根元素
	    Element root = document.getRootElement();
	    // 获取特定名称的子元素
	    List<Element> classElements = root.elements("class");
	    System.out.println("hello child: " + classElements.size());
	    
	    for(Element e:classElements){
	    	String className=e.attributeValue("name");
	    	List<Element> fieldElements=e.elements("field");
	    	List<FieldInfo> fieldList=new ArrayList<>();
	    	for(Element field:fieldElements){
	    		String fieldName=field.attributeValue("name");
	    		Element column=field.element("column");
	    		String clolumName=column.attributeValue("name");
	    		int seq=Integer.parseInt(column.attributeValue("seq"));
	    		
	    		FieldInfo fieldInfo=new FieldInfo();
	    		fieldInfo.setCalssName(className);
	    		fieldInfo.setColumnName(clolumName);
	    		fieldInfo.setFieldName(fieldName);
	    		fieldInfo.setSeq(seq);
	    		
	    		fieldList.add(fieldInfo);
	    	}
	    	Collections.sort(fieldList);
	    	convertMap.put(className, fieldList);
	    }
	    
		return convertMap;
	}
	
	public static Map<String, List<FieldInfo>> getConvertMap(){
		return fieldInfoMap;
	}
	
	public static void list2Excle(List<Project> modelList,String saveAbsPath) throws IOException{
		String className="project";
		List<FieldInfo> infoList=fieldInfoMap.get(className);
		
		Workbook   workbook = new XSSFWorkbook();
		Sheet sheet1=workbook.createSheet("sheet");
		
		//获得列数
		int columnNumber=infoList.size();
		
	   	//设置字体
        Font font =workbook.createFont();
        font.setFontHeightInPoints((short) 10); //字体高度
        font.setColor(XSSFFont.DEFAULT_FONT_COLOR); //字体颜色
        font.setFontName("宋体"); //字体
        font.setBoldweight(XSSFFont.DEFAULT_FONT_SIZE); //宽度
        font.setItalic(false); //是否使用斜体
//      font.setStrikeout(true); //是否使用划线

       //设置单元格类型
        CellStyle cellStyle =workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); //水平布局：居中
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
 
		
		//写表头
		 // Create a row and put some cells in it. Rows are 0 based.
	    Row row=sheet1.createRow(0);
	    // Create a cell and put a date value in it.  The first cell is not styled
	    // as a date.
	    int i=0;
	    Cell headCell = row.createCell(i);
	    headCell.setCellValue("序号");
	    headCell.setCellStyle(cellStyle);
	    i++;
	    for(FieldInfo field:infoList){
	    	headCell= row.createCell(i++);
	    	headCell.setCellValue(field.getColumnName());
	    	headCell.setCellStyle(cellStyle);
	    }
		
	    //保存List
	    int rowIndex=1;
	    for(Project project:modelList){
	    	 Cell bodyCell;
	    	 Row r = sheet1.createRow(rowIndex);
	    	 i=0;
	    	 bodyCell= r.createCell(i++);
	    	 bodyCell.setCellValue(rowIndex++);
	    	 bodyCell.setCellStyle(cellStyle);
	    	 for(FieldInfo field:infoList){
		    	String fieldName=field.getFieldName();
		    	Object cellObject=BeanUtils.getFieldValueByName(fieldName, project);
		    	String cellValue="";
		    	
		    	if(cellObject!=null){
		    		if(fieldName.equalsIgnoreCase("begindate")){
		    			Date date=(Date) cellObject;
		    			cellValue=(date.getYear()+1900)+"年";
			    	}else {
			    		cellValue=cellObject+"";
					}
				}
		    	bodyCell= r.createCell(i++);
		    	bodyCell.setCellValue(cellValue);
		    	bodyCell.setCellStyle(cellStyle);
	    	}	
	    }
	    
	    //设置列宽
	    for(int j=0;j<columnNumber+1;j++){
	    	if(j==1){
	    		sheet1.setColumnWidth(j, 9000);//项目名称
	    		continue;
	    	}
	    	if(j==4){
	    		sheet1.setColumnWidth(j, 5000);//委托单位
	    		continue;
	    	}
	    	sheet1.autoSizeColumn(j);
	    }
	    	
	    
	    //保存文件
	    FileOutputStream saveFile=new FileOutputStream(new File(saveAbsPath));
	    
	    
	    workbook.write(saveFile);
	    saveFile.close();
		
		//
		
	}
	
}
