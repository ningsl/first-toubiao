package spring.beanuitls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import toubiao.dao.ProjectDaoI;
import toubiao.model.Tdesigner;
import toubiao.model.Tproject;
import toubiao.pageModel.Designer;
import toubiao.pageModel.Project;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CopyProperties {

	@Autowired
	private ProjectDaoI projectDao;
	
	@Test
	public void copyPropertiesTest(){
		
		String hql="from Tproject";
		List tpList=projectDao.find(hql);
		String[] igorFields={"designerSet","projectSet"};
		List<Project> projectList=new ArrayList<Project>();
		
		for(Object e:tpList){
			Tproject tp=(Tproject)e;
			Project project=new Project();
			
			BeanUtils.copyProperties(tp, project,igorFields);
			
			Set designerSet=tp.getDesignerSet();
			System.out.println(designerSet.size());
			Iterator iterator=designerSet.iterator();
			while (iterator.hasNext()) {
				Object object=iterator.next();
				Tdesigner d=(Tdesigner) object;
				System.out.println(d.getName());
				Designer designer=new Designer();
				BeanUtils.copyProperties(d, designer, igorFields);
			}
			
			projectList.add(project);
		}
		
		for(Project p:projectList){
			System.out.println(p.getProjectName());
		}
		
		String jsonString=JSON.toJSONString(projectList, true);
		System.out.println(jsonString);
	}
}
