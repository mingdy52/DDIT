package kr.or.ddit.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
	
	String plain = "java";
	
	@Test
	public void testNoOPPasswordEncoder() {
		PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
		String encoded = encoder.encode(plain);
		System.out.println(encoded);
	}
	
	@Test
	public void testDelegatingPasswordEncoder() {
		Map<String, PasswordEncoder> idToPasswordEncoder = new LinkedHashMap<>();
		idToPasswordEncoder.put("noOP", NoOpPasswordEncoder.getInstance());
		idToPasswordEncoder.put("bcrypt", new BCryptPasswordEncoder());
		DelegatingPasswordEncoder encoder = new DelegatingPasswordEncoder("bcrypt", idToPasswordEncoder);
		String encoded = encoder.encode(plain);
		System.out.println(encoded);
	}
	
	@Test
	public void testPasswordEncoderFactories() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encoded = encoder.encode(plain);
		System.out.println(encoded);
	}
}












