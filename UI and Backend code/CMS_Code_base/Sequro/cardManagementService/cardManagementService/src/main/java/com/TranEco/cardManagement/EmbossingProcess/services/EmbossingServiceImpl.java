package com.TranEco.cardManagement.EmbossingProcess.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TranEco.cardManagement.EmbossingProcess.dao.EmbossDao;
import com.TranEco.cardManagement.EmbossingProcess.model.Element;
import com.TranEco.cardManagement.EmbossingProcess.model.EmbossFile;
import com.TranEco.cardManagement.EmbossingProcess.model.TemporaryEmbossingData;
import com.TranEco.cardManagement.HSMThales.services.HSMThalesService;
import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.common.exception.InvalidCVV;
import com.TranEco.cardManagement.crypt.AESKeyGenerator;
import com.TranEco.cardManagement.crypt.EncodeDecode;
import com.TranEco.cardManagement.crypt.model.EncryptData;
import com.TranEco.cardManagement.crypt.model.EncryptKeyData;
import com.TranEco.cardManagement.crypt.model.RsaKeyValue;
import com.TranEco.cardManagement.cryptservice.AESEncryptDecryptService;
import com.TranEco.cardManagement.cryptservice.EncryptedKeyDataService;
import com.TranEco.cardManagement.cryptservice.RSAEncryptDecryptService;
import com.TranEco.cardManagement.cryptservice.RSAKeyValueService;

@Service
@Transactional
//@PropertySource({"classpath:application.properties"})
public class EmbossingServiceImpl implements EmbossingService 
{
	@Autowired
	EmbossDao embossDao;

	@Autowired
	@Lazy
	EmbossFile emboss;

	@Autowired
	@Lazy
	HSMThalesService hSMThalesService;

	@Autowired
	private Environment env;

	@Autowired
	AESKeyGenerator aesKeyGenerator;

	@Autowired
	private AESEncryptDecryptService aesEncryptDecryptService;

	@Autowired
	private RSAEncryptDecryptService rsaEncryptDecryptService;

	@Autowired
	private EncryptedKeyDataService encryptKeyDataService;
	
	@Autowired
	private RSAKeyValueService rsaKeyValueService;
	
	
	private static Logger logger = Logger.getLogger(EmbossingServiceImpl.class.getPackage().getName());

	@Override
	public void generateEmbossingData(String cardType, String flag) 
	{
		logger.info("Request Data" + cardType + " " + flag);
		List<TemporaryEmbossingData> embossList = embossDao.getEmbossingData(cardType.replace("'", ""), flag);
		logger.info("Request Data List" + embossList.size() + " " + embossList);

		String fileName = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
		if ("Y".equals(flag)) 
		{
			fileName = "Instant_" + fileName;
			System.out.println("Generate Instant file :::::"+fileName);
		} 
		else 
		{
			fileName = "Personalized_" + fileName;
			System.out.println("Generate Personalized file ::::::"+fileName);
		}
		processEmbossData(embossList, fileName);
	}

