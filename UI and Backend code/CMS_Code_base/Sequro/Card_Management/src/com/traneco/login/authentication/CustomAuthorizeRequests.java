/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to configure user login details and access URL.
 * 
 *@History
 *===============================================================================================================================================
 *     Version          Date            Author                  Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to configure user login details and access URL.
 *===============================================================================================================================================
 *
 */

package com.traneco.login.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

import com.traneco.common.constants.QueryConstant;
import com.traneco.common.interceptor.CustomLogoutHandler;
import com.traneco.common.interceptor.CustomSuccessHandler;
import com.traneco.config.session.CustomSessionAuthenticationStrategy;
import com.traneco.config.session.CustomSessionExpiredStrategy;
import com.traneco.config.session.CustomSessionInvalidStrategy;
import com.traneco.login.model.Role;
import com.traneco.login.services.CustomUserService;

@Component
public class CustomAuthorizeRequests 
{
	@Autowired
	CustomUserService customUserService;

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	CustomLogoutHandler customLogoutHandler;
	
	//added  -start-
	@Autowired
	CustomSessionExpiredStrategy customSessionExpiredStrategy;
	
	@Autowired
	CustomSessionInvalidStrategy customSessionInvalidStrategy;
	
	@Autowired
	CustomSessionAuthenticationStrategy customSessionAuthenticationStrategy;
	
	@Autowired
	@Qualifier("customSessionRegistryImpl")
	//CustomSessionRegistryImpl sessonRegistryImpl;
	SessionRegistryImpl sessonRegistryImpl;
	//added  -end-
	Role role = null;
	
	//added for cors 
	//@Autowired
	//private CorsConfig corsConfig;
	
	public void configure(HttpSecurity http, DataSource dataSource) throws Exception 
	{
		List<Role> roleList = new ArrayList<Role>();		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);	
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstant.LOAD_ALL_ROLE);		
		for(Map row : rows)
		{
			role = new Role();
			role.setName(String.valueOf(row.get("rolename")));
			role.setAction(String.valueOf(row.get("roleaction1")));
			roleList.add(role);
			role = new Role();
			role.setName(String.valueOf(row.get("rolename")));
			role.setAction(String.valueOf(row.get("roleaction2")));
			roleList.add(role);
		}				
		for(Role list : roleList) 
		{
			http.authorizeRequests().antMatchers("/"+list.getAction()+"/").access("hasRole('"+list.getName()+"')");
		}
		if(roleList.size() == 0) 
		{
			http.authorizeRequests().antMatchers("/default").access("hasRole('default')");
		}
		//http.authorizeRequests()   //For handling cors error
		http.csrf().disable()
		.cors().disable()  //previously before adding cors
		//.cors().configurationSource(corsConfig.corsConfigurationSource())
		//.and()
		
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //options request to check the API header {added by ankit}
		.antMatchers(HttpMethod.POST, "/api/**").permitAll()//permit all for get {added by ankit}
		.antMatchers(HttpMethod.GET, "/api/**").permitAll() //permit all for post {added by ankit}
		.antMatchers(HttpMethod.GET, "/").permitAll() 
		.antMatchers(HttpMethod.GET, "/addUserForm").permitAll()
		.antMatchers(HttpMethod.GET, "/loginForm").permitAll()
		.antMatchers(HttpMethod.GET, "/resources/**").permitAll()
		.antMatchers(HttpMethod.GET, "/loginForm?logout").permitAll()
		.antMatchers(HttpMethod.POST, "/isoTransaction").permitAll()
		.antMatchers(HttpMethod.POST, "/pushTxn").permitAll()
		.antMatchers(HttpMethod.POST, "http://103.11.153.212:8080/Card_Management/pushTxn").permitAll()
		.antMatchers(HttpMethod.POST, "/doTransaction").permitAll()
		.antMatchers(HttpMethod.POST, "/creditcardTxn").permitAll()
		.antMatchers(HttpMethod.POST, "/generateCardEncDecKey").permitAll()
		.antMatchers(HttpMethod.POST, "/getGeneratedKeyValue").permitAll()
		.antMatchers(HttpMethod.POST, "/startLogging").permitAll()
		.antMatchers(HttpMethod.POST, "/startStopLogging").permitAll()
		.antMatchers(HttpMethod.POST, "/stopLogging").permitAll()
		.antMatchers(HttpMethod.POST, "/getCardEncDec").permitAll()
		.antMatchers(HttpMethod.POST, "/addClient").permitAll()
		.antMatchers(HttpMethod.POST, "/cardAccountLinkageForm").permitAll()
		//.antMatchers(HttpMethod.POST, "http://103.11.153.212:8080/Card_Management/doTransaction").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.requiresChannel().antMatchers("/secure/**").requiresSecure()
		.and()
		.formLogin()
		.loginPage("/loginForm")
		.failureUrl("/loginForm")
		.successHandler(customSuccessHandler)
		.usernameParameter("username").passwordParameter("password")
		.and()
		.logout()
		.logoutUrl("/logout")
		.addLogoutHandler(customLogoutHandler)
		.logoutSuccessUrl("/loginForm?logout=true")
		.invalidateHttpSession(true) 
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403")
		.and()
		.csrf()
		.disable()
		.sessionManagement()
		.maximumSessions(2)
		.maxSessionsPreventsLogin(true) //setted false and handling manually
		//.sessionRegistry(sessonRegistryImpl)
		.expiredSessionStrategy(customSessionExpiredStrategy)
		.expiredUrl("/loginForm?expired=true")
		.and()
//		.invalidSessionStrategy(customSessionInvalidStrategy)
//		.invalidSessionUrl("loginForm?expired=true")
		.sessionFixation()
		.migrateSession()
		.sessionCreationPolicy(SessionCreationPolicy.NEVER);
		//.sessionAuthenticationStrategy(customSessionAuthenticationStrategy);
		//.expiredUrl("/loginFrom?expired")
		//.expiredSessionStrategy(customSessionExpiredStrategy);
		
	}
}
