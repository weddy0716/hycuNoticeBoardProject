package com.sinho.hycu.common.util;

import java.security.MessageDigest;

public class PasswordCrypto {
	
	/**
	 * 입력된 패스워드 데이터를 해싱한다.  
	 * @param pw
	 * @return
	 */
	public static String SHA256(String pw) {
		try{

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pw.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
			
		} catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
}
