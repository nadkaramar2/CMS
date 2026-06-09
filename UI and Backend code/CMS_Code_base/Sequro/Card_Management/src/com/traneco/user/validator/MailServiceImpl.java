package com.traneco.user.validator;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.traneco.user.model.UserBean;

@Service
public class MailServiceImpl implements MailService 
{
	public String sendEmailCustomer(UserBean userBean) 
	{
		String stringSenderEmail = "contactus@sequrotechnologies.com";
		String stringReceiverEmail = userBean.getStrEmailID();
		//String stringPasswordSenderEmail = "S3QnR0@2022";
		String stringPasswordSenderEmail = "Waz80463";
		String stringHost = "smtp.office365.com";
 
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", stringHost);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
 
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		javax.mail.Session session = Session.getInstance(properties, new javax.mail.Authenticator() 
		{
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
			{
				return new javax.mail.PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
			}
		});
		Thread thread = new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					 StringBuilder bodyMsg = new StringBuilder("Dear "+userBean.getStrFirstName()+", ");
					 bodyMsg.append("\r\n");
					 bodyMsg.append("Your Username For CMS is : "+userBean.getStrUserName()+" ");
		    		 bodyMsg.append("\r\n"); 
		    		 bodyMsg.append( "Your Default password is: "+userBean.getPlainPassword()+" "); 
		    		 bodyMsg.append("\r\n"); 
		    		 bodyMsg.append( "Kindly reset Your Password.");
		    		 bodyMsg.append( "\r\n");
		    		 bodyMsg.append( "\r\n");
		    		 bodyMsg.append( "Powered by Montra.");
		    		 //bodyMsg.append( "Powered by Sequro Technologies Pvt Ltd.");
					
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(stringSenderEmail));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(stringReceiverEmail));
					message.setSubject("Password Reset Request");
					message.setText(bodyMsg.toString());
					
					Transport.send(message);
 
					System.out.println("Done");
				} 
				catch (MessagingException e) 
				{
					throw new RuntimeException(e);
				}
			}
		});
		thread.start();
		return "Email Sent Successfully";
	}
	
}
