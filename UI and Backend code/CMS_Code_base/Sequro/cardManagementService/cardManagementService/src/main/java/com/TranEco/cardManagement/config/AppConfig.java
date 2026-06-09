package com.TranEco.cardManagement.config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.Socket;
import java.util.List;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.TranEco.cardManagement.EmbossingProcess.model.EmbossFile;
import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;
import com.TranEco.cardManagement.scheduler.service.SchedulerService;

/*
import com.TranEco.cardManagement.EmbossingProcess.model.EmbossFile;
import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;
import com.TranEco.cardManagement.scheduler.service.SchedulerService;
*/

import com.TranEco.cardManagement.utils.StringUtil;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
*/

@EnableCaching
@Configuration
//@EnableSwagger2
@EnableScheduling
@ComponentScan(basePackages = "com.TranEco.*")
public class AppConfig {
	private static Logger logger = Logger.getLogger(AppConfig.class.getName());

	@Autowired
	private Environment env;

	@Autowired
	@Lazy
	SchedulerService schedulerService;

	
	/*
	 * @Bean public Docket api() { return new
	 * Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any
	 * ()) .paths(PathSelectors.any()).build(); }
	 */

	@Bean("jsonMessageConverter")
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	@Bean("xmlMessageConverter")
	public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
		return new Jaxb2RootElementHttpMessageConverter();
	}

	/*
	 * @Bean public RequestMappingHandlerAdapter requestMappingHandlerAdapter(
	 * 
	 * @Autowired MappingJackson2HttpMessageConverter jsonMessageConverter,
	 * 
	 * @Autowired Jaxb2RootElementHttpMessageConverter xmlMessageConverter) {
	 * RequestMappingHandlerAdapter request = new RequestMappingHandlerAdapter();
	 * List<HttpMessageConverter<?>> msgList = new ArrayList<>();
	 * msgList.add(jsonMessageConverter); msgList.add(xmlMessageConverter);
	 * request.setMessageConverters(msgList); return request; }
	 */

	@Bean(name = "dataSource")
	public DataSource setUpPool() throws ClassNotFoundException {
		GenericObjectPool gPool = null;
		// Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		gPool = new GenericObjectPool();
		gPool.setMaxActive(10);
		String username = env.getProperty("databaseUsername");
		String password = env.getProperty("databasePassword");
		String ipport = env.getProperty("ipport");
		System.out.println("Card Management service IP::::" + ipport);
		ipport = "11.211.4.6:3306";

		// ConnectionFactory cf = new
		// DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/cardmanagement",
		// "root","SEP@ssw0rd123");
		// ConnectionFactory cf = new
		// DriverManagerConnectionFactory("jdbc:mysql://"+ipport+"/cardmanagement",
		// username,password);
		// System.out.println("CMS service IP"+cf);

		//ConnectionFactory cf = new DriverManagerConnectionFactory("jdbc:mysql://"+ipport+"/cardmanagement","cmsuser","Cmsuser@123");
		ConnectionFactory cf = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/cardmanagement", "root","SEP@ssw0rd123");
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
		return new PoolingDataSource(gPool);
	}

	@Bean(name = "jdbcTemplate")
	@Autowired
	public JdbcTemplate getJdbcTemplate(@Qualifier("dataSource") DataSource ds) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		return jdbcTemplate;
	}

	@Bean
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

	@Bean(name = "hSMSocketConnection")
	@Autowired
	@Lazy
	public Socket getHSMSocketConnection() {
		Socket HSMConnnectionSocket = null;
		try {
			HSMConnnectionSocket = new Socket(env.getProperty("hsmip"), Integer.parseInt(env.getProperty("hsmport")));
			logger.info("Connection HSM: " + HSMConnnectionSocket);
		} catch (Exception e) {
			logger.info("Error HSM Connection: " + StringUtil.getExStackTrace(e));
			return new Socket();
		}
		return HSMConnnectionSocket;
	}

	@Bean(name = "hSMSocketConnectionIn")
	@Autowired
	@Lazy
	public DataInputStream getHSMSocketInputStream(@Autowired Socket hSMSocketConnection) {
		DataInputStream inHSMSocketConnection = null;
		try {
			inHSMSocketConnection = new DataInputStream(new BufferedInputStream(hSMSocketConnection.getInputStream()));
			logger.info("inHSMSocketConnection: " + inHSMSocketConnection);
		} catch (Exception e) {
			logger.info("Error getHSMSocketInputStream: " + StringUtil.getExStackTrace(e));
			return null;
		}
		return inHSMSocketConnection;
	}

	@Bean(name = "hSMSocketConnectionOut")
	@Autowired
	@Lazy
	public DataOutputStream getHSMSocketOutPutStream(@Autowired Socket hSMSocketConnection) {
		DataOutputStream outHSMSocketConnection = null;
		try {
			outHSMSocketConnection = new DataOutputStream(
					new BufferedOutputStream(hSMSocketConnection.getOutputStream()));
			logger.info("outHSMSocketConnection: " + outHSMSocketConnection);
		} catch (Exception e) {
			logger.info("Error getHSMSocketOutPutStream: " + StringUtil.getExStackTrace(e));
			return null;
		}
		return outHSMSocketConnection;
	}

	// emboss File Bean
	@Bean("embossFile")
	public EmbossFile getEmbossFile() 
	{
		EmbossFile embossFile = null;
		// try (BufferedReader br = new BufferedReader(new FileReader("./cms/emboss.xml")))
		// try (BufferedReader br = new BufferedReader(new FileReader("D:\\Sequro\\winservice\\cms\\cms\\emboss.xml")))
		try (BufferedReader br = new BufferedReader(new FileReader("D:\\Sequro\\winservice\\cardManagement\\cms\\emboss.xml")))
		//try (BufferedReader br = new BufferedReader(new FileReader("/usr/local/tomcat/paths/cms/emboss.xml")))
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(EmbossFile.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			embossFile = (EmbossFile) jaxbUnmarshaller.unmarshal(br);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return embossFile;
	}

	@Bean(name = "scheduler")
	public ThreadPoolTaskScheduler poolScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("poolScheduler");
		scheduler.setPoolSize(1);
		scheduler.initialize();
		return scheduler;
	}

	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent() {
	}

	// @EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		List<SchedulerModel> schedulerList = schedulerService.getAllScheduler();
		schedulerService.schedulerTask(schedulerList);
		List<MobileTokenModel> mobileSchedulerList = schedulerService.getAllMobileScheduler();
		schedulerService.schedulerMobileTask(mobileSchedulerList);
	}
	// Loop Connection

	@Bean(name = "txnSocketConnection")
	@Autowired
	public Socket getTxnSocketConnection() {
		Socket txnConnnectionSocket = null;
		try {
			String hostIP = env.getProperty("txnip");
			String hostPort = env.getProperty("txnport");
			// String ip = env.getProperty("ip");
			// String port=env.getProperty("port");
			String ip = "11.211.4.6";
			String port = "3306";
			System.out.println("Creating Socket Connection......");
			// txnConnnectionSocket = new Socket(hostIP, Integer.parseInt(hostPort));
			txnConnnectionSocket = new Socket("localhost", Integer.parseInt("3306"));
			//txnConnnectionSocket = new Socket(ip, Integer.parseInt(port));
			logger.info("Connection Txn: " + txnConnnectionSocket);
		} 
		catch (Exception e) 
		{
			logger.info("Error Txn Connection: " + StringUtil.getExStackTrace(e));
			return null;
		}
		return txnConnnectionSocket;
	}

	@Bean(name = "txnSocketConnectionIn")
	@Autowired
	@Lazy
	public DataInputStream getTxnSocketInputStream(@Autowired Socket txnSocketConnection) 
	{
		DataInputStream inTxnSocketConnection = null;
		try 
		{
			inTxnSocketConnection = new DataInputStream(new BufferedInputStream(txnSocketConnection.getInputStream()));
			logger.info("inTxnSocketConnection: " + inTxnSocketConnection);
		} 
		catch (Exception e) 
		{
			logger.info("Error inTxnSocketConnection: " + StringUtil.getExStackTrace(e));
			return null;
		}
		return inTxnSocketConnection;
	}

	@Bean(name = "txnSocketConnectionOut")
	@Autowired
	@Lazy
	public DataOutputStream getTxnSocketOutPutStream(@Autowired Socket txnSocketConnection) 
	{
		DataOutputStream outTxnSocketConnection = null;
		try 
		{
			outTxnSocketConnection = new DataOutputStream(
					new BufferedOutputStream(txnSocketConnection.getOutputStream()));
			logger.info("outTxnSocketConnection: " + outTxnSocketConnection);
		} 
		catch (Exception e) 
		{
			logger.info("Error outTxnSocketConnection: " + StringUtil.getExStackTrace(e));
			return null;
		}
		return outTxnSocketConnection;
	}
}