package com.TranEco.cardManagement.transaction.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXB;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.TranEco.cardManagement.transaction.model.DataElement;
import com.TranEco.cardManagement.transaction.model.TAGType;
import com.TranEco.cardManagement.transaction.model.TLV;
import com.TranEco.cardManagement.transaction.model.TransactionRequest;

@Component
public class TransactionUtils {

	public List<String> tagList = new ArrayList<String>();

	public static String hexToBin(String s) 
	{
		return StringUtils.leftPad(new BigInteger(s, 16).toString(2), 4, '0');
	}

	public static DataElement dataElement = null;
	public static String data = null;
	
	static {
		StringWriter sw = new StringWriter();
		DataElement element = new DataElement();
		List<TLV> tlvList = new ArrayList<TLV>();

		tlvList.add(new TLV("de1", TAGType.B8));
		tlvList.add(new TLV("de2", TAGType.HLL));
		tlvList.add(new TLV("de3", TAGType.H6));
		tlvList.add(new TLV("de4", TAGType.H12));
		tlvList.add(new TLV("de5", TAGType.H12));
		tlvList.add(new TLV("de6", TAGType.H12));
		tlvList.add(new TLV("de7", TAGType.H10));
		tlvList.add(new TLV("de9", TAGType.H8));
		tlvList.add(new TLV("de10", TAGType.H8));
		tlvList.add(new TLV("de11", TAGType.H6));
		tlvList.add(new TLV("de12", TAGType.H6));
		tlvList.add(new TLV("de13", TAGType.H4));
		tlvList.add(new TLV("de14", TAGType.H4));
		tlvList.add(new TLV("de15", TAGType.H4));
		tlvList.add(new TLV("de16", TAGType.H4));
		tlvList.add(new TLV("de18", TAGType.H4));
		tlvList.add(new TLV("de19", TAGType.H3));
		tlvList.add(new TLV("de20", TAGType.H3));
		tlvList.add(new TLV("de21", TAGType.H3));
		tlvList.add(new TLV("de22", TAGType.H4));
		tlvList.add(new TLV("de23", TAGType.H3));
		tlvList.add(new TLV("de25", TAGType.H2));
		tlvList.add(new TLV("de28", TAGType.F9));
		tlvList.add(new TLV("de32", TAGType.HLL));
		tlvList.add(new TLV("de33", TAGType.HLL));
		tlvList.add(new TLV("de35", TAGType.HLL));
		tlvList.add(new TLV("de37", TAGType.F12));
		tlvList.add(new TLV("de38", TAGType.F6));
		tlvList.add(new TLV("de39", TAGType.F2));
		tlvList.add(new TLV("de41", TAGType.F8));
		tlvList.add(new TLV("de42", TAGType.F15));
		tlvList.add(new TLV("de43", TAGType.F40));
		tlvList.add(new TLV("de44", TAGType.JLL));
		tlvList.add(new TLV("de48", TAGType.JLL));
		tlvList.add(new TLV("de49", TAGType.H3));
		tlvList.add(new TLV("de50", TAGType.H3));
		tlvList.add(new TLV("de51", TAGType.H3));
		tlvList.add(new TLV("de52", TAGType.B8));
		tlvList.add(new TLV("de53", TAGType.H16));
		tlvList.add(new TLV("de54", TAGType.JLL));
		tlvList.add(new TLV("de55", TAGType.ILL));
		tlvList.add(new TLV("de59", TAGType.JLL));
		tlvList.add(new TLV("de60", TAGType.ILL));
		tlvList.add(new TLV("de62", TAGType.ILL));
		tlvList.add(new TLV("de63", TAGType.ILL));
		tlvList.add(new TLV("de64", TAGType.B8));
		tlvList.add(new TLV("de66", TAGType.H1));
		tlvList.add(new TLV("de68", TAGType.H3));
		tlvList.add(new TLV("de69", TAGType.H3));
		tlvList.add(new TLV("de70", TAGType.H3));
		tlvList.add(new TLV("de74", TAGType.H10));
		tlvList.add(new TLV("de75", TAGType.H10));
		tlvList.add(new TLV("de76", TAGType.H10));
		tlvList.add(new TLV("de77", TAGType.H10));
		tlvList.add(new TLV("de86", TAGType.H16));
		tlvList.add(new TLV("de87", TAGType.H16));
		tlvList.add(new TLV("de88", TAGType.H16));
		tlvList.add(new TLV("de89", TAGType.H16));
		tlvList.add(new TLV("de90", TAGType.H42));
		tlvList.add(new TLV("de95", TAGType.H42));
		tlvList.add(new TLV("de96", TAGType.B8));
		tlvList.add(new TLV("de97", TAGType.F17));
		tlvList.add(new TLV("de99", TAGType.HLL));
		tlvList.add(new TLV("de100", TAGType.HLL));
		tlvList.add(new TLV("de102", TAGType.JLL));
		tlvList.add(new TLV("de104", TAGType.ILL));
		tlvList.add(new TLV("de115", TAGType.JLL));
		tlvList.add(new TLV("de117", TAGType.JLL));
		tlvList.add(new TLV("de118", TAGType.JLL));
		tlvList.add(new TLV("de119", TAGType.JLL));
		tlvList.add(new TLV("de126", TAGType.ILL));

		element.setTlvList(tlvList);
		JAXB.marshal(element, sw);
		//System.out.println(sw.toString());
		dataElement = JAXB.unmarshal(new StringReader(sw.toString()), DataElement.class);

	}

	
	
	
	/**
	 * Test Case
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String hexStr = "160102015E00000041341100000000000000000000000200723C668128E09A1610476173900101022601000000000002000006211336221334271536220621221260110578051000050206434879224761739001010226D22122011590279289F8F1F7F2F1F5F1F3F3F4F2F7C5D5D5D6F0F0F0F2F1F6F1F6F0F0F0F0F1F14040404040D3A486A38881A595A28296A49385A5819984859540F6404040D281A2A399A497404040404040D5D60978212E915F79BAAA9B20010101000000008B0100889F0608A00000000310100482025C008408A000000003101004950580800480009A031806219C01015F2A0209789F02060000000200009F0902008C9F100706010A03A088189F1A0205789F260870EBBA4B6894931A9F2701809F33036040209F34030203009F3501149F3602000B9F37046140AD7C9F41030001755F3401059F03060000000000000425000010098000000000000000E8058000000000";
		String bitMap = hexStr.substring(48, 64);
		System.out.println(bitMap);
		TransactionUtils.data = hexStr.substring(64);
		TransactionUtils utils = new TransactionUtils();
		char[] ch = bitMap.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ch.length; i++) {
			sb.append(hexToBin(String.valueOf(ch[i])));
		}

		char[] tags = sb.toString().toCharArray();
		int b = 0;
		for (int j = 0; j < tags.length; j++) {
			if ('1' == tags[j]) {
				b = j;
				utils.tagReader(Integer.parseInt("" + j));
			}
		}
		System.out.println(TransactionUtils.data);
		TransactionRequest request = new TransactionRequest();
		setParseValue("de0", hexStr.substring(44, 48), request);
		for(String data: utils.tagList) {
			TLV tlv = dataElement.getTlvList().stream().filter( k -> data.equals(k.getName())).findAny().orElse(null);
			if(tlv != null) {
				String value = parseTag(tlv.getType(), TransactionUtils.data);
				setParseValue(tlv.getName(), value, request);
			}
		}
		for(Entry<String, Object> entry : request.getRequest().entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	public static void setParseValue(String tag, String value, TransactionRequest request) {
		switch (tag) {
		
		case "de0":
			request.getRequest().put("de0", value);
			break;
			
		case "de1":
			request.getRequest().put("de1", value);
			break;

		case "de2":
			request.setPan(value);
			request.getRequest().put("de2", value);
			break;

		case "de3":
			request.setProccode(value);
			request.getRequest().put("d3", value);
			break;

		case "de4":
			request.setAmount(value);
			request.getRequest().put("d4", value);
			break;

		case "de5":
			request.getRequest().put("de5", value);
			break;

		case "de6":
			request.getRequest().put("de6", value);
			break;

		case "de7":
			request.setTransTime(value);
			request.getRequest().put("de7", value);
			break;

		case "de9":
			request.getRequest().put("de9", value);
			break;

		case "de10":
			request.getRequest().put("de10", value);
			break;

		case "de11":
			request.setStan(value);
			request.getRequest().put("de11", value);
			break;

		case "de12":
			request.setTransTime(value);
			request.getRequest().put("de12", value);
			break;

		case "de13":
			request.getRequest().put("de13", value);
			break;

		case "de14":
			request.setExpiry(value);
			request.getRequest().put("de14", value);
			break;

		case "de15":
			request.getRequest().put("de15", value);
			break;

		case "de16":
			request.getRequest().put("de16", value);
			break;

		case "de17":
			request.setMcat(value);
			request.getRequest().put("de17", value);
			break;
			
		case "de18":
			request.setMcat(value);
			request.getRequest().put("de18", value);
			break;
			
		case "de19":
			request.getRequest().put("de19", value);
			break;

		case "de20":
			request.getRequest().put("de20", value);
			break;

		case "de21":
			request.getRequest().put("de21", value);
			break;

		case "de22":
			request.setPanEntry(value);
			request.getRequest().put("de22", value);
			break;

		case "de23":
			request.getRequest().put("de23", value);
			break;

		case "de24":
			request.getRequest().put("de24", value);
			break;
			
		case "de25":
			request.setCondCode(value);
			request.getRequest().put("de25", value);
			break;
			
		case "de26":
			request.getRequest().put("de26", value);
			break;
	
		case "de27":
			request.getRequest().put("de27", value);
			break;	

		case "de28":
			request.getRequest().put("de28", value);
			break;
			
		case "de29":
			request.getRequest().put("de29", value);
			break;	

		case "de30":
			request.setCondCode(value);
			request.getRequest().put("de30", value);
			break;
		
		case "de31":
			request.getRequest().put("de31", value);
			break;
			
		case "de32":
			request.getRequest().put("de32", value);
			break;

		case "de33":
			request.getRequest().put("de33", value);
			break;
	
		case "de34":
			request.getRequest().put("de34", value);
			break;	

		case "de35":
			request.setTrack2(value);
			request.getRequest().put("de35", value);
			break;
			
		case "de36":
			request.getRequest().put("de36", value);
			break;	

		case "de37":
			request.getRequest().put("de37", EBCDIC_TO_String(value));
			break;

		case "de38":
			request.getRequest().put("de38", EBCDIC_TO_String(value));
			break;

		case "de39":
			request.getRequest().put("de39", EBCDIC_TO_String(value));
			break;
		
		case "de40":
			request.getRequest().put("de40", value);
			break;	
			
		case "de41":
			request.setTid(value);
			request.getRequest().put("de41", EBCDIC_TO_String(value));
			break;
			
		case "de42":
			request.setMid(value);
			request.getRequest().put("de42", EBCDIC_TO_String(value));
			break;
			
		case "de43":
			request.getRequest().put("de43", EBCDIC_TO_String(value));
			break;
			
		case "de44":
			request.getRequest().put("de44", value);
			break;
		
		case "de45":
			request.getRequest().put("de45", value);
			break;
			
		case "de46":
			request.getRequest().put("de46", value);
			break;
			
		case "de47":
			request.getRequest().put("de47", value);
			break;	
			
		case "de48":
			request.getRequest().put("de48", value);
			break;
			
		case "de49":
			request.getRequest().put("de49", value);
			break;
			
		case "de50":
			request.getRequest().put("de50", value);
			break;
			
		case "de51":
			request.getRequest().put("de51", value);
			break;
			
		case "de52":
			request.setPac_1(value);
			request.getRequest().put("de52", value);
			break;
			
		case "de53":
			request.getRequest().put("de53", value);
			break;
			
		case "de54":
			request.getRequest().put("de54", EBCDIC_TO_String(value));
			break;
			
		case "de55":
			request.getRequest().put("de55", value);
			break;
			
		case "de56":
			request.getRequest().put("de56", value);
			break;	
			
		case "de57":
			request.getRequest().put("de57", value);
			break;
			
		case "de58":
			request.getRequest().put("de58", value);
			break;
			
		case "de59":
			request.getRequest().put("de59", value);
			break;
			
		case "de60":
			request.getRequest().put("de60", value);
			break;
		
		case "de61":
			request.getRequest().put("de61", value);
			break;
			
		case "de62":
			request.getRequest().put("de62", value);
			break;
			
		case "de63":
			request.getRequest().put("de63", value);
			break;
			
		case "de64":
			request.getRequest().put("de64", value);
			break;
			
		case "de65":
			request.getRequest().put("de65", value);
			break;	
			
		case "de66":
			request.getRequest().put("de66", value);
			break;
		
		case "de67":
			request.getRequest().put("de67", value);
			break;	
			
		case "de68":
			request.getRequest().put("de68", value);
			break;
			
		case "de69":
			request.getRequest().put("de69", value);
			break;
			
		case "de70":
			request.getRequest().put("de70", value);
			break;
		
		case "de71":
			request.getRequest().put("de71", value);
			break;
			
		case "de72":
			request.getRequest().put("de72", value);
			break;
			
		case "de73":
			request.getRequest().put("de73", value);
			break;	
			
		case "de74":
			request.getRequest().put("de74", value);
			break;
			
		case "de75":
			request.getRequest().put("de75", value);
			break;
			
		case "de76":
			request.getRequest().put("de76", value);
			break;
			
		case "de77":
			request.getRequest().put("de77", value);
			break;
		
		case "de78":
			request.getRequest().put("de78", value);
			break;
		
		case "de79":
			request.getRequest().put("de79", value);
			break;
			
		case "de80":
			request.getRequest().put("de80", value);
			break;
			
		case "de81":
			request.getRequest().put("de81", value);
			break;
	
		case "de82":
			request.getRequest().put("de82", value);
			break;	
			
		case "de83":
			request.getRequest().put("de83", value);
			break;
			
		case "de84":
			request.getRequest().put("de84", value);
			break;
			
		case "de85":
			request.getRequest().put("de85", value);
			break;
			
		case "de86":
			request.getRequest().put("de86", value);
			break;
			
		case "de87":
			request.getRequest().put("de87", value);
			break;
			
		case "de88":
			request.getRequest().put("de88", value);
			break;
			
		case "de89":
			request.getRequest().put("de89", value);
			break;
			
		case "de90":
			request.getRequest().put("de90", value);
			break;
			
		case "de91":
			request.getRequest().put("de91", value);
			break;
			
		case "de92":
			request.getRequest().put("de92", value);
			break;
			
		case "de93":
			request.getRequest().put("de93", value);
			break;
			
		case "de94":
			request.getRequest().put("de94", value);
			break;	
			
		case "de95":
			request.getRequest().put("de95", value);
			break;
			
		case "de96":
			request.getRequest().put("de96", value);
			break;	
			
		case "de97":
			request.getRequest().put("de97", value);
			break;
			
		case "de98":
			request.getRequest().put("de99", value);
			break;	
			
		case "de99":
			request.getRequest().put("de99", value);
			break;
			
		case "de100":
			request.getRequest().put("de100", value);
			break;
			
		case "de102":
			request.getRequest().put("de102", value);
			break;
			
		case "de104":
			request.getRequest().put("de104", value);
			break;
			
		case "de115":
			request.getRequest().put("de115", value);
			break;
			
		case "de117":
			request.getRequest().put("de117", value);
			break;
			
		case "de118":
			request.getRequest().put("de118", value);
			break;
			
		case "de119":
			request.getRequest().put("de119", value);
			break;
			
		case "de126":
			request.getRequest().put("de126", value);
			break;

		}
	}

	public static String parseTag(TAGType tagType, String data) {
		String value = null;
		switch (tagType) {
		case B2:
			int b2Length = 4;
			value = StringUtils.substring(data, 0, b2Length);
			TransactionUtils.data = StringUtils.substring(data, b2Length);
			break;

		case B8:
			int b8Length = 16;
			value = StringUtils.substring(data, 0, b8Length);
			TransactionUtils.data = StringUtils.substring(data, b8Length);
			break;

		case F12:
			int b12Length = 24;
			value = StringUtils.substring(data, 0, b12Length);
			TransactionUtils.data = StringUtils.substring(data, b12Length);
			break;

		case F15:
			int f15Length = 30;
			value = StringUtils.substring(data, 0, f15Length);
			TransactionUtils.data = StringUtils.substring(data, f15Length);
			break;

		case F17:
			int f17Length = 34;
			value = StringUtils.substring(data, 0, f17Length);
			TransactionUtils.data = StringUtils.substring(data, f17Length);
			break;

		case F2:
			int f2Length = 4;
			value = StringUtils.substring(data, 0, f2Length);
			TransactionUtils.data = StringUtils.substring(data, f2Length);
			break;

		case F40:
			int f40Length = 80;
			value = StringUtils.substring(data, 0, f40Length);
			TransactionUtils.data = StringUtils.substring(data, f40Length);
			break;

		case F42:
			int f42Length = 84;
			value = StringUtils.substring(data, 0, f42Length);
			TransactionUtils.data = StringUtils.substring(data, f42Length);
			break;

		case F6:
			int f6Length = 12;
			value = StringUtils.substring(data, 0, f6Length);
			TransactionUtils.data = StringUtils.substring(data, f6Length);
			break;
		case F8:
			int f8Length = 16;
			value = StringUtils.substring(data, 0, f8Length);
			TransactionUtils.data = StringUtils.substring(data, f8Length);
			break;

		case F9:
			int f9Length = 18;
			value = StringUtils.substring(data, 0, f9Length);
			TransactionUtils.data = StringUtils.substring(data, f9Length);
			break;

		case H1:
			int h1Length = 1;
			value = StringUtils.leftPad(StringUtils.substring(data, 0, h1Length), h1Length, '0');
			TransactionUtils.data = StringUtils.substring(data, h1Length);
			break;

		case H10:
			int h10Length = 10;
			value = StringUtils.leftPad(StringUtils.substring(data, 0, h10Length), h10Length, '0');
			TransactionUtils.data = StringUtils.substring(data, h10Length);
			break;

		case H12:
			int h12Length = 12;
			value = StringUtils.leftPad(StringUtils.substring(data, 0, h12Length), h12Length, '0');
			TransactionUtils.data = StringUtils.substring(data, h12Length);
			break;

		case H16:
			int h16Length = 16;
			value = StringUtils.leftPad(StringUtils.substring(data, 0, h16Length), h16Length, '0');
			TransactionUtils.data = StringUtils.substring(data, h16Length);
			break;

		case H2:
			int h2Length = 2;
			value = StringUtils.leftPad(StringUtils.substring(data, 0, h2Length), h2Length, '0');
			TransactionUtils.data = StringUtils.substring(data, h2Length);
			break;

		case H3:
			int h3Length = 4;
			value = StringUtils.leftPad(StringUtils.substring(data, 0, h3Length), h3Length, '0');
			TransactionUtils.data = StringUtils.substring(data, h3Length);
			break;

		case H4:
			int h4Length = 4;
			value = StringUtils.leftPad(StringUtils.substring(data, 0, h4Length), h4Length, '0');
			TransactionUtils.data = StringUtils.substring(data, h4Length);
			break;

		case H42:
			int h42Length = 42;
			value = StringUtils.substring(data, 0, h42Length);
			TransactionUtils.data = StringUtils.substring(data, h42Length);
			break;

		case H6:
			int h6Length = 6;
			value = StringUtils.substring(data, 0, h6Length);
			TransactionUtils.data = StringUtils.substring(data, h6Length);
			break;

		case H8:
			int h8Length = 8;
			value = StringUtils.substring(data, 0, h8Length);
			TransactionUtils.data = StringUtils.substring(data, h8Length);
			break;

		case HLL:
			int last = 2;
			int length = Integer.parseInt(data.substring(0,last));
			int end = hexToDec(""+length)+last;
			value = StringUtils.substring(data, 2, end);
			TransactionUtils.data = data.substring(end);
			break;
			
		case ILL:
			int illLength = hexToDec(data.substring(0,2)) * 2;
			value = StringUtils.substring(data, 2, illLength+2);
			// value = StringUtils.substring(data, 2, illLength);
			TransactionUtils.data = data.substring(illLength+2);
			break;

		case JLL:
			int jllLength = hexToDec(data.substring(0,2)) * 2;
			value = StringUtils.substring(data, 2, jllLength);
			TransactionUtils.data = data.substring(jllLength+2);
			break;
		}
		return value;
	}

	public void tagReader(int i) {
		tagList.add("de" + (i + 1));
	}
	
	static int hexToDec(String data) {
		try {
			return Integer.parseInt(data,16);
		} catch (Exception e) {
			return 0;
		}	
	}
	
	public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
	
	static String EBCDIC_TO_String(String data) {
		try {
	         return new String(hexStringToByteArray(data), "Cp273");
		}catch (Exception e) {
			return null;
		}
	}

}
