package toubiao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 初始化数据库控制器
 * 
 * @author 孙宇
 * 
 */
@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/getPath")
	public String init(HttpSession session) {
		String webAbsPath=session.getServletContext().getRealPath("/");//\\Y:\EclipseProject\RecentlyProject\toubiao\src\main\webapp\
		System.out.println("webAbsPath="+webAbsPath);
		return "redirect:/index.jsp";
	}
}
