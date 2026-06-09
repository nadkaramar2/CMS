package com.TranEco.cardManagement;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class StringClear {

	public static void main(String args[]) throws IOException 
	{
			try (FileOutputStream fos = new FileOutputStream("key.txt")) 
			{
			   System.out.println(fos);
			} catch (IOException e) {
			    e.printStackTrace();
		 
		{
			e.printStackTrace();
		}  
		}
		
			
			FileWriter writer = new FileWriter("test.txt", true); // 'true' for append mode
			  String keyPartForTxtFile = "prashanatTayde";
			  writer.write(keyPartForTxtFile);
			  writer.close();
			
}
}
