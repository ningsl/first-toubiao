package toubiao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import nsl.spring.utils.RequestAndSession;

import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import toubiao.pageModel.SessionInfo;

/**
 * 登陆
 * @author nsl
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/test")
	public String test(HttpSession session) {
		//HttpSession session=RequestAndSession.getSession();
		SessionInfo sessionInfo=new SessionInfo();
		sessionInfo.setName("test");
		List<String> resourceList=new ArrayList<String>();
		resourceList.add("/projectController/projectDetail");
		resourceList.add("/projectController/delete");
		
		
		sessionInfo.setResourceList(resourceList);
		sessionInfo.setId("testId");
		session.setAttribute("sessionInfo", sessionInfo);
		session.setAttribute("test", "aaa");
		return "/index";
	}
	
	@RequestMapping("/test2")
	public String test2(HttpSession session) {
		//HttpSession session=RequestAndSession.getSession();
		SessionInfo sessionInfo=new SessionInfo();
		sessionInfo.setName("test");
		List<String> resourceList=new ArrayList<String>();
		resourceList.add("/projectController/projectDetail");
		sessionInfo.setResourceList(resourceList);
		sessionInfo.setId("testId");
		session.setAttribute("sessionInfo", sessionInfo);
		session.setAttribute("test", "aaa");
		return "/project/project2";
	}
}
