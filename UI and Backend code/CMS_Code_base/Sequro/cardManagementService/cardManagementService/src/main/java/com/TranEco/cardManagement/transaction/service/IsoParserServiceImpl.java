package com.TranEco.cardManagement.transaction.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.handler.VisaHandle;
import com.TranEco.cardManagement.transaction.model.TLV;
import com.TranEco.cardManagement.transaction.model.TransactionRequest;
import com.TranEco.cardManagement.transaction.util.TransactionUtils;

@Service
public class IsoParserServiceImpl implements IsoParserService 
{

	private static Logger logger = Logger.getLogger(IsoParserServiceImpl.class.getPackage().getName());
	
	@Autowired
	DataOutputStream txnSocketConnectionOut;
	
	@Autowired
	DataInputStream txnSocketConnectionIn;
	
	@Autowired
	TransactionUtils transactionUtils;
	
	@Autowired
	VisaHandle visaHandle;
	
	
	@Override
	public void processTransaction() 
	{
		try {
			System.out.println("Getting Incomming Request.................");
			String incomingData = txnSocketConnectionIn.readUTF();
			System.out.println("incomingData:::"+incomingData);
			if(StringUtils.isNoneEmpty(incomingData))
			{
				String bitMap = incomingData.substring(48, 64);
				transactionUtils.data = incomingData.substring(64);
				char[] ch = bitMap.toCharArray();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < ch.length; i++) {
					sb.append(transactionUtils.hexToBin(String.valueOf(ch[i])));
				}

				char[] tags = sb.toString().toCharArray();
				int b = 0;
				for (int j = 0; j < tags.length; j++) {
					if ('1' == tags[j]) {
						b = j;
						transactionUtils.tagReader(Integer.parseInt("" + j));
					}
				}
				TransactionRequest request = new TransactionRequest();
				for(String data: transactionUtils.tagList) {
					TLV tlv = transactionUtils.dataElement.getTlvList().stream().filter( k -> data.equals(k.getName())).findAny().orElse(null);
					if(tlv != null) {
						String value = transactionUtils.parseTag(tlv.getType(), TransactionUtils.data);
						transactionUtils.setParseValue(tlv.getName(), value, request);
					}
				}
				// Pass this to Exact handler class.
				Map<String, Object> response = Collections.emptyMap();
				visaHandle.processTxn(request.getRequest(), response);
				StringBuilder sb1 = new StringBuilder();
				for(int i = 0 ;i < 64; i++) {
					if(response.get("d"+i) != null) {
						sb1.append(Integer.toString(i, 2));
					}else {
						sb1.append(Integer.toString(0, 2));
					}
				}
				StringBuilder sb2 = new StringBuilder();
				sb2.append(sb1.toString());
				for(Entry<String, Object> responseEntry : response.entrySet()) {
					TLV tlv = transactionUtils.dataElement.getTlvList().stream().filter( k -> responseEntry.getKey().equals(k.getName())).findAny().orElse(null);
					if(tlv != null) {
						String value = transactionUtils.parseTag(tlv.getType(), TransactionUtils.data);
						sb2.append(value);
					}
				}
				
				txnSocketConnectionOut.writeUTF(sb.toString());
				txnSocketConnectionOut.flush();				
			}
		}catch (Exception e) {
			logger.error("Transaction Procssing error: ",e);
		}
	}


	@Override
	public void test() throws Exception 
	{
		try 
		{
			System.out.println("some stuff");

			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			final Runnable task = new Runnable()
			{
			    @Override
			    public void run() 
			    {
			        System.out.println("do something::"+txnSocketConnectionOut);
			        try
			        {
						txnSocketConnectionOut.writeUTF("hello");
						txnSocketConnectionOut.flush();	
					}
			        catch (IOException e) {
						e.printStackTrace();
					}
			    }
			};
			Future<?> futureHandle = scheduler.scheduleWithFixedDelay(task, 10, 10, TimeUnit.SECONDS);

			System.out.println("some other stuff");
			
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
