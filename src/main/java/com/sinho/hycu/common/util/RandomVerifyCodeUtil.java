package com.sinho.hycu.common.util;

import java.util.Random;

public class RandomVerifyCodeUtil {
	public static String generateVerifyCode() {
        // 무작위 숫자 생성을 위한 Random 객체 생성
        Random random = new Random();
        
        // StringBuilder를 사용하여 문자열 동적 생성
        StringBuilder codeBuilder = new StringBuilder();
        
        // 6번 반복하여 무작위 숫자를 생성하여 코드에 추가
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // 0부터 9까지의 숫자 중 무작위 선택
            codeBuilder.append(digit); // 코드에 숫자 추가
        }
        return codeBuilder.toString(); // 최종적으로 생성된 코드 반환
    }
}
