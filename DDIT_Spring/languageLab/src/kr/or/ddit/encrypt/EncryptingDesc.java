package kr.or.ddit.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.jasypt.util.text.AES256TextEncryptor;

/**
 * 
 * Encrypting(암호화) / Decrypting(복호화) [hash 함수]: 데이터 보호를 목적으로 허가받지 않은 사용자가 읽을 수 없는 표현방식으로 데이터 표현을 바꾸는 과정
 * 	단방향 암호화(hash 함수) : 암호화 가능, 복호화 불가. 비민번호 암호화에 사용. ex) SHA-512
 * 	hash ?? 입력데이터에 대해 일정 길이로 만들어지는 코드값.
 * 시스템 매체 이해 목적 없음. 권한이 없으면 읽을 수 없음.
 * 
 * 	양방향 암호화 : 암호화, 복호화 가능. 전송 데이터 보호에 사용. 전자 서명에 사용. (Java Crypto Library - Cipher)
 * 		대칭키 방식(비밀키 방식) : 하나의 비밀키로 암호화와 복호화를 수행. ex)AES(128) -- 키가 같이 넘어가야 하기 때문에 위험함.
 * 		비대칭키 방식(공개키 방식) : 한 쌍의 키(공개키/개인키)로 암복호화 수행 ex)RSA -- 속도가 느림
 * 
 * Encoding(부호화) / Decoding(복호화) : 데이터를 전송하거나 저장하기 위해 사용할 매체가 인지할 수 있는 표현방식으로 데이터의 표현을 바꾸는 과정.
 *		ex) URLEncoding(Percent Encoding), Base64
 *		권한(key)이 있다면 복화하할 수 있음. 어떤 시스템이나 매체가 이용
 */
public class EncryptingDesc {
	public static void main(String[] args) throws Exception {
		String plain = "java";
		
		AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword(plain);
		String myEncryptedText = textEncryptor.encrypt(plain);
		String plainText = textEncryptor.decrypt(myEncryptedText);
		
		System.out.println(myEncryptedText);
		System.out.println(plainText);
		
	}
	
	
	/**
	 * SHA-512알고리즘으로 단방향 암호화 후 Base64로 인코딩함.
	 * @param plain
	 * @return
	 * @throws Exception
	 */
	public static String encryptSha512(String plain) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] encrypted = md.digest(plain.getBytes());
		System.out.println(encrypted.length * 8);
		// 어떤 입력 데이터가 들어가도 나오는 값은 똑같음. --> 해시 데이타
		// 입력 데이터의 길이가 길수록 나올 수 있는 경우의 수는 많음. -> 발생할 수 있는 입력데이터의 충돌을 줄이기 위해 비밀번호 길이 제한함.
		
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		// Base64 : 
		System.out.printf("평문 : %s, 암호문 : %s\n", plain, encoded);
		
		return encoded;
	}
	
	public static void encryptAESExample(String plain) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 암호화 알고리즘 / 앞블럭과 뒷블럭을 연관시키는 알고리즘 / 패딩 알고리즘
		
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256); // 16바이트
		// AES: 128, 256. 근데 제약조건 때문에 128밖에 못씀. 블럭 암호화 방식
		
		SecretKey key = keyGen.generateKey();
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] iv = md.digest("password".getBytes()); // 128비트 배열
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		// 초기화 벡터
		
		cipher.init(cipher.ENCRYPT_MODE, key, ivSpec);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.printf("평문 : %s, 암호문 : %s\n", plain, encoded);
		
		byte[] decoded = Base64.getDecoder().decode(encoded); // 암호원문. encrypted와 같음.
		cipher.init(cipher.DECRYPT_MODE, key, ivSpec);
		byte[] decrypted = cipher.doFinal(decoded); // input 과 같음.
		System.out.printf("복호화된 평문 : %s\n", new String(decrypted));
		// 블럭 암호화 알고리즘 구동 방식 때문에 에러남.
	}
	
	public static void encryptRSAExample(String plain) throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// RSA: 길이 1024, 2048 이 있음.
		// 안전하지만 속도가 느림
		
		keyPairGen.initialize(2048);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		Cipher cipher = Cipher.getInstance("RSA"); // 블럭 암호화 방식을 사용하지 않음.
		cipher.init(cipher.ENCRYPT_MODE, publicKey);
		byte[] input = plain.getBytes();
		byte[] encrypted = cipher.doFinal(input);
		String encoded = Base64.getEncoder().encodeToString(encrypted);
		System.out.printf("평문 : %s, 암호문 : %s\n", plain, encoded);
		
		byte[] decoded = Base64.getDecoder().decode(encoded);
		cipher.init(cipher.DECRYPT_MODE, privateKey);
		byte[] decrypted = cipher.doFinal(decoded);
		System.out.printf("복호화된 평문 : %s\n", new String(decrypted));
	}
	
}
