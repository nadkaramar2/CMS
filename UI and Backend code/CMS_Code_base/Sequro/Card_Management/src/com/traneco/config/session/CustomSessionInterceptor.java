package com.traneco.config.session;
/*
 * added the interceptor before every request except for registration and login URL
 * date 18-08-2023
 * It is just a normal Interceptor just named it session interceptor 
 * it is used to redirect to login page if session is expired
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;

@Component
public class CustomSessionInterceptor implements HandlerInterceptor
{
	@Autowired
	LoggerUtil loggerUtil;
	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/loginForm") || requestURI.equals("/register")
        	|| requestURI.equals("/") || requestURI.equals("/Card_Management/api/getCardTypeInfo")
        	|| requestURI.equals("/Card_Management/api/isCardAlreadyLinked")
        	|| requestURI.equals("/Card_Management/api/getCardHolderName")
        	|| requestURI.equals("/Card_Management/api/getNetworkType")
        	|| requestURI.equals("/Card_Management/api/addCardAccountLinkageCMS") ||
        	requestURI.equals("/Card_Management/api/updateIssueCardToCustomer") ||
        	requestURI.equals("/Card_Management/api/getCardTypedataForLinkage")
        	|| requestURI.equals("/Card_Management/isoTransaction")
        	|| requestURI.equals("/Card_Management/pushTxn")
        	|| requestURI.equals("/Card_Management/doTransaction")
        	|| requestURI.equals("/Card_Management/creditcardTxn")
        	|| requestURI.equals("/Card_Management/generateCardEncDecKey")
        	|| requestURI.equals("/Card_Management/getGeneratedKeyValue")
        	|| requestURI.equals("/Card_Management/startLogging")
        	|| requestURI.equals("/Card_Management/stopLogging")
        	|| requestURI.equals("/Card_Management/getCardEncDec")
        	|| requestURI.equals("/Card_Management/addClient")
        	|| requestURI.equals("/Card_Management/cardAccountLinkageForm")
        		)
        {
            return true;
        }
        HttpSession session = request.getSession(false);
        //System.err.println(session.getId());
        if (session == null || session.getAttribute("user") == null) 
        {
        	loggerUtil.doLog(4, Utils.getLocalDate(), Utils.getLocalTime(), "Logged In Session Expired::");
            request.setAttribute("error", "Session Expired Please Login Again!");
            response.sendRedirect("/Card_Management/loginForm?expired=true");
            return false;
            
            //request.setAttribute("error", "Session Expired Please Login Again!");
            //response.sendRedirect("/Card_Management/loginForm?error=Session Expired Please Login Again! From Interceptor");
            //response.sendRedirect("/Card_Management/loginForm");
            //throw new Exception("error=Session Expired Please Login Again!");
            //return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
	    throws Exception
    {
	
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
	    throws Exception
    {
	
    }

}
