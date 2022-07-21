package kr.or.ddit.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtils {
	
	public static boolean matche(String plain/*클라이언트 입력 비밀번호*/, String savedData/*디비저장 암호화 비밀번호*/) {
		String encoded = encryptSha512(plain);
		return savedData.equals(encoded);
	}

	public static String encryptSha512(String plain) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(plain.getBytes());
		System.out.println(encrypted.length * 8);
		// 어떤 입력 데이터가 들어가도 나오는 값은 똑같음. --> 해시 데이타
		// 입력 데이터의 길이가 길수록 나올 수 있는 경우의 수는 많음. -> 발생할 수 있는 입력데이터의 충돌을 줄이기 위해 비밀번호 길이 제한함.
		
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		// Base64 : 
		System.out.printf("평문 : %s, 암호문 : %s\n", plain, encoded);
		
		return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
