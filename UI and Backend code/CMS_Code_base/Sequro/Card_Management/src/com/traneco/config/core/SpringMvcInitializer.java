package com.traneco.config.core;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.traneco.config.AppConfig;

/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to initialized dispatcher servlet and request mapping .
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to initialized dispatcher servlet and request mapping .
 *===============================================================================================================================================
 *
 */

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

	private String TMP_FOLDER = "/tmp"; 
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024; 

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {			
		return null;
	}

	@Override
	protected String[] getServletMappings() {		
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
	    ctx.register(AppConfig.class);
	    ctx.setServletContext(servletContext);
	    Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
	    servlet.addMapping("/");
	    servlet.setLoadOnStartup(1);
		/*
		 * FilterRegistration.Dynamic multipartFilter =
		 * servletContext.addFilter("multipartFilter", MultipartFilter.class);
		 * multipartFilter.addMappingForUrlPatterns(null, true, "/*");
		 */
	    servlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	    servletContext.addFilter("requestContextFilter", new RequestContextFilter() ).addMappingForUrlPatterns(null, false, "/*");
	    servletContext.addListener(new RequestContextListener());
	   
	    
	           
	          MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, 
	            MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
	          servlet.setMultipartConfig(multipartConfigElement);
	    
	}	
	
	
	/*
	 * @Override protected void customizeRegistration(ServletRegistration.Dynamic
	 * registration) {
	 * 
	 * // upload temp file will put here File uploadDirectory = new File("D:\\");
	 * 
	 * // register a MultipartConfigElement MultipartConfigElement
	 * multipartConfigElement = new
	 * MultipartConfigElement(uploadDirectory.getAbsolutePath(), maxUploadSizeInMb,
	 * maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
	 * 
	 * registration.setMultipartConfig(multipartConfigElement);
	 * 
	 * }
	 */
}
