package com.traneco.config.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomSessionInformationExpiredStrategy
implements SessionInformationExpiredStrategy
{

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException
    {
	
	System.err.println("--------On Expiration Session Detection -START----------");
	System.err.println("--------On Expiration Session Detection -END----------");
    }

}
