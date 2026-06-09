/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to if user is successfully authenticated than user role base menu mapping set to session.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to if user is successfully authenticated than user role base menu mapping set to session.
 *===============================================================================================================================================
 *
 */

package com.traneco.common.interceptor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.traneco.login.model.CustomUser;
import com.traneco.login.model.Menu;
import com.traneco.login.model.ParentMenu;
import com.traneco.login.model.SubParentMenu;
import com.traneco.login.services.CustomUserServiceImpl;
import com.traneco.user.model.UserBean;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler 
{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private CustomUserServiceImpl userService;

	@Autowired
	Environment env;

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
	{
		if (response.isCommitted())
		{
			return;
		}
		//request.getSession().invalidate();
		HttpSession session = request.getSession();
		CustomUser user = (CustomUser) authentication.getPrincipal();
		session.setAttribute("user", user);
		
		//Added by prashant Tayde for Reset the passsword
		UserBean userBean = new UserBean();
		userBean.setStrUserName(user.getUsername());
		userBean = userService.getUserDetails(userBean.getStrUserName());
		
		if("N".equalsIgnoreCase(userBean.getIsPasswordReset())) 
		{
			request.getSession().setMaxInactiveInterval(10 * 60); //10 minutes
			redirectStrategy.sendRedirect(request, response, "/resetPassword");
		}
		else
		{
			if (!env.getProperty("imps.superadmin.user").equals(user.getUsername())) 
			{
				List<Menu> menuList = userService.menuList(Integer.parseInt(user.getGroupID()), Integer.parseInt(user.getParticipantid()));
				session.setAttribute("menuList", menuList);
				
				List<Menu> menuidList = userService.menuidList(Integer.parseInt(user.getGroupID()),	Integer.parseInt(user.getParticipantid()));
				session.setAttribute("menuidList", menuidList);
				
				List<SubParentMenu> subParentMenuList = userService.subParentMenuList(Integer.parseInt(user.getGroupID()), Integer.parseInt(user.getParticipantid()));
				session.setAttribute("subParentMenuList", subParentMenuList);
				
				List<ParentMenu> parentMenuList = userService.ParentmenuList(Integer.parseInt(user.getGroupID()), Integer.parseInt(user.getParticipantid()));
				session.setAttribute("parentMenuList", parentMenuList);
			}
			else
			{
				List<Menu> menuList = new ArrayList<Menu>();
				Menu menu = new Menu();
				menu.setMenuID(1);
				menu.setParentSubMenuID("1");
				menu.setMenuName("Add Institution");
				menu.setMenuIcon("fa fa-plus");
				menu.setMenuAction("addInstForm");
				menuList.add(menu);
				session.setAttribute("menuList", menuList);
				List<SubParentMenu> subParentMenuList = new ArrayList<SubParentMenu>();
				SubParentMenu subParentMenu = new SubParentMenu();
				subParentMenu.setParentSubMenuID(1);
				subParentMenu.setParentMenuID(1);
				subParentMenu.setSubMenuName("Participant Configuration");
				subParentMenuList.add(subParentMenu);
				session.setAttribute("subParentMenuList", subParentMenuList);
				List<ParentMenu> parentMenuList = new ArrayList<ParentMenu>();
				ParentMenu parentMenu = new ParentMenu();
				parentMenu.setParentMenuID(1);
				parentMenu.setParentMenuName("System Configuration");
				parentMenu.setMenuIcon("fa fa-gears");
				parentMenuList.add(parentMenu);
				session.setAttribute("parentMenuList", parentMenuList);
			}
			userService.updateSuccessLogin(user.getUserid(), user.getParticipantid());
			request.getSession().setMaxInactiveInterval(5 * 60); //5 Minutes
			redirectStrategy.sendRedirect(request, response, "/home");
		}
	}
		
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) 
	{
		   return dateToConvert.toInstant()
		     .atZone(ZoneId.systemDefault())
		     .toLocalDateTime();
		}
}
