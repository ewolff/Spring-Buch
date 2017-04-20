package main;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Main {
	
	public static void main(String[] args) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		System.out.println(encoder.encodePassword("wolff", null));
	}

}
