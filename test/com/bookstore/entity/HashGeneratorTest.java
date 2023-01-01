package com.bookstore.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bookstore.service.HashGenerator;

public class HashGeneratorTest {

	@Test
	public void testGenerateMD5() {
		String password ="mysecret";
		String encryptedPassword =HashGenerator.generateMD5(password);
		
		System.out.println(encryptedPassword);

		assertTrue(true);
	}

}
