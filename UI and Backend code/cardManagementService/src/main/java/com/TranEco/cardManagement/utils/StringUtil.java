
package com.TranEco.cardManagement.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil
{
   static final private Log log = LogFactory.getLog(StringUtil.class);

   /**
    * Set the Version to the current ITM Release level.
    */
   private final static String Version = "41Z";

   /**
    * Add 1 to the BuildSeq on each PTF when the Version does not change.
    * If you change the Version, set the BuildSeq to 1. 
    */
   private final static String BuildSeq = "1";

   /**
    * Set the PTF Number to the PTF Number of the current PTF.
    */
   private final static String PTFNumber = "SYS41Z0500";

   /**
    * Returns the Build Sequence Number.
    * @return the Build Sequence Number.
    */
   public static String getBuildSeq()
   {
      return BuildSeq;
   }

   /**
    * Returns the PTF Number.
    * @return the PTF Number.
    */
   public static String getPTFNumber()
   {
      return PTFNumber;
   }

   /**
    * Returns the Version Number.
    * @return the Version Number.
    */
   public static String getVersion()
   {
      return Version;
   }

   /**
    * Returns "ClassName: Version.Build#.PTFNumber".
    * @return the Release Level.
    */
   public static String getReleaseLevel()
   {
      return "StringUtil: " + Version + "." + BuildSeq + "." + PTFNumber;
   }

   /**
    * 
    * Function will pad a string with passed pad character to passed target
    * length
    * @param sTarget
    * @param iTargetLength
    * @param cPadChar
    * @param cJustification 
    * @return
    */
   public static String pad(String sTarget, int iTargetLength, char cPadChar,
         char cJustification)
   {
      if ( sTarget == null )
         sTarget = "";
      else
         sTarget = trimRight(sTarget);

      int length = sTarget.length();
      if ( length == iTargetLength )
      {
         if ( cJustification == 'R' && cPadChar == '0' )
         {
            try
            {
               if ( Long.parseLong(sTarget) < 0 )
               {
                  StringBuffer buffer = new StringBuffer(sTarget);
                  buffer.deleteCharAt(0);
                  buffer = negativeToASCII(buffer);
                  buffer.insert(0, cPadChar);
                  sTarget = buffer.toString();
               }
            }
            catch ( Exception e )
            {

            }
         }
         return sTarget;
      }
      else if ( length > iTargetLength )
      {
         if ( cJustification == 'L' )
         {
            return sTarget.substring(0, iTargetLength);
         }
         else
         {

            int remaining = length - iTargetLength;

            if ( cPadChar == '0' )
            {
               try
               {
                  if ( Long.parseLong(sTarget) < 0 )
                  {
                     StringBuffer buffer = new StringBuffer(sTarget);
                     buffer.deleteCharAt(0);
                     buffer = negativeToASCII(buffer);
                     buffer.insert(0, cPadChar);
                     sTarget = buffer.toString();
                  }
               }
               catch ( Exception e )
               {

               }
            }
            return sTarget.substring(remaining, length);
         }
      }
      else
      {
         int remaining = iTargetLength - length;
         StringBuffer buffer = new StringBuffer(sTarget);

         if ( cJustification == 'R' && cPadChar == '0' )
         {
            try
            {
               if ( Long.parseLong(sTarget) < 0 )
               {
                  buffer.deleteCharAt(0);
                  buffer = negativeToASCII(buffer);
                  buffer.insert(0, cPadChar);
               }
            }
            catch ( Exception e )
            {

            }
         }

         for ( int i = 0; i < remaining; i++ )
         {
            if ( cJustification == 'L' )
            {
               buffer.append(cPadChar);
            }
            else
            {
               buffer.insert(0, cPadChar);
            }
         }
         return buffer.toString();
      }
   }

   /**
    * Converts a Negative sign to ASCII Char.
    * @param input - Buffer Srting must be less than 20 digit
    * @return - A string Buffer 
    */

   public static StringBuffer negativeToASCII(StringBuffer buffer)
   {
      switch ( buffer.charAt(buffer.toString().length() - 1) )
      {

         case '0':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "}");
            break;
         }
         case '1':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "J");
            break;
         }
         case '2':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "K");
            break;
         }
         case '3':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "L");
            break;
         }
         case '4':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "M");
            break;
         }
         case '5':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "N");
            break;
         }
         case '6':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "O");
            break;
         }
         case '7':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "P");
            break;
         }
         case '8':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "Q");
            break;
         }
         case '9':
         {
            buffer.replace(buffer.toString().length() - 1, buffer.toString()
                  .length(), "R");
            break;
         }

      }

      return buffer;
   }

   /**
    * Removes leading characters from a string
    * @param target - the starting string
    * @param minLength - the minimum length to return.  If the target is shorter than 
    * minLengh, the target will not be modified.
    * @param padChar - the character to remove from the start of the string
    * @return
    */
   public static String removePad(String target, int minLength, char padChar)
   {
      String result;
      if ( target.length() == 0 )
      {
         result = "";
      }
      else
      {
         result = target;
      }
      while ( (result.length() > minLength) && (result.charAt(0) == padChar) )
      {
         result = result.substring(1);
      }

      return result;
   }

   /**
    * Checks to see if all characters in a string are numeric (0-9).
    * @param testString - the string to test
    * @return true if all characters in the test string are numeric.  false if
    * any non-numeric character exists in the string.
    */
   public static boolean isNumeric(String testString)
   {
      int i;
      boolean numeric = true;

      if ( testString.length() > 0 )
      {
         for ( i = 0; i < testString.length() && numeric == true; i++ )
         {
            if ( (testString.charAt(i) < '0') || (testString.charAt(i) > '9') )
            {
               numeric = false;
            }
         }
      }
      else
      {
         numeric = false;
      }

      return numeric;
   }

   /**
    * Converts a hex string into the corresponding binary string
    * @param input Incoming HexString
    * @return the exploded binary string
    */
   public static String HexStringToBinaryString(String input)
   {
      String sReturn = "";

      for ( int i = 0; i < input.length(); ++i )
      {
         switch ( input.charAt(i) )
         {
            case '0':
               sReturn += "0000";
               break;
            case '1':
               sReturn += "0001";
               break;
            case '2':
               sReturn += "0010";
               break;
            case '3':
               sReturn += "0011";
               break;
            case '4':
               sReturn += "0100";
               break;
            case '5':
               sReturn += "0101";
               break;
            case '6':
               sReturn += "0110";
               break;
            case '7':
               sReturn += "0111";
               break;
            case '8':
               sReturn += "1000";
               break;
            case '9':
               sReturn += "1001";
               break;
            case 'A':
               sReturn += "1010";
               break;
            case 'B':
               sReturn += "1011";
               break;
            case 'C':
               sReturn += "1100";
               break;
            case 'D':
               sReturn += "1101";
               break;
            case 'E':
               sReturn += "1110";
               break;
            case 'F':
               sReturn += "1111";
               break;
         }
      }

      return sReturn;
   }

   /**
    * Converts a binary string into the corresponding hex string
    * @param input Incoming Binary String
    * @return the compressed hex string
    */
   public static String BinaryStringToHexString(String input)
   {
      String sReturn = "";
      String nibble = "";

      for ( int i = 0; i < input.length(); i += 4 )
      {
         nibble = input.substring(i, i + 4);

         if ( nibble.equals("0000") )
         {
            sReturn += "0";
         }
         else if ( nibble.equals("0001") )
         {
            sReturn += "1";
         }
         else if ( nibble.equals("0010") )
         {
            sReturn += "2";
         }
         else if ( nibble.equals("0011") )
         {
            sReturn += "3";
         }
         else if ( nibble.equals("0100") )
         {
            sReturn += "4";
         }
         else if ( nibble.equals("0101") )
         {
            sReturn += "5";
         }
         else if ( nibble.equals("0110") )
         {
            sReturn += "6";
         }
         else if ( nibble.equals("0111") )
         {
            sReturn += "7";
         }
         else if ( nibble.equals("1000") )
         {
            sReturn += "8";
         }
         else if ( nibble.equals("1001") )
         {
            sReturn += "9";
         }
         else if ( nibble.equals("1010") )
         {
            sReturn += "A";
         }
         else if ( nibble.equals("1011") )
         {
            sReturn += "B";
         }
         else if ( nibble.equals("1100") )
         {
            sReturn += "C";
         }
         else if ( nibble.equals("1101") )
         {
            sReturn += "D";
         }
         else if ( nibble.equals("1110") )
         {
            sReturn += "E";
         }
         else if ( nibble.equals("1111") )
         {
            sReturn += "F";
         }
      }

      return sReturn;
   }

   /**
    * Converts a hex string to a decimal string
    * @param input - the hex string to convert.  This string must be contains 4 byte
    * instances of 0s and 1s.
    * @return - a decimal representation of the input string
    */
   public static String binaryHexStringToDecimalString(String input)
   {
      String result = "";
      String nibble = "";
      int value = 0;

      for ( int i = 0; i < input.length(); i += 4 )
      {
         if ( input.length() >= (i + 4) )
            nibble = input.substring(i, i + 4);
         else
         {
            nibble = "";
            log.error("Remaining input string is less than 4 bytes");
         }

         if ( nibble.equals("0000") )
         {
            value = (value * 16) + 0;
         }
         else if ( nibble.equals("0001") )
         {
            value = (value * 16) + 1;
         }
         else if ( nibble.equals("0010") )
         {
            value = (value * 16) + 2;
         }
         else if ( nibble.equals("0011") )
         {
            value = (value * 16) + 3;
         }
         else if ( nibble.equals("0100") )
         {
            value = (value * 16) + 4;
         }
         else if ( nibble.equals("0101") )
         {
            value = (value * 16) + 5;
         }
         else if ( nibble.equals("0110") )
         {
            value = (value * 16) + 6;
         }
         else if ( nibble.equals("0111") )
         {
            value = (value * 16) + 7;
         }
         else if ( nibble.equals("1000") )
         {
            value = (value * 16) + 8;
         }
         else if ( nibble.equals("1001") )
         {
            value = (value * 16) + 9;
         }
         else if ( nibble.equals("1010") )
         {
            value = (value * 16) + 10;
         }
         else if ( nibble.equals("1011") )
         {
            value = (value * 16) + 11;
         }
         else if ( nibble.equals("1100") )
         {
            value = (value * 16) + 12;
         }
         else if ( nibble.equals("1101") )
         {
            value = (value * 16) + 13;
         }
         else if ( nibble.equals("1110") )
         {
            value = (value * 16) + 14;
         }
         else if ( nibble.equals("1111") )
         {
            value = (value * 16) + 15;
         }
      }

      result = String.valueOf(value);

      return result;
   }

   /**
    * Converts a decimal string value to binary
    * @param input - the decimal string to convert.  It must be a valid integer
    * @param returnLen - the return length.  If the converted string is not this long, 0s
    * will be added to the front
    * @return binary string representation of the input string
    */
   public static String DecimalStringToBinaryString(String input, int returnLen)
   {
      String result = "";
      int intValue;

      try
      {
         intValue = Integer.parseInt(input);
      }
      catch ( Exception e )
      {
         intValue = 0;
      }

      while ( intValue >= 1 )
      {
         //result += String.valueOf(intValue % 2);
         result = String.valueOf(intValue % 2) + result;
         intValue = intValue / 2;
      }

      result = pad(result, returnLen, '0', 'R');

      return result;
   }

   /**
    * Tests the input string for characters 0-9, A-F
    * @param input
    * @return true if all characters in the string are valid Hex digits
    */
   public static boolean isHex(String input)
   {
      boolean result = true;
      String test = input.toUpperCase();
      int i;
      char testChar;

      for ( i = 0; i < test.length(); i++ )
      {
         testChar = test.charAt(i);
         if ( (testChar < '0' && testChar > '9')
               && (testChar < 'A' && testChar > 'F') )
            result = false;
      }

      return result;
   }

   /**
    * Trims spaces from the input string
    * @param input - the string to trim
    * @return the input string without any trailing blanks
    */
   public static String trimRight(String input)
   {
      String result;
      int len;

      result = input;
      len = result.length();
      while ( len > 0 && result.charAt(len - 1) == ' ' )
      {
         result = result.substring(0, len - 1);
         len = result.length();
      }

      return result;
   }

   /**
    * Escapes the 'input' String to Unicode characters and 
    * translates the Unicode characters to HTML characters
    * @param input The string to convert
    * @return The HTML string for the 'input' String
    */
   public static String convertStringToHTMLString(String input)
   {
      String output = input;

      try
      {
         byte[] utf8 = output.getBytes("UTF-8");
         output = new String(utf8, "UTF-8");
      }
      catch ( UnsupportedEncodingException e )
      {
         output = input;
      }
      output = escapeUnicodeString(output, true);
      output = convertUnicodeToHTML(output);

      return output;
   }

   /**
    * Converts a UTF-8 string to HTML unicode. An example character is \u5e94,
    * which is a Chinese character. After running through this function, \u5e94
    * becomes &#x5e94;
    * 
    * @param text -
    *            The UTF-8 string to translate.
    * @return The HTML compliant string for the input parameter, text.
    */
   private static String convertUnicodeToHTML(String text)
   {
      int pos;
      int i = 0;

      pos = text.indexOf("\\u");
      while ( i < text.length() && pos >= 0 )
      {
         if ( pos == 0 )
            text = "&#x" + text.substring(pos + 2);
         else
            text = text.substring(0, pos) + ";&#x" + text.substring(pos + 2);
         pos = text.indexOf("\\u");
      }
      text = text + ";";

      return text;
   }

   /**
    * Builds the Unicode string for str.
    * @param str
    * @param escapeAscii
    * @return
    */
   private static String escapeUnicodeString(String str, boolean escapeAscii)
   {
      StringBuffer ostr = new StringBuffer();
      for ( int i = 0; i < str.length(); i++ )
      {
         char ch = str.charAt(i);
         if ( !escapeAscii && ((ch >= 0x0020) && (ch <= 0x007e)) )
         {
            ostr.append(ch);
         }
         else
         {
            ostr.append("\\u");
            String hex = Integer.toHexString(str.charAt(i) & 0xFFFF);
            for ( int j = 0; j < 4 - hex.length(); j++ )
               ostr.append("0");
            ostr.append(hex.toUpperCase(Locale.ENGLISH));
         }
      }
      return (new String(ostr));
   }
   
   public static String getExStackTrace(Exception e) 
   {
	   StringWriter sw = new StringWriter();
       e.printStackTrace(new PrintWriter(sw));
       String exceptionAsString = sw.toString();
		return exceptionAsString;
	}
   
  public static String getCurrentDateTime() {
	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      return String.valueOf(timestamp);
  }
}

