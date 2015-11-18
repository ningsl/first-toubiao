package spring.test;

import java.util.Map;






import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import toubiao.model.Tresource;
import toubiao.pageModel.Tree;
import nsl.spring.utils.CustomizedPropertyConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-hibernate.xml"})  
public class PropTest {
	
	@Test
	public void showPropTest(){
		Map<String, String> map=CustomizedPropertyConfigurer.getContextMap();
		System.out.println(map.get("fileKey"));
	}
	
	@Test
	public void beanUtilsTest(){
		Tresource r=new Tresource();
		r.setId("a");
		
		Tree tree=new Tree();
		
		BeanUtils.copyProperties(r, tree);
	}
	
	
}
