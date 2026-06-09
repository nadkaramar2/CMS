package com.traneco.config.session;
/**
 * Created by to manage the 
 * invalid Session Id 
 * on 25-08-2023
 * = Initally the the session is empty so creating session here=
 * */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;
/*Not in use Discarded this
 * */
@Component
public class CustomSessionInvalidStrategy implements InvalidSessionStrategy
{

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException
    {
	
//	System.err.println("-------Invalid Session Detection -----");
//	String requestedSessionId = request.getRequestedSessionId();
//	System.err.println("Session ID on invalid Detection Exists or Not "+requestedSessionId);
//	System.err.println("Start of Applicatin Requires a Session so Creating the Sessin From Here");
//	Object attribute = request.getAttribute("logout");
//	System.out.println("Logut Messageg "+attribute);
	//creating a new Session to access the home Page
//	HttpSession session = request.getSession();
//	System.err.println("New Session Created Here"+session.getId());
//	 //Session Created to access the page 
//	System.err.println("-------Invalid Session Detection -----");
	
//	Cookie[] cookies = request.getCookies();
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	            if (cookie.getName().equals("logout")) {
//	                String logoutMessage = cookie.getValue();
//			request.getSession().setAttribute("LogoutSessionMessage", logoutMessage);
//			System.err.println("-------LogoutSession Detection -----");
//	            }
//	            else {
//			String errorMessage = "Sesson is Invalid! Please Login Again";
//			request.getSession().setAttribute("InvalidSessionError", errorMessage);
//
//		    }
//	        }
//	    }
//	    
//	    else {
//		String errorMessage = "Sesson is Invalid! Please Login Again";
//		request.getSession().setAttribute("InvalidSessionError", errorMessage);
//
//	    }
//	  
//	
//	//code for deleting the cookie
//	Cookie cookie = new Cookie("logout", null);
//	cookie.setMaxAge(0);
//	response.addCookie(cookie);
	response.sendRedirect("/Card_Management/loginForm?expired=true");
    }

}
