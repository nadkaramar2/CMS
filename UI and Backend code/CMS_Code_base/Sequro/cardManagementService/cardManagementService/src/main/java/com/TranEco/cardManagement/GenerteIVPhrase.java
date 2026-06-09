package com.TranEco.cardManagement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenerteIVPhrase 
{
	public static void main(String[] args) 
	{
		
		   //CardUtils cardUtils = new CardUtils();
	       //SecretKey key = cardUtils.generateKey("22f44dd16389566bf87e537dbf38f9e9", "nrMXgrdn");
	       //System.out.println("Generate Key::::"+key);
	//Lambda Expression 
	Runnable runnable = () -> System.out.println("Hello Prashant");
	runnable.run();
	
	
	List<String> list = Arrays.asList("a1","a2","a3","s4","s5");
	List<String> filtered = list.stream().filter(s -> s.startsWith("s"))
			.collect(Collectors.toList());
	
	System.out.println("GenerteIVPhrase.main()"+filtered);
	
	}
		
	}

