package com.traneco.config.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;
/**
 * This event is getting triggered when the session is expired and automatically the 
 * session is expired from the Session Registry , 
 * i am not sure whether the session is removed or not 
 * so i am manually removing any session which are inactive of the user 
 * */
@Component
public class CustomSessionDestroyedListener implements 
ApplicationListener<HttpSessionDestroyedEvent>
{
    
    
//    private CustomSessionRegistryImpl customSessionRegistryImpl;
    
//    public CustomSessionDestroyedListener(CustomSessionRegistryImpl customSessionRegistryImpl) {
//        this.customSessionRegistryImpl = customSessionRegistryImpl;
//    }

    @Autowired
    private SessionRegistryImpl sessionRegistryImpl;
    
    public CustomSessionDestroyedListener(SessionRegistryImpl sessionRegistryImpl) {
	this.sessionRegistryImpl = sessionRegistryImpl;
    }
    
    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent event)
    {
//	 System.err.println("Custom Session Destroy Listner Called");
	 String sessionId = event.getSession().getId();
	 SessionInformation sessionInformation = sessionRegistryImpl.getSessionInformation(sessionId);
	 if (sessionInformation != null) {
	       sessionInformation.expireNow();
	 }
    }

}
