/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define application configuration and create class beans.
 * 
 *@History
 *===============================================================================================================================================
 *     Version         Date         	Author                 Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define application configuration and create class beans.
 *===============================================================================================================================================
 *
 */

package com.traneco.config;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traneco.common.interceptor.CustomLogoutHandler;
import com.traneco.config.session.CustomSessionDestroyedListener;
import com.traneco.config.session.CustomSessionExpiredStrategy;
import com.traneco.config.session.CustomSessionInterceptor;
import com.traneco.login.authentication.CustomAuthenticationProvider;
import com.traneco.login.authentication.CustomAuthorizeRequests;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.traneco.*" })
@Import({ SecurityConfig.class })
@PropertySource({"classpath:database.properties","classpath:config.properties"})
public class AppConfig extends WebMvcConfigurerAdapter 
{
	@Autowired
	Environment env;
	
	@Bean(name = "dataSource1")	
	public DataSource setUpPool() throws ClassNotFoundException 
	{
		GenericObjectPool gPool = null;
		Class.forName(env.getProperty("cms.db.DriverClassName"));		
		gPool = new GenericObjectPool();
		gPool.setMaxActive(Integer.parseInt(env.getProperty("cms.db.SetMaxActive")));	
		ConnectionFactory cf = new DriverManagerConnectionFactory(env.getProperty("cms.db.URL"), env.getProperty("cms.db.UserName"), env.getProperty("cms.db.Password"));
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);		
		return new PoolingDataSource(gPool);
	}
	
	@Bean(name = "dataSource2")	
	public DataSource setUpPool1() throws ClassNotFoundException 
	{
		GenericObjectPool gPool = null;
		Class.forName(env.getProperty("cms.db.DriverClassName"));		
		gPool = new GenericObjectPool();
		gPool.setMaxActive(Integer.parseInt(env.getProperty("cms.db.SetMaxActive")));		
		ConnectionFactory cf = new DriverManagerConnectionFactory(env.getProperty("cardmgt.db.URL"), env.getProperty("cms.db.UserName"), env.getProperty("cms.db.Password"));
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);		
		return new PoolingDataSource(gPool);			
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() 
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver1() 
	{
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(100000);
	    return multipartResolver;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() 
	{
	    return new StandardServletMultipartResolver();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/classes/config");
		return messageSource;
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/resources/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer(){
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/resources/**/tiles.xml"});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {	
		registry.addResourceHandler("/resources/**")
		.addResourceLocations("/WEB-INF/resources/");
	}

	@Bean(name="jdbcTemplate")	
	@Autowired
	public JdbcTemplate getJdbcTemplate(@Qualifier("dataSource1") DataSource ds){
		JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
		return jdbcTemplate;
	}
	
	@Bean(name="jdbcCMSTemplate")	
	@Autowired
	public JdbcTemplate getCMSJdbcTemplate(@Qualifier("dataSource2") DataSource ds){
		JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
		return jdbcTemplate;
	}

	/*@Bean
	public CacheManager cacheManager() {		
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
		factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factory.setShared(true);
		return factory;
	}
*/
	@Bean(name="customAuthenticationProvider")
	public CustomAuthenticationProvider customAuthenticationProvider(){
		return new CustomAuthenticationProvider();
	}

	@Bean(name="customAuthorizeRequests")
	public CustomAuthorizeRequests customAuthorizeRequests() {
		return new CustomAuthorizeRequests();
	}

	@Bean("restTemplate")
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(new RequestHandlerInterceptor()); }
	 */
	
	@Bean("mapper")
	public ObjectMapper mapper() 
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
	
	//Added for getting http ip with port START
	public static String getCurrentBaseUrl() 
	{
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = sra.getRequest();
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
	}
	//Added for getting http ip with port End
	
	//added by ankit -start-
		@Bean
		public CustomSessionExpiredStrategy customSessionExpiredStrategy()
		{
		    return new CustomSessionExpiredStrategy();
		    
		}
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		    registry.addInterceptor(new CustomSessionInterceptor()).addPathPatterns("/**")
		    .excludePathPatterns("/Card_Management/api/getCardType",
	                    "/Card_Management/api/assignUnsoldCard",
	                    "/Card_Management/api/getCardNo",
	                    "/Card_Management/api/test",
	                    "/Card_Management/api/getCardDetails",
	                    "/Card_Management/api/updateCardStatusBlock",
	                    "/Card_Management/api/updateCardStatusActive",
	                    "/Card_Management/api/getCMSCardDetails",
	                    "/Card_Management/api/assignUnsoldCard",
	                    "/loginForm",
	                    "/");
		}
		
		@Bean
		public HttpSessionEventPublisher httpSessionEventPublisher() {
		    return new HttpSessionEventPublisher();
		}
		
//		@Bean(name="customSessionRegistryImpl")
//		public CustomSessionRegistryImpl sessionRegistry()
//		{
//		     return new CustomSessionRegistryImpl();
//		}
		
		//@Bean(name="customSessionRegistryImpl") 
		//not using custom Session Registry , using predefined custom registry
		public SessionRegistryImpl sessionRegistry()
		{
		     return new SessionRegistryImpl();
		}
		
		@Bean(name="customLogoutHandler")
		public CustomLogoutHandler customLogoutHandler() {
		    return new CustomLogoutHandler();
		}
		
		//only for testing session creation and destruction
//		@Bean
//		public HttpSessionListener httpSessionListener() {
//		    return new MySessionListener();
//		}
		
		@Bean
		public CustomSessionDestroyedListener customSessionDestroyedListener()
		{
		    return new CustomSessionDestroyedListener(sessionRegistry());
		}
		
		//	@Bean
//		public CustomSessionAuthenticationStrategy CustomSessionAuthenticationStrategy()
//		{
//		    return new CustomSessionAuthenticationStrategy(sessionRegistry());
//		}
	
}