package com.ulsa.main.utils;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public String encode(String passWord) {
	    BCryptPasswordEncoder bCryptPasswordEncoder =
	            new BCryptPasswordEncoder(10, new SecureRandom());
	    return bCryptPasswordEncoder.encode(passWord);

	}

}
