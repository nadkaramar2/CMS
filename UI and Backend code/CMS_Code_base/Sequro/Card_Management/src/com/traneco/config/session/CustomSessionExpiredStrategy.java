package com.traneco.config.session;
/*
 * Created by ankit  28-08-2023
 * 
 * Note -> Session invalid Strategy is not letting this classs get called 
 * */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomSessionExpiredStrategy implements SessionInformationExpiredStrategy
{

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) 
	    throws IOException, ServletException, SessionAuthenticationException
    {
	
	HttpServletRequest request = event.getRequest();
    HttpServletResponse response = event.getResponse();

        // Add your custom logic here
        String errorMessage = "Sesson Expired! Please Login Again";
        if (errorMessage == null)
        {
     
        }
        
        request.getSession().setAttribute("error", errorMessage);
        
        //throw new SessionAuthenticationException("Session Expired Please Login Again");
        // Redirect the user to the expired URL with the error message
        response.sendRedirect("/loginForm?expired=true");

    }
}
