package com.traneco.config.session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class SessionUtil
{
    public String generateSessionDate(long time)
    {
	Date lastAccessedDate = new Date(time);
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String formattedLastAccessedTime = dateFormat.format(lastAccessedDate);
	return formattedLastAccessedTime;
	
    }
    
    public void sessionInvalidateProcess()
    {
	
    }
    
    public void sessionDetailsPrinter(String message , HttpSession session)
    {
	long sessionLastAccessedTime = session.getLastAccessedTime();
	 int sessionMaxInactiveInterval = session.getMaxInactiveInterval();
	 String sessionlastAccessedTimeString = generateSessionDate(sessionLastAccessedTime);
	 long creationTime = session.getCreationTime();
	 String generateSessionDate = generateSessionDate(creationTime);
	 System.err.println(message + " Initial Session ID is "+session.getId());
	 System.err.println(message +" last Acessd Time is  "+ sessionlastAccessedTimeString);
	 System.err.println(message +" MaxInactive Interval Int  "+sessionMaxInactiveInterval);
	 System.err.println(message +" Generated Session Date Interval Int  "+generateSessionDate);
    }
}