	public void processEmbossData(List<TemporaryEmbossingData> embossList, String fileName) 
	{
		try 
		{
			// here generation of keys will be done {same aes key and iv for the whole file}
			// added for encryption--
			byte[] generateIV = aesKeyGenerator.generateIV();
			SecretKey generateKey = aesKeyGenerator.generateKey();
			String aesEncodedKey = EncodeDecode.encode(generateKey.getEncoded());
			String encodedIV = EncodeDecode.encode(generateIV);

			EncryptData encryptData = new EncryptData();
			encryptData.setKey(aesEncodedKey);
			encryptData.setIv(encodedIV);
			System.err.println(aesEncodedKey);
			System.err.println(encodedIV);
			// added for encryption--
			for (TemporaryEmbossingData data : embossList) 
			{
				logger.info(data.toString());
				generateData(data, fileName, encryptData);
				UpdateEmbossFlag.updateCardFlag(data.getTokenCard(), embossDao);
				// embossFlag(data.getStrCardNo());
			}

			// finally adding the encrypted key in the key table
			
			RsaKeyValue rsAKeyValue = rsaKeyValueService.getRSAKeyValue(1);
			//String publicKey1 = RSAStaticKey.publicKey1;
			String publicKey1 = rsAKeyValue.getPublicKey();
			EncryptData encryptAesKey = new EncryptData();
			encryptAesKey.setEncryptionData(aesEncodedKey);
			encryptAesKey.setKey(publicKey1);

			String rsaEncryptedAesKey = rsaEncryptDecryptService.encrypt(encryptAesKey);
			EncryptKeyData encryptKeyData = new EncryptKeyData();
			encryptKeyData.setAesEncodedKey(rsaEncryptedAesKey);
			// encryptKeyData.setRsaEncodedPublicKey(rsaPublicKey); //not creating any new
			// keys for every text file generated
			// encryptKeyData.setRsaEncodePrivateKey(rsaPrivateKey); // hardcoded keys are
			// in the project
			encryptKeyData.setFileName(fileName);
			encryptKeyData.setAesEncodedIv(encodedIV);
			encryptKeyDataService.saveData(encryptKeyData);
			System.err.println(aesEncodedKey);
			System.err.println(encodedIV);
		} catch (Exception e) {
			logger.info("Error--" + e.getMessage(), e);
		}
		logger.info("Processing Comepleted ----------------- ");
	}

