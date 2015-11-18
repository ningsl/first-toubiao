package toubiao.testDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.dao.PhotoDaoI;
import toubiao.model.Tphoto;
import toubiao.model.TprojectPhoto;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class PhotoDaoTest {

	@Autowired
	private PhotoDaoI photoDao;
	
	@Test
	public void getPhoto(){
		Tphoto photo=photoDao.get(Tphoto.class, 4L);
		System.out.println(photo.getFileAbsPath());
		
		if (photo instanceof TprojectPhoto) {
			System.out.println("photo is type of tprojectPhoto");
			
		}
		TprojectPhoto photo2= (TprojectPhoto)photo;
		System.out.println(photo2.getProject().getProjectName());
	}
	
}
