package com.traneco.config.session;
/*
 * Only For checking if the Session is Created or not 
 * */
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

public class MySessionListener implements HttpSessionListener
{

    @Autowired
    SessionUtil sessionUtil;
    
    @Override
    public void sessionCreated(HttpSessionEvent event)
    {
	//HttpSession session = event.getSession();
	//sessionUtil.sessionDetailsPrinter("Creation of Session in My Session Listner", session);
	//System.out.println("Session created: " + event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event)
    {
	//HttpSession session = event.getSession();
	//sessionUtil.sessionDetailsPrinter("Destroying of Session in My Session Listner", session);
	//System.out.println("Session destroyed: " + event.getSession().getId());
    }

}
