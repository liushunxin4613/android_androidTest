package util;

public class UnicodeUtil {
	
	/**
	 * �ַ���ת��unicode
	 */
	public static String string2Unicode(String string) {
	    StringBuffer unicode = new StringBuffer();
	    for (int i = 0; i < string.length(); i++) {
	        // ȡ��ÿһ���ַ�
	        char c = string.charAt(i);
	        // ת��Ϊunicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	    return unicode.toString();
	}
	
	/**
	 * unicode ת�ַ���
	 */
	public static String unicode2String(String unicode) {
	    StringBuffer string = new StringBuffer();
	    String[] hex = unicode.split("\\\\u");
	    for (int i = 1; i < hex.length; i++) {
	        // ת����ÿһ�������
	        int data = Integer.parseInt(hex[i], 16);
	        // ׷�ӳ�string
	        string.append((char) data);
	    }
	    return string.toString();
	}
}
