package toubiao.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartupListener implements ApplicationListener<ApplicationContextEvent>{

	@Override
	public void onApplicationEvent(ApplicationContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("************初始化容器****************8");
	}


}
