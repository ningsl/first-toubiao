package Xml;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;

import toubiao.utils.WebClassField;

public class ReadXmlTest {
	
	@Test
	public void readXmlDemo() throws DocumentException, URISyntaxException{
		
		String rootPath=ReadXmlTest.class.getResource("/").getPath();
		rootPath=rootPath.substring(1,rootPath.length());
		System.out.println(rootPath);
		SAXReader saxReader = new SAXReader();
		System.out.println(new File("/").getAbsolutePath());
		
        Document document = saxReader.read(new File(rootPath+"project-dictionary.xml"));
        
     // 获取根元素
        Element root = document.getRootElement();
        System.out.println("Root: " + root.getName());

        // 获取所有子元素
        List<Element> childList = root.elements();
        System.out.println("total child count: " + childList.size());

        // 获取特定名称的子元素
        List<Element> childList2 = root.elements("web-class");
        System.out.println("hello child: " + childList2.size());

        // 获取名字为指定名称的第一个子元素
        Element firstWorldElement = root.element("model-class");
        System.out.println(firstWorldElement);
        // 输出其属性
        System.out.println("first World Attr: "
                + firstWorldElement.attribute(0).getName() + "="
                + firstWorldElement.attributeValue("name"));

        System.out.println("迭代输出-----------------------");
        // 迭代输出
        for (Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            System.out.println(e.attributeValue("name"));

        }
	}


@Test
public void readXml2WebFieldClass() throws DocumentException, URISyntaxException{
	
	String rootPath=ReadXmlTest.class.getResource("/").getPath();
	rootPath=rootPath.substring(1,rootPath.length());
	System.out.println(rootPath);
	SAXReader saxReader = new SAXReader();
	System.out.println(new File("/").getAbsolutePath());
	
    Document document = saxReader.read(new File(rootPath+"project-dictionary.xml"));
    
 // 获取根元素
    Element root = document.getRootElement();
    System.out.println("Root: " + root.getName());
    // 获取所有子元素
    List<Element> childList = root.elements();
    System.out.println("total child count: " + childList.size());

    // 获取特定名称的子元素
    List<Element> childList2 = root.elements("web-class");
    System.out.println("hello child: " + childList2.size());

    // 获取名字为指定名称的第一个子元素
    Element element = root.element("web-class");
    
    List list=element.elements("field");
    for(Iterator iter = element.elementIterator(); iter.hasNext();){
    	Element e = (Element) iter.next();
    	WebClassField field=new WebClassField();
    	field.setWebFieldName(e.attributeValue("name"));
    	field.setWebClassName(element.attributeValue("name"));
    	field.setFilterOperator(e.element("model-field").attributeValue("filter-operator"));
    	field.setModelClassName(e.element("model-class").attributeValue("name"));
    	field.setModelFieldName(e.element("model-field").attributeValue("name"));
    	
    	System.out.println(field);
    	
    }
    
    
	}
}
