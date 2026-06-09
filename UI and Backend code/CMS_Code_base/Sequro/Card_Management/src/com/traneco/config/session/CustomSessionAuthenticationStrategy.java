package com.traneco.config.session;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

/*
 * Created the SessionREgistry Variable 
 * If Autowired then a new Object of the SEssionREgistry will be inside the session Registry Variable 
 * 
 * but using constructor we are initializing the current SessionRegistry object to the one which is passed to the CustomAuthetniction Strategy
 * */
@Component
public class CustomSessionAuthenticationStrategy extends ConcurrentSessionControlAuthenticationStrategy 
{
    @Autowired
    private SessionRegistryImpl sessionRegistry;
    
    @Autowired
    private SessionUtil sessionUtil;
    
    //current Session registry should be the one that is passed to the Authentication Strategys
    public CustomSessionAuthenticationStrategy(SessionRegistryImpl sessionRegistry)
    {
	super(sessionRegistry);
	this.sessionRegistry = sessionRegistry;
    }


    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response)
	    throws SessionAuthenticationException
    {
	
	 //System.err.println("----------------------------on Authentication --------------------------");
	        //principal passed from the login page
	 Object principal = authentication.getPrincipal();
	        //cannot get the session of the principal passed from the login page here
	 //HttpSession sessionBeginning = request.getSession(false);
	 //String concurrentMessage = "Concurrent Initial Loadup";
	 //sessionUtil.sessionDetailsPrinter(concurrentMessage, sessionBeginning);
	 //List<SessionInformation> allSessions = getAllSessions();
//	 System.err.println("All Sesson Present in Project"+allSessions.size());
//	 if(allSessions.size()>0)
//	 {
//	     Object principal2 = allSessions.get(0).getPrincipal();
//	     if(principal.equals(principal2))
//	     {
//		 System.err.println("The First Principals are Equal");
//	     }
//	     else
//	     {
//		 System.err.println("The Frist Principals are Not Equal");
//	     }
//	 }
	 
	 List<SessionInformation> sessionOfLoggedInPrincipal = sessionRegistry.getAllSessions(principal, false);
	 //System.err.println("All Sesson Present of Current Logged In user "+sessionOfLoggedInPrincipal.size());
	 
	 if (!sessionOfLoggedInPrincipal.isEmpty()) {
//	     SessionInformation sessionInformation = sessionOfLoggedInPrincipal.get(0);
//	     String sessionRegistrySavedSessionId = sessionInformation.getSessionId();
//	     Date sessionRegistryLastRequest = sessionInformation.getLastRequest();
//	     boolean expired = sessionInformation.isExpired();
//	     
//	     
//	     System.err.println("SessionREgistry Saved Session Id is  "+sessionRegistrySavedSessionId);
//	     System.err.println("SessionREgistry saved session LastRequestDate is  "+sessionRegistryLastRequest);
//	     System.err.println("SessionREgistry saved session isExpired  "+expired);
//	     
//	     HttpSession session = request.getSession(false);
//	     String message = "Session when principal is found ";
//	     sessionUtil.sessionDetailsPrinter(message, session);
            throw new SessionAuthenticationException("User is already logged in with an active session");
        }
	 else
        {
//            HttpSession session = request.getSession(false);
//            if(session != null)
//            {
//        	session.invalidate();
//        	
//            } 
            	//sessionRegistry.removeSessionInformation(principal);
            	super.onAuthentication(authentication, request, response);
            //sessionRegistry.registerNewSession(request.getSession().getId(),authentication.getPrincipal());
        }
    }
	
    
    // method to get all the principals and the sessions
    public List<SessionInformation> getAllSessions() {
	    List<Object> principals = sessionRegistry.getAllPrincipals();
	    List<SessionInformation> sessions = new ArrayList<SessionInformation>();
	    for (Object principal : principals) 
	    {
	        sessions.addAll(sessionRegistry.getAllSessions(principal, false));
	    }
	    return sessions;
	}

}
