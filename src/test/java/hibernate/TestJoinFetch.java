package hibernate;

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
import toubiao.model.TprojectPhoto;
import toubiao.pageModel.Photo;
import toubiao.pageModel.ProjectPhoto;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestJoinFetch {

	@Autowired
	private ProjectDaoI projectDao;
	
	@Test
	public void testJoinFetch(){
		//left join
		//String hql="select  p from Tproject p left join fetch p.designerSet left join fetch p.contact left join fetch p.contractSet left join fetch p.departmentSet left join fetch p.projectPhotoList where p.id=1";
		
		//inner join
		//String hql="select  p from Tproject p inner join fetch p.designerSet  inner join fetch p.departmentSet inner join fetch p.projectPhotoList photo where p.id=1 group by photo";
		
		//String hql="select  p from Tproject p left join fetch p.designerSet left join fetch p.contact left join fetch p.contractSet left join fetch p.departmentSet left join fetch p.projectPhotoList where p.id=1";
		
		//
		String hql="select distinct p from Tproject p "
				+ " left join fetch p.designerSet designer"
				+ " left join fetch p.contact contact"
				+ " left join fetch p.contractSet contract"
				+ " left join fetch p.departmentSet department"
				+ " left join fetch p.projectPhotoList photo"
				+ " where p.id=1"
				+ " group by designer,contact,contract,department,photo";
		
		List<Tproject> list=projectDao.find(hql);
		System.out.println("list.size="+list.size());
		int i=1;
		for(Tproject p:list){
			
			System.out.println("**************   "+i+"   *************");
			System.out.println("designerSet.size="+p.getDesignerSet().size());
			System.out.println("departmentSet.size="+p.getDepartmentSet().size());
			System.out.println("photoList.size="+p.getProjectPhotoSet().size());
			System.out.println("");
			i++;
		}
	}
	
	@Test
	public void testLazy(){
		String hql="select distinct p from Tproject p  where p.id=1";
		List<Tproject> list=projectDao.find(hql);
		System.out.println("list.size="+list.size());
		int i=1;
		for(Tproject p:list){
			
			System.out.println("**************   "+i+"   *************");
			System.out.println("designerSet.size="+p.getDesignerSet().size());
			System.out.println("departmentSet.size="+p.getDepartmentSet().size());
			System.out.println("photoList.size="+p.getProjectPhotoSet().size());
			System.out.println("");
			i++;
		}
	}
	
	@Test
	public void testOrderBy(){
		String hql="select distinct p from Tproject p  where p.id=1";
		List<Tproject> list=projectDao.find(hql);
		System.out.println("list.size="+list.size());
		int i=1;
		for(Tproject p:list){
			
			System.out.println("**************   "+i+"   *************");
			System.out.println("designerSet.size="+p.getDesignerSet().size());
			System.out.println("departmentSet.size="+p.getDepartmentSet().size());
			System.out.println("photoList.size="+p.getProjectPhotoSet().size());
			for(TprojectPhoto photo:p.getProjectPhotoSet()){
				System.out.println(photo.getTitle());
				System.out.println(photo.hashCode());
				
			}
			System.out.println("");
			i++;
		}
	}
}
