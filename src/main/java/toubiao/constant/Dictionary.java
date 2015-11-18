package toubiao.constant;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import toubiao.utils.WebClassField;


/** 保存页面Class 对应 后台 class的相关信息
 * @author nsl
 *
 */
public class Dictionary {

	static public  Map<String, Map<String, WebClassField>> WEB_CLASS=getWebClassDictionary();
	
	static{
	}

	private static Map<String, Map<String, WebClassField>> getWebClassDictionary() {
		// TODO Auto-generated method stub
		
		Map<String, Map<String, WebClassField>> dictionary=new HashMap<String, Map<String, WebClassField>>(0);
		Map<String, WebClassField> webClassDictionary=new HashMap<String, WebClassField>();
		
		
		String rootPath=Dictionary.class.getResource("/").getPath();
		rootPath=rootPath.substring(1,rootPath.length());
		System.out.println(rootPath);
		SAXReader saxReader = new SAXReader();
		System.out.println(new File("/").getAbsolutePath());
		
	    Document document=null;
		try {
			document = saxReader.read(new File(rootPath+"project-dictionary.xml"));
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
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
	    	field.setType(e.element("model-field").attributeValue("type"));
	    	
	    	System.out.println(field);
	    	
	    	webClassDictionary.put(field.getWebFieldName(), field);
	    	
	    }
	    dictionary.put("project", webClassDictionary);
		return dictionary;
	}
	
	
	
}
