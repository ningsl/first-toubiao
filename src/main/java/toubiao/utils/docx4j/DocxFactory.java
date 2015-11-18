package toubiao.utils.docx4j;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import nsl.utils.ClassPathUtils;
import nsl.utils.docx4j.DocxUtils;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;






import toubiao.pageModel.Project;

public class DocxFactory {


	public static  void createProjectDocx(List<Project> list){
		WordprocessingMLPackage wordMLPackage=null;
		try {
			File projectDocxFile=ClassPathUtils.getFileBaseOnClassPath("项目模板f3.docx");
			wordMLPackage = WordprocessingMLPackage.load(projectDocxFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//处理表格部分
		List<Tbl> tblList=DocxUtils.getAllTbl(wordMLPackage);
		Tbl tbl=tblList.get(0);//获得docx中项目列表
		List<Object> trList=DocxUtils.getAllElementFromObject(tbl, Tr.class);
		Tr tr1=(Tr) trList.get(1);//获得table第二行，里面包含模板信息，需删除
		/*String xmlString=XmlUtils.marshaltoString(tbl);*/
		/*	System.out.println(string);*/
		/*System.out.println(xmlString);*/
		/*System.out.println(XmlUtils.marshaltoString(tr1));*/
		DocxUtils.remove(tr1.getParent(), tr1);//删除模板行信息
		int i=2;//表格序号
		for(Project p :list){
			/*Map map=new HashMap();
			map.put("i", i);
			map.put("p",p);
			String trXml=XmlFactory.getDocxXml("projectTr", map);
			Tr trNew=null;
			try {
				trNew = (Tr) XmlUtils.unmarshalString(trXml);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tbl.getContent().add(trNew);*/
			DocxUtils.addTrByIndex(tbl, i);
			i++;
		}
		try {
			wordMLPackage.save(new File("/f3.docx"));
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
