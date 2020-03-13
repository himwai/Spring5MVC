package com.cityray.bootstrap;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebStartup 
	implements WebApplicationInitializer 
{

	private final Logger log = LoggerFactory.getLogger(WebStartup.class);
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.info("Starting WebInitialization in {}", servletContext.getContextPath());
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.setServletContext(servletContext);
		webContext.register(WebConfig.class);
		
		webContext.refresh();
		
		DispatcherServlet servlet = new DispatcherServlet(webContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("springDispatcher", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        
	}
	
}
