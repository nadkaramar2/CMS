package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePassword 
{

	 private static final String PASSWORD_PATTERN =
	           "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";

	   private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	   public static boolean isValid(final String password) 
	   {
	       Matcher matcher = pattern.matcher(password);
	       return matcher.matches();
	   }
	   
	   public static void main(String[] args) 
	   {
		       String password = "Prashant@123";
		       if (ValidatePassword.isValid(password)) 
		       {
		    	   System.out.println("Password is valid.");
		       }
		       else 
		       {
		           System.out.println("Password is not valid.");
		       }
		   }
}
