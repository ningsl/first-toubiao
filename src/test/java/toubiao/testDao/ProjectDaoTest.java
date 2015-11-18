package toubiao.testDao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.dao.ProjectDaoI;
import toubiao.model.Tproject;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ProjectDaoTest {
	@Autowired
	private ProjectDaoI projectDao;
	
	@Test
	public void testHql(){
		
		String hqlString="From Tproject p where p.investAmount > 1000";
		List<Tproject> list=projectDao.find(hqlString);
		System.out.println(list.size());
		hqlString="From Tproject p where p.partA = '河南联通'";
		list=projectDao.find(hqlString);
		System.out.println(list.size());
		
	}
	
	@Test
	public void testJoinFetch(){
		String hqlString="select distinct project from Tproject project left join fetch project.departmentSet department"
				+ " where department.name like '%一分%' ";
		List<Tproject> list=projectDao.find(hqlString);
		for(Tproject project:list){
				Long id=(Long) project.getId();
				projectDao.getCurrentSession().evict(project);
				Tproject p=projectDao.get(Tproject.class, id);
				/*System.out.println(project.getDepartmentSet().size());*/
				int size=p.getDepartmentSet().size();
				//size=project.getDepartmentSet().size();
				if(size>1)
					System.out.println(size);

		}
		
	}
	
	@Test
	public void testGet(){
		Tproject project=projectDao.get(Tproject.class, 8L);
		System.out.println(project.getDepartmentSet().size());

		
	}
	
}
