package com.sinho.hycu.common.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 게시판 제목 및 내용을 암복호화하기위한 유틸 클래스이다.
 * @author weddy
 *
 */
public class BoardContentsCrypto {
	
	//KEY는 PROPERTY로 뺴야한다.
	private static final String KEY = "HYCU2022107598SH";
	
	// AES 키 생성
	private static SecretKey generateAESKey(String key) throws Exception {
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
	    return new SecretKeySpec(keyBytes, "AES");
	}
	
	// 문자열 암호화
	public static String encrypt(String plainText) throws Exception {
	    Cipher cipher = Cipher.getInstance("AES");
	    SecretKey secretKey = generateAESKey(KEY);
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
	    return Base64.getEncoder().encodeToString(encryptedBytes);
	}
	
	// 문자열 복호화
	public static String decrypt(String encryptedText) throws Exception {
	    Cipher cipher = Cipher.getInstance("AES");
	    SecretKey secretKey = generateAESKey(KEY);
	    cipher.init(Cipher.DECRYPT_MODE, secretKey);
	    byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
	    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
	    return new String(decryptedBytes, StandardCharsets.UTF_8);
	}
}


   
