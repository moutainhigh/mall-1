package com.yunxin.cb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ECMd5Utils {
	
	/**
	 * 私有构造函数，不允许本类生成实例 
	 */
	private ECMd5Utils(){
		
	}
	
	private static final String sv = "0ploi98kmjuy76hnbgt54r8vcde62wsxzaq1";
	
	

	/**
	 * 用MD5算法进行加密
	 * @param pStrPW
	 * @return
	 */
	public static String crypt(String pStrPW) {
		String strCrypt = hash(pStrPW);
		if(strCrypt.length() > 0) {
			strCrypt += sv;
			strCrypt = hash(strCrypt);
		}
		
		return strCrypt;
	}
	/**
	 * 用MD5算法进行加密(16位)
	 * @param strPW
	 * @return
	 */
	public static String encryptMD5(String strPW) {
		String strCrypt = hash(strPW);
		if(strCrypt.length() > 0) {
			strCrypt += sv;
			strCrypt = hash(strCrypt);
		}
		
		return strCrypt.substring(8,24);
	}
	
	/**
	 * MD5算法进行散列
	 * @param str
	 * @return
	 */
	public static String hash(String str) {
		String result = "";
		if (str == null || str.equals("")) { // 如果传入参数为空，则返回空字符串
			return result;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] data = str.getBytes();
			int l = data.length;
			for (int i = 0; i < l; i++)
				md.update(data[i]);
			byte[] digest = md.digest();
			
			result = ECByteUtils.byteArrayToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(),e);
		}

		return result;		
	}
	
	
	/**
	 * MD5算法进行散列
	 * @param str
	 * @return
	 */
	public static String hash16(String str) {
		String result = "";
		if (str == null || str.equals("")) { // 如果传入参数为空，则返回空字符串
			return result;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] data = str.getBytes();
			int l = data.length;
			for (int i = 0; i < l; i++)
				md.update(data[i]);
			byte[] digest = md.digest();
			
			result = ECByteUtils.byteArrayToHexString(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(),e);
		}

		return result;		
	}

	public static void main(String args[]) {
		String pwd = "123456";
		if (args != null && args.length > 0) {
			pwd = args[0];
		}
		
		try {
			System.out.println(ECMd5Utils.hash(pwd));
			System.out.println(ECMd5Utils.crypt(pwd));
			System.out.println(ECMd5Utils.encryptMD5(pwd));
			System.out.println(MD5Encode(pwd,null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * =======================================laiding begin===========================================
	 */
	/**
	 * 来定项目由于会员是导入所以使用原有数据
	 */	
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	private static String byteArrayToHexString(byte[] b)
	 {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	 }
	 
	 private static String byteToHexString(byte b)
	 {
	   int n = b;
	   if (n < 0) {
	     n += 256;
	   }
	   int d1 = n / 16;
	   int d2 = n % 16;
	   return hexDigits[d1] + hexDigits[d2];
	 }
	 
	 public static String MD5Encode(String origin, String charsetname)
	 {
	   String resultString = null;
	   try
	   {
	     resultString = new String(origin);
	     MessageDigest md = MessageDigest.getInstance("MD5");
	     if ((charsetname == null) || ("".equals(charsetname))) {
	       resultString = byteArrayToHexString(md.digest(resultString
	         .getBytes()));
	     } else {
	       resultString = byteArrayToHexString(md.digest(resultString
	         .getBytes(charsetname)));
	     }
	   }
	   catch (Exception exception) {}
	   return resultString;
	 }
	 /**
		 * =======================================laiding end===========================================
		 */
	 
}
