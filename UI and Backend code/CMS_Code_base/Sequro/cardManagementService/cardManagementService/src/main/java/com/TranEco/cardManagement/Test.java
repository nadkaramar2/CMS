package com.TranEco.cardManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class Test {
	
	@Autowired
	private static Environment env;
	
	public static void main(String[] args) throws Exception
    {
 
        // File path is passed as parameter
        File file = new File(env.getProperty("keystoreFile"));
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        BufferedReader br
            = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
 
            // Print the string
            System.out.println("Prashantttttt:::::::::::"+st);
    }
}
