package com.TranEco.cardManagement.EmbossingProcess.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.EmbossingProcess.model.EmbossRequest;
import com.TranEco.cardManagement.EmbossingProcess.services.EmbossingService;

@RestController
//@PropertySource("classpath:application.properties")
public class EmbossController 
{
	private static Logger logger = Logger.getLogger(EmbossController.class.getPackage().getName());
	
	@Autowired
	EmbossingService embossingService;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(method = RequestMethod.POST, value = "embossRequest")
	public void embossRequest(@RequestBody EmbossRequest embossRequest) 
	{
		try 
		{
			String data[] = embossRequest.getCardType().split("\\|");
			embossingService.generateEmbossingData(data[0],data[1]);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "test")
	public void fileDataWrite() 
	{
		try 
		{
			FileWriter fw = null;
			File file = null;
			try 
			{
				logger.info("Entered fileDataWrite");
				Properties properties = new Properties();
				//String filepath = env.getProperty("emboss.file.path");
				//String filepath = "/usr/local/tomcat/paths/cms/emboss";
				String filepath = "C:\\CMS\\winservice\\cardManagement\\cms\\emboss";
				file = new File(filepath+File.separator+"fileName"+".txt");
				System.out.println("EmbossController.fileDataWrite--1--- filepath:["+filepath+"]");
				file = new File(filepath+File.separator+"fileName"+".txt");
				
				file.getParentFile().mkdirs(); 
					file.createNewFile();
					fw=new FileWriter(file,true);
					fw.write("testing"+"\n");
					logger.info("ENd of fileDataWrite");
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				if(fw != null) 
				{
					try 
					{
						fw.close();
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
