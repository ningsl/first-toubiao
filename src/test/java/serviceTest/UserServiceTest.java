package serviceTest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.model.Tuser;
import toubiao.pageModel.DataGrid;
import toubiao.pageModel.PageHelper;
import toubiao.pageModel.User;
import toubiao.service.UserServiceI;
import toubiao.testDao.ResourceDaoTest;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
public class UserServiceTest {
	static Logger logger = LogManager.getLogger(UserServiceTest.class.getName());
	
	@Autowired
	private UserServiceI userService;
	
	@Test
	public void loginComboboxTest(){
		String q="a";
		List list= userService.loginCombobox(q);
		for(Object object:list){
			User user=(User) object;
			logger.info(user.getName());
		}
	}
	
	@Test
	public void loginCombogridTest(){
		String q="a";
	
	}
}
