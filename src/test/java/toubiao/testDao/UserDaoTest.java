package toubiao.testDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import toubiao.dao.UserDaoI;
import toubiao.dao.impl.ResourceDaoImpl;
import toubiao.dao.impl.ResourceTypeDaoImpl;
import toubiao.model.Tuser;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserDaoTest {
	
	static Logger logger = LogManager.getLogger(UserDaoTest.class.getName());
	
	@Autowired	
	private	UserDaoI userDao;
	
	
	@Test
	public void find(){
		String q="a";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "%%" + q.trim() + "%%");
		List<Tuser> tl = userDao.find("from Tuser t where t.name like :name order by name", params, 1, 10);
		for(Object object:tl){
			Tuser uTuser=(Tuser) object;
			logger.info(uTuser.getId());
		}
		
		
		String hql="from Tuser user where user.name like '%宁%'";
		logger.info(userDao.find(hql).size());
		
	}
}