	public String encryptRowDataOfFile(EncryptData encryptData) {
		String encryptDataByKey = "";
		try {
			encryptDataByKey = aesEncryptDecryptService.encrypt(encryptData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptDataByKey;
	}
	// changed by ankit
	
	
	
	public void generateData(TemporaryEmbossingData data,String fileName,EncryptData encryptData) 
	{
		logger.info("Entered generateData");
		
		List<Element> elements = emboss.getElement();
		StringBuffer sb = new StringBuffer();
		
		for (Element element : elements) 
		{
			try 
			{
				switch (element.getType()) {

				case Constant.Emboss_File.STATIC:
					sb.append(element.getValue());
					break;

				case Constant.Emboss_File.DYNAMIC:

					switch (element.getValue()) {
					case "EMCARD":
						sb.append(data.getStrCardNo());
						break;

					case "EMDNAM":
						if (element.getPaddingType() != null && element.getPaddingvalue() != null) {
							if ("R".equals(element.getPaddingType()))
								sb.append(StringUtils.rightPad(data.getStrEmbossLine1(), element.getLength(),
										element.getPaddingvalue()));
							else
								sb.append(StringUtils.leftPad(data.getStrEmbossLine1(), element.getLength(),
										element.getPaddingvalue()));
						}
						break;

					case "EMCNAM":
						StringBuffer sb1 = new StringBuffer();
						sb1.append(data.getStrEncodeFirstName() == null ? "" : data.getStrEncodeFirstName()).append(" ")
								.append(data.getStrEncodeMiddleName() == null ? "" : data.getStrEncodeMiddleName())
								.append(" ")
								.append(data.getStrEncodeLastName() == null ? "" : data.getStrEncodeLastName());
						if (element.getPaddingType() != null && element.getPaddingvalue() != null) 
						{
							if ("R".equals(element.getPaddingType()))
								sb.append(StringUtils
										.rightPad(sb1.toString(), element.getLength(), element.getPaddingvalue())
										.substring(0, element.getLength()));
							else
								sb.append(StringUtils
										.leftPad(sb1.toString(), element.getLength(), element.getPaddingvalue())
										.substring(0, element.getLength()));
						}
						break;

					case "ACDATE":
						sb.append(data.getStrActiveDate());
						break;

					case "EXDATE":
						sb.append(data.getStrExpiryDate());
						break;

					case "CCV2":
						sb.append(generateCVV(data.getStrCardNo(),
								data.getStrExpiryDate().substring(3, 5) + data.getStrExpiryDate().substring(0, 2),
								env.getProperty("hsm.cvv2")));
						break;

					case "ANO": // ARTHIA NUMBER/Kisan code
						sb.append(StringUtils.leftPad("", 8, " "));
						break;

					case "EXYY":
						sb.append(data.getStrExpiryDate().substring(3, 5));
						break;

					case "EXMM":
						sb.append(data.getStrExpiryDate().substring(0, 2));
						break;

					case "EMDSER": // SERVICE CODE(Right padded with spaces)
						if (element.getPaddingType() != null && element.getPaddingvalue() != null) 
						{
							if ("R".equals(element.getPaddingType()))
								sb.append(StringUtils.rightPad(data.getStrServiceCode(), element.getLength(),
										element.getPaddingvalue()));
							else
								sb.append(StringUtils.leftPad(data.getStrServiceCode(), element.getLength(),
										element.getPaddingvalue()));
						}
						break;

					case "CVV1":
						sb.append(generateCVV(data.getStrCardNo(),
								data.getStrExpiryDate().substring(3, 5) + data.getStrExpiryDate().substring(0, 2),
								data.getStrServiceCode()));
						break;

					case "EMDANO": // AADHAAR NUMBER sb.append(StringUtils.leftPad("", 12, "0"));
						break;

					case "EMDAKNO": // DISCRETIONARY DATA – ARTHIA NUMBER/Kisan Code
						sb.append(StringUtils.leftPad("", 8, " "));
						break;

					case "DDATA": // DISCRETIONARY DATA
						sb.append(StringUtils.leftPad("", 10, "0"));
						break;

					case "ICVV":
						sb.append(generateCVV(data.getStrCardNo(),
								data.getStrExpiryDate().substring(3, 5) + data.getStrExpiryDate().substring(0, 2),
								env.getProperty("hsm.icvv")));
						break;

					default:
						if (element.getValue().contains("SUBSTR") && element.getValue().contains("EMCARD")) {
							String length[] = element.getValue().replace("(", "").replace(")", "").split("\\,", 4);
							sb.append(data.getStrCardNo().substring(Integer.parseInt(length[1]),
									Integer.parseInt(length[2])));
						}
						break;
					}

					break;

				default:
					break;
				}
			} catch (Exception e) 
			{
				logger.info("Error" + e.getMessage(), e);
			}

		}
		
		encryptData.setEncryptionData(sb.toString());
		String encryptRowDataOfFile = encryptRowDataOfFile(encryptData);
		logger.info("After Encrypt Data to Entered fileDataWrite");
		fileDataWrite(encryptRowDataOfFile, fileName);
	}

	public void fileDataWrite(String data, String fileName) {
		FileWriter fw = null;
		File file = null;
		try 
		{
			logger.info("Entered fileDataWrite");
			Properties properties = new Properties();
			String filepath = "D:\\Sequro\\winservice\\cardManagement\\cms\\emboss";
			//String filepath = env.getProperty("emboss.file.path");
			//String filepath = env.getProperty("/usr/local/tomcat/paths/cms/emboss");
			file = new File(filepath + File.separator + fileName + ".txt");
			file.getParentFile().mkdirs();
			file.createNewFile();
			fw = new FileWriter(file, true);
			fw.write(data + "\n");
			logger.info("ENd of fileDataWrite");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String generateCVV(String strCard, String strExpDate, String strServiceCode) throws IOException, InvalidCVV 
	{
		String cvv = hSMThalesService.GenerateCVV(strCard, strExpDate, strServiceCode, env.getProperty("hsm.cvk"));
		if (cvv != null && cvv.length() > 8) 
		{
			return cvv.substring(8);
		}
		else 
		{
			throw new InvalidCVV(cvv);
		}
	}

	@Override
	public void embossFlag(String card) {
		int count = embossDao.embossFlag(card);
		logger.info("Update Emboss Flag + " + card + " flag is " + count);
	}

	// Original Generate Data -modified by ankit-
	/*
	 * public void generateData(TemporaryEmbossingData data,String fileName) {
	 * 
	 * List<Element> elements = embossFile.getElement(); StringBuffer sb = new
	 * StringBuffer(); for(Element element : elements) { try { switch
	 * (element.getType()) {
	 * 
	 * case Constant.Emboss_File.STATIC: sb.append(element.getValue()); break;
	 * 
	 * case Constant.Emboss_File.DYNAMIC:
	 * 
	 * switch (element.getValue()) { case "EMCARD": sb.append(data.getStrCardNo());
	 * break;
	 * 
	 * case "EMDNAM": if(element.getPaddingType() != null &&
	 * element.getPaddingvalue() != null) { if("R".equals(element.getPaddingType()))
	 * sb.append(StringUtils.rightPad(data.getStrEmbossLine1(), element.getLength(),
	 * element.getPaddingvalue())); else
	 * sb.append(StringUtils.leftPad(data.getStrEmbossLine1(), element.getLength(),
	 * element.getPaddingvalue())); } break;
	 * 
	 * case "EMCNAM": StringBuffer sb1 = new StringBuffer();
	 * sb1.append(data.getStrEncodeFirstName() == null ? "" :
	 * data.getStrEncodeFirstName()) .append(" ")
	 * .append(data.getStrEncodeMiddleName() == null ? "" :
	 * data.getStrEncodeMiddleName()) .append(" ")
	 * .append(data.getStrEncodeLastName() == null ? "" :
	 * data.getStrEncodeLastName()); if(element.getPaddingType() != null &&
	 * element.getPaddingvalue() != null) { if("R".equals(element.getPaddingType()))
	 * sb.append(StringUtils.rightPad(sb1.toString() , element.getLength(),
	 * element.getPaddingvalue()).substring(0, element.getLength())); else
	 * sb.append(StringUtils.leftPad(sb1.toString() , element.getLength(),
	 * element.getPaddingvalue()).substring(0, element.getLength())); } break;
	 * 
	 * case "ACDATE": sb.append(data.getStrActiveDate()); break;
	 * 
	 * case "EXDATE": sb.append(data.getStrExpiryDate()); break;
	 * 
	 * case "CCV2": sb.append(generateCVV(data.getStrCardNo(),
	 * data.getStrExpiryDate().substring(3,5)+data.getStrExpiryDate().substring(0,2)
	 * , env.getProperty("hsm.cvv2"))); break;
	 * 
	 * case "ANO": //ARTHIA NUMBER/Kisan code sb.append(StringUtils.leftPad("", 8,
	 * " ")); break;
	 * 
	 * case "EXYY": sb.append(data.getStrExpiryDate().substring(3,5)); break;
	 * 
	 * case "EXMM": sb.append(data.getStrExpiryDate().substring(0,2)); break;
	 * 
	 * case "EMDSER": //SERVICE CODE(Right padded with spaces)
	 * if(element.getPaddingType() != null && element.getPaddingvalue() != null) {
	 * if("R".equals(element.getPaddingType()))
	 * sb.append(StringUtils.rightPad(data.getStrServiceCode(), element.getLength(),
	 * element.getPaddingvalue())); else
	 * sb.append(StringUtils.leftPad(data.getStrServiceCode(), element.getLength(),
	 * element.getPaddingvalue())); } break;
	 * 
	 * case "CVV1": sb.append(generateCVV(data.getStrCardNo(),
	 * data.getStrExpiryDate().substring(3,5)+data.getStrExpiryDate().substring(0,2)
	 * , data.getStrServiceCode())); break;
	 * 
	 * case "EMDANO": //AADHAAR NUMBER sb.append(StringUtils.leftPad("", 12, "0"));
	 * break;
	 * 
	 * case "EMDAKNO": //DISCRETIONARY DATA – ARTHIA NUMBER/Kisan Code
	 * sb.append(StringUtils.leftPad("", 8, " ")); break;
	 * 
	 * case "DDATA": //DISCRETIONARY DATA sb.append(StringUtils.leftPad("", 10,
	 * "0")); break;
	 * 
	 * case "ICVV": sb.append(generateCVV(data.getStrCardNo(),
	 * data.getStrExpiryDate().substring(3,5)+data.getStrExpiryDate().substring(0,2)
	 * , env.getProperty("hsm.icvv"))); break;
	 * 
	 * default: if(element.getValue().contains("SUBSTR") &&
	 * element.getValue().contains("EMCARD")) { String length[] =
	 * element.getValue().replace("(", "").replace(")", "").split("\\,",4);
	 * sb.append(data.getStrCardNo().substring(Integer.parseInt(length[1]),Integer.
	 * parseInt(length[2]))); } break; }
	 * 
	 * break;
	 * 
	 * default: break; } }catch (Exception e) {
	 * logger.info("Error"+e.getMessage(),e); } }
	 * 
	 * fileDataWrite(sb.toString(), fileName); }
	 * 
	 * 
	 * // Original Code written for every line new key value pair was getting
	 * generated public void fileDataWrite(String data, String fileName) {
	 * FileWriter fw = null; File file = null; try { file = new
	 * File(env.getProperty("emboss.file.path")+File.separator+fileName+".txt");
	 * file.getParentFile().mkdirs(); file.createNewFile(); fw=new
	 * FileWriter(file,true);
	 * 
	 * //encrypting the data which is to be written in file //using AES for
	 * encrypting data byte[] generateIV = aesKeyGenerator.generateIV(); SecretKey
	 * generateKey = aesKeyGenerator.generateKey(); String aesEncodedKey =
	 * EncodeDecode.encode(generateKey.getEncoded()); String encodedIV =
	 * EncodeDecode.encode(generateIV);
	 * 
	 * EncryptData encryptData = new EncryptData(); encryptData.setMessage(data);
	 * encryptData.setKey(aesEncodedKey); encryptData.setIv(encodedIV);
	 * 
	 * String encryptDataByKey = aesEncryptDecryptService.encrypt(encryptData);
	 * 
	 * //writing the encrypted data to the file
	 * encryptData.setEncryptedData(encryptDataByKey);
	 * 
	 * //encrypting the AES key with RSA Algorithm EncryptData generatedRsaKeys =
	 * rsaKeyGenerator.generateKeys(); String rsaPublicKey =
	 * generatedRsaKeys.getPublicKey(); String rsaPrivateKey =
	 * generatedRsaKeys.getPrivateKey();
	 * 
	 * 
	 * EncryptData rsaEncryptData = new EncryptData();
	 * rsaEncryptData.setMessage(aesEncodedKey);
	 * rsaEncryptData.setKey(rsaPublicKey); String rsaEncryptedAesKey =
	 * rsaEncryptDecryptService.encrypt(rsaEncryptData);
	 * 
	 * EncryptKeyData encryptKeyData = new EncryptKeyData();
	 * encryptKeyData.setAesEncodedKey(rsaEncryptedAesKey);
	 * encryptKeyData.setRsaEncodedPublicKey(rsaPublicKey);
	 * encryptKeyData.setRsaEncodePrivateKey(rsaPrivateKey);
	 * encryptKeyData.setFileName(fileName);
	 * encryptKeyDataService.saveData(encryptKeyData);
	 * fw.write(encryptDataByKey+"\n");
	 * 
	 * }catch (Exception e) { e.printStackTrace(); }finally { if(fw != null) { try {
	 * fw.close(); } catch (IOException e) { e.printStackTrace(); } } } }
	 * 
	 * 
	 */
}
