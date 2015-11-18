package toubiao.utils.docx4j;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import nsl.utils.ClassPathUtils;
import nsl.utils.file.FileUtils;

import org.apache.log4j.Logger;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class XmlFactory {
	
	private static Configuration cfg =new Configuration(Configuration.VERSION_2_3_23);
	
	static {
		Logger logger = Logger.getLogger(XmlFactory.class);
		StringTemplateLoader stringLoader = new StringTemplateLoader();  
		
		//读取各个xml文件
		File projectTrFile= ClassPathUtils.getFileBaseOnClassPath("projectTr.txt");
		String projectTrXml=FileUtils.file2String(projectTrFile);
		
		//添加模板
		 stringLoader.putTemplate("projectTr",projectTrXml); 
		 cfg.setTemplateLoader(stringLoader);
	}
	
	static public String getDocxXml(String templateName,Map parametersMap){
		
			Template template=null;
			try {
				template = cfg.getTemplate(templateName, "utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StringWriter writer = new StringWriter();    
			/*System.out.println(writer.toString());    */
			try {
				template.process(parametersMap, writer);
			} catch (TemplateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String result=writer.toString();
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}
}
