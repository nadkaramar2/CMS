/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define all common method use in application.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define all common method use in application.
 *===============================================================================================================================================
 *
 */

package com.traneco.common.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.traneco.cms.notification.EmailTemplate;
import com.traneco.service.model.TransactionIdTable;
import com.traneco.service.services.ClientService;

@Component
public class Utils 
{
	 public static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");	
	 private static final String PASSWORD_PATTERN ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";

	    private static final Random RANDOM = new SecureRandom();
		private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		private static final int ITERATIONS = 10000;
		private static final int KEY_LENGTH = 256;
		
		private static SecureRandom random = new SecureRandom();
		private static final String CHARACTER_SET="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$*&@?<>~!%#";
		
		public static DecimalFormat decimalFormat = new DecimalFormat("0.00");
		private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	 //Password must to be at least 8 Chars, 1 Number, 1 upper case, 1Lower Case, 1 SpecialChar, No Spaces
		public static boolean isValid(final String password) 
		   {
		       Matcher matcher = pattern.matcher(password);
		       return matcher.matches();
		   }
	
	private static ClientService clientService;
	
	@Autowired
	public Utils(ClientService clientServiceobj) 
	{
		Utils.clientService = clientServiceobj;
	}
	 
	public static String getClientIpAddr(HttpServletRequest request) 
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) 
		{
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	public static String getSalt(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

	public static byte[] hash(char[] password, byte[] salt) {
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try 
		{
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			return skf.generateSecret(spec).getEncoded();
		} 
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) 
		{
			throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
		}
		finally {
			spec.clearPassword();
		}
	}

	public static String generateSecurePassword(String password, String salt) 
	{
		String returnValue = null;
		byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
		returnValue = Base64.getEncoder().encodeToString(securePassword);
		return returnValue;
	}

	public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
		boolean returnValue = false;
		String newSecurePassword = generateSecurePassword(providedPassword, salt);
		returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
		return returnValue;
	}

	public static boolean isNumber(String value) {
		String regex = "[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean isCharacter(String value) {
		String regex = "^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean isAlphaNumeric(String value) {
		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static String trim(String value) 
	{
		return value == null ? "" : value.trim();
	}

	public static boolean isEmptyStr(String value) {
		if (value != null && value.trim().length() > 0)
			return false;
		else
			return true;
	}

	public static boolean isValidEmail(String emailID) {
		Pattern pattern;
		Matcher matcher;
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(emailID);
		return matcher.matches();
	}

	public static boolean isAlphaNumericSpace(String value) 
	{
		String regex = "^[a-zA-Z0-9\\s]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean isDouble(String value) 
	{
		String regex = "^\\d{2,5}+(\\.{0,1}(\\d{0,2}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static String convertToStingBuff(String[] menuIdsArr) 
	{
		StringBuffer strBuff = new StringBuffer();
		try 
		{
			for (int i = 0; i < menuIdsArr.length; i++) 
			{
				strBuff.append(menuIdsArr[i]).append(",");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return strBuff.toString().substring(0, strBuff.length() - 1);
	}

	public static String convertToString(List<String> list) 
	{
		StringBuffer buff = new StringBuffer();
		String returnStr = new String();
		try 
		{
			for (String string : list) 
			{
				buff.append(string).append(",");
			}
			returnStr = buff.toString().substring(0, buff.toString().length() - 1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return returnStr;
	}

	public static boolean isMinLenValid(String value, int minLength) 
	{
		if (value.length() < minLength)
			return false;
		else
			return true;
	}

	public static boolean isMaxLenValid(String value, int minLength) 
	{
		if (value.length() > minLength)
			return false;
		else
			return true;
	}

	public static void closeResources(Object... obj) 
	{
		for (Object object : obj) 
		{
			object = null;
		}
	}

	public static Timestamp generateDate()
	{
		/*
		 * Date date=new Date();
		 * 
		 * SimpleDateFormat sf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"); String
		 * sdate=sf.format(date);
		 */
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;

	}

	public static String getSHA256(String data) throws NoSuchAlgorithmException {
		StringBuffer sb = new StringBuffer();

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(data.getBytes());
		byte byteData[] = md.digest();

		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	public static String objectToJson(Object obj) throws Exception 
	{
		Class<? extends Object> c = obj.getClass();
		JsonObject jsonObject = new JsonObject();
		for (java.lang.reflect.Field field : c.getDeclaredFields()) 
		{
			field.setAccessible(true);
			String name = field.getName();
			String value = String.valueOf(field.get(obj));
			jsonObject.addProperty(name, value);
		}

		return jsonObject.toString();
	}

	public static String convertExpDate(String date) throws ParseException 
	{
		SimpleDateFormat sb = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		SimpleDateFormat sb1 = new SimpleDateFormat("yy/MM");
		return sb1.format(sb.parse(date));
	}

	public static String maskCardNumber(String cardNumber, String mask) 
	{
		// format the number
		int index = 0;
		StringBuilder maskedNumber = new StringBuilder();
		for (int i = 0; i < mask.length(); i++) 
		{
			char c = mask.charAt(i);
			if (c == '#') 
			{
				maskedNumber.append(cardNumber.charAt(index));
				index++;
			} 
			else if (c == 'x') 
			{
				maskedNumber.append(c);
				index++;
			}
			else 
			{
				maskedNumber.append(c);
			}
		}
		// return the masked number
		return maskedNumber.toString();
	}

	public static String getSysDate() 
	{
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("YYYYMMddHHmmss");
		String sdate = sf.format(date);
		return sdate;
	}
	
	public static String getGeneratedAcNumber(String digitsLengthStr, String startNumStr) 
	{
	    String acNoStr = "";
	    StringBuilder sb = new StringBuilder();
	    int digitLen = Integer.parseInt(digitsLengthStr);
	    
	    int count = startNumStr.length(); //001 = 3 14
	    digitLen = digitLen - count; //14 - 3 = 11
	    
	    for (int i = 0; i < digitLen; i++)
	    {
	        sb.append("0");
	    }
	    //00100000000000
	    acNoStr = startNumStr + sb.toString();
	    return acNoStr;
	}
	
	/*
	 * public static String getUpdatedAccNumber(String lastAccNumber) { int
	 * lastAccNo = Integer.parseInt(lastAccNumber)+ 1; //2001 return lastAccNo+""; }
	 */
	
	
	public static String getUpdatedAccNumber(String lastAccNumber)
	{
		long lastAccNo = Long.parseLong(lastAccNumber) + 1;
		return lastAccNo+"";
	}
	
	public static Date getNextDateBasedOnNoOfDays(String dateFormatStr)
	{
		try 
		{
			if (dateFormatStr!=null && dateFormatStr.trim().length() > 0)
			{
				System.out.println("--- NO DATE FORMAT FOUND ----");
				return null;
			}
			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Using today's date
			c.add(Calendar.DATE, 5); // Adding 5 days
			String output = sdf.format(c.getTime());
			System.out.println(output);
			
			Date nextDate = sdf.parse(output);
			
			return nextDate;
		}
		catch (Exception e) {
		}
		return null;
	}
	
	//created By ankit on 02-01-2023 to get current Date
	public static String getLocalDate() 
	{
		LocalDateTime myDate = LocalDateTime.now();
		DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		String formattedDate = myDate.format(myDateFormatter);
		SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
		return formattedDate;
	}
	
	//created By ankit on 02-01-2023 to get current Time
	public static String getLocalTime() {
		DateTimeFormatter myTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		LocalDateTime myTime = LocalDateTime.now();
		String formattedTime = myTime.format(myTimeFormatter);
		return formattedTime;
	}
	
	public static String getJulidanDate() 
	{
		int dayOfCurrentYear = LocalDateTime.now().getDayOfYear();
		String julianDayFormat = String.format("%03d", dayOfCurrentYear); //formatting till last 3 digits
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		int year = LocalDateTime.now().getYear();
		String julianYear = String.valueOf(year);
		String julianYearFormat = df.format(LocalDateTime.now().getYear());
		String julianDate = julianYear + julianDayFormat;
		/*
		 * String yearTwoDigits = df.format(Calendar.getInstance().getTime()); String
		 * julianDate = yearTwoDigits + julianDayFormat;
		 */
        return julianDate;
	}
	
    public static String getLastTwoDigitsOfYear() {
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
        String year = df.format(Calendar.getInstance().getTime());
        return year;
    }

    public static String getJulianDay() 
    {
        int dayOfCurrentYear = LocalDateTime.now().getDayOfYear();
        String  dayOfCurrentYearString  = String.valueOf(dayOfCurrentYear);
        return dayOfCurrentYearString;
    }
	
    public static String getJulianYear() 
    {
        int currentYear = LocalDateTime.now().getYear();
        String  currentYearString = String.valueOf(currentYear);
        return currentYearString;
    }
    //added by ankit till here
	public static String getLastTransactionId() 
	{
		return null;
	}
	
	public static String getGeneratedTransactionId() 
	{
		String transactionId = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try 
		{
			String year = String.valueOf((LocalDateTime.now().getYear()) % 100);//23
			String dayOfCurrentYearinStr = String.valueOf(LocalDateTime.now().getDayOfYear());//37
			
			StringBuilder julianDayFormatSb = new StringBuilder("");
			
			if (dayOfCurrentYearinStr.length() == 1) {
				julianDayFormatSb.append("00").append(dayOfCurrentYearinStr);
			}
			else if (dayOfCurrentYearinStr.length() == 2) {
				julianDayFormatSb.append("0").append(dayOfCurrentYearinStr);
			}
			else
			{
				julianDayFormatSb.append(dayOfCurrentYearinStr);
			}
			String julianDayFormat = julianDayFormatSb.toString();
			
			String Date = dateFormat.format(new Date());
			
			List<TransactionIdTable> transactionIdTables = clientService.getTransactionIdList(year, julianDayFormat);
			TransactionIdTable transactionIdTable = null;
			
			if(transactionIdTables.isEmpty()) 
			{
				String yearJulianDateFormatData = year + julianDayFormat;
				transactionIdTable = new TransactionIdTable();
				transactionId = yearJulianDateFormatData + "0000001";
				transactionIdTable.setStrYear(year);
				transactionIdTable.setStrJulianDate(julianDayFormat);
				transactionIdTable.setStrLastTxnSerialNo(transactionId);
				transactionIdTable.setStrCreatedDate(Date);
				transactionIdTable.setStrCreatedBy("System"); 
				clientService.saveTransactionIdDetails(transactionIdTable);
			}
			else 
			{
				transactionIdTable = transactionIdTables.get(0); 
				//String tranId = String.valueOf(Long.parseLong(transactionIdTable.getStrLastTxnSerialNo()) + 1);
				transactionId = String.valueOf(Long.parseLong(transactionIdTable.getStrLastTxnSerialNo()) + 1);
				//transactionId = tranId;
				transactionIdTable.setStrLastTxnSerialNo(transactionId);
				transactionIdTable.setStrCreatedDate(Date);
				clientService.updateTransactionIdDetails(transactionIdTable);
			}	   
			return transactionId;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return transactionId;
	}
	
	/*private Model getModelDatafromJsonObject(Model model, String jsonStr) 
	{
		try
		{
			JsonNode actualObj = this.mapper.readTree(jsonStr);			
			Iterator<String> itr = actualObj.fieldNames();
			System.out.println(itr);
			
			while(itr.hasNext()) 
			{
				String keyData = itr.next();
				System.out.println("keyData:::"+keyData);
				String keyValue = actualObj.get(keyData).asText();
				System.out.println("keyValue:::"+keyValue);
				if (keyValue!=null && keyValue.trim().length() >0) 
				{
					model.addAttribute(keyData, keyValue);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return model;
	}
	*/
	
	public static String getBase64Image(String imageFileLocation) 
	{
		try 
		{
			File file = new File(imageFileLocation);
			//new File("E:\\Sequro\\Speta\\logo\\cbimage.png")
			//E:\\KYC_IMAGE\\adharCard.png
			
			//File file = new File("E:\\KYC_IMAGE\\adharCard.png");
			
			if (!file.exists())
			{
				return null;
			}
			
			byte[] fileContent = FileUtils.readFileToByteArray(file);		
			String encodeString = Base64.getEncoder().encodeToString(fileContent);
			return encodeString;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static EmailTemplate getEmailtemplate(String mailTo, String subject, String bodyMessage) 
	{
		try 
		{
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setStrFrom("contactus@sequrotechnologies.com");

			emailTemplate.setStrTo(mailTo);
			
			emailTemplate.setStrSubject(subject);
			
			emailTemplate.setStrText(bodyMessage);
			
			return emailTemplate;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static double stringToDouble(String value) {
		double convertValue = Double.parseDouble(value);
		return convertValue;
	}
	
	public static BigDecimal stringToBigDecimal(String txnAmount)
	{
		return new BigDecimal(txnAmount).setScale(2, RoundingMode.HALF_UP);
	}
	
	public static int stringToInt(String value)
	{
		int convertValue = Integer.parseInt(value);
		return convertValue;
	}
	
	public static Long stringToLong(String value) 
	{
		return Long.parseLong(value);
	}
	
	public static String getAlphaNumericString()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rd = new Random();
		int number = rd.nextInt(10000);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 2; i++)
		{
			int index=(int)(AlphaNumericString.length()* Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		sb.append(number);
		return sb.toString();
	}
	public static String getResponseMTI(String requestMti) 
	{
		Map<String, String> mtiDataMap = new HashMap<String, String>();
		mtiDataMap.put("0200", "0210");
		mtiDataMap.put("0400","0410");		
		return mtiDataMap.get(requestMti);
	}
	
	public static String DateAndHoursFormat() 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
		return formattedDate;
	}
	
	public static Date getCurrentDate() 
	{
		return new Date();
	}
	
	//Added By prashant Tayde  --10Oct 2023
	@SuppressWarnings("unused")
	public static EmailTemplate getEmailTemplateForSendMail(String fromMailId, String toMailId, String subject, String bodyMessage) 
	{
		EmailTemplate emailTemplate = new EmailTemplate();
		try 
		{
			if (fromMailId == null || fromMailId == "")
			{
				fromMailId = "contactus@sequrotechnologies.com";
			}
			
			emailTemplate.setStrFrom(fromMailId);
			emailTemplate.setStrTo(toMailId);
			emailTemplate.setStrSubject(subject);
			emailTemplate.setStrText(bodyMessage);
			return emailTemplate;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Connection getConnectDB(String databaseUrl, String userName, String password)	 
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
 
            Connection con = DriverManager.getConnection(databaseUrl, userName, password);
            return con;
        }
        catch (SQLException | ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
	
	//Added by prashant T For generate AlphanumberSpecial CHar -12Oct2023
	public static String getRandomString(int len) 
	{
        StringBuffer buff = new StringBuffer(len);

        for(int i=0;i<len;i++) 
        {
            int offset = random.nextInt(CHARACTER_SET.length());

            buff.append(CHARACTER_SET.substring(offset,offset+1));
        }
        return buff.toString();

    }
					
	
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) 
	{
		   return dateToConvert.toInstant()
		     .atZone(ZoneId.systemDefault())
		     .toLocalDateTime();
		}
	
	public static String getFormattedDateTimeStr() 
	{
		try 
		{
			SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("dd MMM YYYY hh:mm:ss");
			return simpleDateFormat3.format(new Date());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static Date getCurrentFormattedDate() throws Exception 
	{
		Date curretDate = new Date();		
		String strCurrentDate = simpleDateTimeFormat.format(curretDate);		
		return simpleDateTimeFormat.parse(strCurrentDate);
	}
	
	
	public static String maskValue(String value) 
	{
		   if(value != null)
	        {
	            StringBuffer maskNum = new StringBuffer();
	            maskNum.append(value.trim());
	            int size = maskNum.length();
	           
	                if(size > 10)
	                {
	                    int inx = 6;
	                    for(int end = inx + (size - 10); inx < end; inx++)
	                        maskNum.setCharAt(inx, '*');
	                }
	                value = maskNum.toString();
	        }
	        return value;
	   }
	
	
	public static String bigDecimalToString(BigDecimal bigDecimal) 
	{
		return bigDecimal.toPlainString();
	}
	
}