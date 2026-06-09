package com.traneco.common.interceptor;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.traneco.login.model.CustomUser;
import com.traneco.login.model.Permission;
import com.traneco.login.model.Role;

/**
 * @author mithape
 * @version 1.0
 * @purpose This class is used to verify all request URL action to allow user
 *          authorized or not.
 * 
 * @History ===============================================================================================================================================
 * @Version @Date @Author @Purpose
 *          ===============================================================================================================================================
 *          1.0 15-01-18 Mayur I This class is used to verify all request URL
 *          action to allow user authorized or not.
 *          ===============================================================================================================================================
 *
 */

@ControllerAdvice
public class RequestHandlerInterceptor extends HandlerInterceptorAdapter implements HandlerAdapter 
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception 
	 {
		boolean flag = false;
		CustomUser customUser = null;
		String actionName[] = null;
		HttpSession session = null;
		session = request.getSession();
		if (session == null) 
		{
			modelAndView.setViewName("sessionExpire");
		}
		else 
		{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!request.getRequestURI().contains("logout") && !request.getRequestURI().contains("login")
					&& !request.getRequestURI().contains("home")) 
			 {
				if ("anonymousUser".equalsIgnoreCase(String.valueOf(authentication.getPrincipal()))) 
				{
					modelAndView.setViewName("login");
				}
				else 
				{
					customUser = (CustomUser) authentication.getPrincipal();
					if (customUser != null) 
					{
						actionName = request.getRequestURI().split("\\/");
						if (customUser.isLoginFlag()) 
						{
							Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
							for (GrantedAuthority a : authorities) 
							{
								Role role = (Role) a;
								if (actionName != null)
									if (actionName.length > 2)
										flag = isValidAction(role.getPermissions(),
												actionName.length < 1 ? "" : actionName[2]);
								if (!flag) 
								{
									modelAndView.setViewName("403");
								}
							}
						} 
						else 
						{
							modelAndView.setViewName("login");
						}
					}
				}
			}
		}
	}

	private boolean isValidAction(List<Permission> roles, String actionName) {
		boolean permissionFlag = false;
		for (Permission permission : roles) {
			if (permission.getName().equals(actionName)) {
				permissionFlag = true;
			}
		}
		return permissionFlag;
	}

	@Override
	public long getLastModified(HttpServletRequest arg0, Object arg1) {
		DispatcherType dispatcherType = arg0.getDispatcherType();
		System.out.println("DispatcherType:::"+dispatcherType);
		return 0;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorTitle", "404 Not Found");
		modelAndView.addObject("errorDesc", "Sorry, an error has occured, Requested page not found!");
		modelAndView.setViewName("404");
		return modelAndView;
	}

	@Override
	public boolean supports(Object arg0) {
		if (arg0 instanceof DefaultServletHttpRequestHandler) {
			return true;
		} else {
			return false;
		}
	}

	@ExceptionHandler(Exception.class)
	public String exceptionError() {
		return "error";
	}

	@ExceptionHandler(IOException.class)
	public String ioException() {
		return "error";
	}

	@ExceptionHandler(IllegalStateException.class)
	public String illegalStateException() {
		return "error";
	}

}
