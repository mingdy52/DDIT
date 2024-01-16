package kr.or.ddit.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.jasypt.util.text.AES256TextEncryptor;

public class PasswordUtilsText {
	static AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
	
	public static void encryptText (String plain) throws Exception {
		textEncryptor.setPassword(plain);
		String myEncryptedText = textEncryptor.encrypt(plain);
		String plainText = textEncryptor.decrypt(myEncryptedText);
	}
	
	public static void decryptText (String plain) throws Exception {
		String plainText = textEncryptor.decrypt(plain);
	}
		
		
		

	
	
}
