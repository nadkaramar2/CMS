package com;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test 
{
	public static void main(String[] args) 
	{
		//System.out.println(Utils.generateSecurePassword("Abcd1234*", "1@46!%0*3(7^"));
		/*
		 * int i = 0; for (i = 0; i < 10; i++) { break; }
		 * 
		 * System.out.println("Printing Value :::::"+i);
		 */
		List<String> stringValues = Arrays.asList("Prashant","Ajay","Sanajay","Sujay");
	
		List<String> reStrings = stringValues.stream().map(e-> e+"")
				.filter(e->e.startsWith("S")).collect(Collectors.toList());
	
		System.out.println("String Value of"+reStrings);
	}
		
		
		public static void main(int[] i) 
		{
			System.out.println("Test.main()::::::Integer Method Overloaded");
		}
		
		public static void main(long[] args) 
		{
			System.out.println("Test.main()::::Long Method Overloaded");
		}
	}

