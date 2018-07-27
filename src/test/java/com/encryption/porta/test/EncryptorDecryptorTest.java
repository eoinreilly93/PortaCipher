package com.encryption.porta.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.encrpytion.porta.EncryptorDecryptor;

@RunWith(JUnit4.class)
public class EncryptorDecryptorTest {

	@Test
	public void encryptTest() {
		String key = "abcd";
		String inputText = "eoin";
		String expectedOutputText = "rbwm";
		
		EncryptorDecryptor ed = new EncryptorDecryptor(key);
		String result = ed.encrypt(inputText);
		assertEquals(expectedOutputText, result);
	}
	
	@Test
	public void encryptTest2() {
		String key = "OBJECTORIENTATEDSOFTWAREDEVELOPMENT";
		String inputText = "THECURFEWTOLLSTHEKNELLOFPARTINGDAY";
		String expectedOutputText = "MUVRGIZZFEIUYJEVNRLNWYGUBPHENGNWPF";
		
		EncryptorDecryptor ed = new EncryptorDecryptor(key);
		String result = ed.encrypt(inputText);
		assertEquals(expectedOutputText.toLowerCase(), result);
	}
	
	@Test
	public void encryptSmallKeyTest1() {
		
		String key = "test";
		String inputText = "longer";
		String expectedOutputText = "umepnc";
		
		EncryptorDecryptor ed = new EncryptorDecryptor(key);
		String result = ed.encrypt(inputText);
		assertEquals(expectedOutputText.toLowerCase(), result);
	}
	
	@Test
	public void encryptSmallKeyTest2() {
		
		String key = "test";
		String inputText = "longerlonger";
		String expectedOutputText = "umepncufevni";
		
		EncryptorDecryptor ed = new EncryptorDecryptor(key);
		String result = ed.encrypt(inputText);
		assertEquals(expectedOutputText.toLowerCase(), result);
	}
	
	@Test
	public void decryptTest() {
		
		String key = "OBJECTORIENTATEDSOFTWAREDEVELOPMENT";
		String inputText = "MUVRGIZZFEIUYJEVNRLNWYGUBPHENGNWPF";
		String expectedOutputText = "THECURFEWTOLLSTHEKNELLOFPARTINGDAY";
		
		EncryptorDecryptor ed = new EncryptorDecryptor(key);
		String result = ed.decrypt(inputText);
		assertEquals(expectedOutputText.toLowerCase(), result);
	}
	
	@Test
	public void shiftRightTest1() {
		String cipher = "nopqrstuvwxyz"; 
		int x = 1;
		String expectedResult = "opqrstuvwxyzn";
		
		EncryptorDecryptor ed = new EncryptorDecryptor();
		String result = ed.shiftRight(cipher, x);
		assertEquals(expectedResult, result);
		
	}
	
	@Test
	public void shiftRightTest2() {
		String cipher = "nopqrstuvwxyz"; 
		int x = 4;
		String expectedResult = "rstuvwxyznopq";
		
		EncryptorDecryptor ed = new EncryptorDecryptor();
		String result = ed.shiftRight(cipher, x);
		assertEquals(expectedResult, result);
		
	}
	
	@Test
	public void shiftLeftTest1() {
		String cipher = "abcdefghijklm";
		int x = 1;
		String expectedResult = "mabcdefghijkl";
		
		EncryptorDecryptor ed = new EncryptorDecryptor();
		String result = ed.shiftLeft(cipher, x);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void shiftLeftTest2() {
		String cipher = "abcdefghijklm"; 
		int x = 4;
		String expectedResult = "jklmabcdefghi";
		
		EncryptorDecryptor ed = new EncryptorDecryptor();
		String result = ed.shiftLeft(cipher, x);
		assertEquals(expectedResult, result);
		
	}
}
