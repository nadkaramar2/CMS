package com;

import com.traneco.common.util.Utils;

public class GenerateRandomString {

	public static void main(String[] args) 
	{
		 String generateKey = Utils.getRandomString(32);
		 System.out.println("KEY:::"+generateKey);
		 
		 
		 String currentDate = Utils.getLocalDate();
		 System.out.println("Local Date:::"+currentDate);
		 
		 
		 String CurrentTime = Utils.getLocalTime();
		 System.out.println("Local Time:::"+CurrentTime);
	}
}
