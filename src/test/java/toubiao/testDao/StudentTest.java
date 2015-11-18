package toubiao.testDao;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.dao.impl.CourseDaoImpl;
import toubiao.dao.impl.StudentDaoImpl;
import toubiao.model.Course;
import toubiao.model.Student;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class StudentTest {

	@Autowired
	private StudentDaoImpl studentDao;
	@Autowired
	private CourseDaoImpl courseDao;
	
	@Test
	public void findTest() {
		Student s=studentDao.get(Student.class, 1);
		
		Set<Course> courseSet=s.getCourseSet();
		
		for(Course course:courseSet){
			System.out.println(course.getName());
		}
	}
}
