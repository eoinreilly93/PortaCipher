package com.encrpytion.porta;

import java.util.HashMap;
import java.util.Map;

public class EncryptorDecryptor {
	
	private String key;
	private Map<Character, Integer> charMapKey;
	private Map<Character, Integer> charMap;
	private final static String baseCipherRHS = "abcdefghijklm";
	private final static String baseCipherLHS = "nopqrstuvwxyz";
	private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * Constructor
	 * @param key
	 */
	public EncryptorDecryptor(String key) {
		this.key = key.toLowerCase();
		
		buildKeyMap();
		buildCharMap();
	}
	
	/**
	 * Default constructor
	 */
	public EncryptorDecryptor() {
		
	}
	
	
	/**
	 * Encrypts the input text using the given key
	 * 
	 * @param text
	 * @return
	 */
	public String encrypt(String text) {
		
		//If key is shorter than the text, repeat keyword along length of text
		if(key.length() < text.length()) {
			repeatKey(text);
		}
		
		text = text.toLowerCase();		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < text.length(); i++) {
			char textLetter = text.charAt(i);
			char keyLetter = key.charAt(i);
						
			//Char to encode's position in alphabet
			int textCharacterIndex = alphabet.indexOf(textLetter);
			
			//Only encrypt letters
			if(Character.isLetter(textLetter)) {
				
				//If character is in the second half of cipher A-M
				if(charMap.get(textLetter) > 12 && charMap.get(textLetter) <= 25) {
					
					int keyValue = charMapKey.get(keyLetter);
					String cipher = shiftLeft(baseCipherRHS, keyValue);					
					sb.append(cipher.charAt(textCharacterIndex % 13));
				}
				else {
					int keyValue = charMapKey.get(keyLetter);
					String cipher = shiftRight(baseCipherLHS, keyValue);
					sb.append(cipher.charAt(textCharacterIndex % 13));
				}
			}
			else {
				sb.append(text.charAt(i));
			}					
		}
		
		return sb.toString();
	}
	
	/**
	 * Decrypts the input text using the given key
	 * 
	 * @param text
	 * @return
	 */
	public String decrypt(String text) {
		
		//Porta encryption is symmetric so no additional decryption implementation is necessary
		return encrypt(text);
	}
	
	/**
	 * Shifts the cypher down by key character value. 
	 * Removes x characters from front of the cipher, and adds them to the end
	 * @param text
	 * @param x
	 */
	public String shiftRight(String text, int x) {
		
		String toShift = text.substring(0, x);
		String newCipher = text += toShift;
		String result = newCipher.substring(toShift.length(), newCipher.length());
		
		return result;
		
	}
	
	/**
	 * Removes x characters from the end of the cipher, and adds them to the front
	 * @param text
	 * @param x
	 * @return
	 */
	public String shiftLeft(String text, int x) {
		String toShift = text.substring(text.length() - x , text.length());
		String newCipher = toShift += text;
		String result = newCipher.substring(0, toShift.length() - x);
		
		return result;
	}
	
	
	/**
	 * Builds map for input text characters 
	 */
	private void buildCharMap() {
		charMap = new HashMap<Character , Integer>();
		charMap.put('a', 0);
		charMap.put('b', 1);
		charMap.put('c', 2);
		charMap.put('d', 3);
		charMap.put('e', 4);
		charMap.put('f', 5);
		charMap.put('g', 6);
		charMap.put('h', 7);
		charMap.put('i', 8);
		charMap.put('j', 9);
		charMap.put('k', 10);
		charMap.put('l', 11);
		charMap.put('m', 12);
		charMap.put('n', 13);
		charMap.put('o', 14);
		charMap.put('p', 15);
		charMap.put('q', 16);
		charMap.put('r', 17);
		charMap.put('s', 18);
		charMap.put('t', 19);
		charMap.put('u', 20);
		charMap.put('v', 21);
		charMap.put('w', 22);
		charMap.put('x', 23);
		charMap.put('y', 24);
		charMap.put('z', 25);	
	}
	
	/**
	 * Builds map for key characters
	 */
	private void buildKeyMap() {
		charMapKey = new HashMap<Character , Integer>();
		charMapKey.put('a', 0);
		charMapKey.put('b', 0);
		charMapKey.put('c', 1);
		charMapKey.put('d', 1);
		charMapKey.put('e', 2);
		charMapKey.put('f', 2);
		charMapKey.put('g', 3);
		charMapKey.put('h', 3);
		charMapKey.put('i', 4);
		charMapKey.put('j', 4);
		charMapKey.put('k', 5);
		charMapKey.put('l', 5);
		charMapKey.put('m', 6);
		charMapKey.put('n', 6);
		charMapKey.put('o', 7);
		charMapKey.put('p', 7);
		charMapKey.put('q', 8);
		charMapKey.put('r', 8);
		charMapKey.put('s', 9);
		charMapKey.put('t', 9);
		charMapKey.put('u', 10);
		charMapKey.put('v', 10);
		charMapKey.put('w', 11);
		charMapKey.put('x', 11);
		charMapKey.put('y', 12);
		charMapKey.put('z', 12);
	}
	
	/**
	 * Repeats the key so it is the same length as the input text
	 * @param text
	 */
	private void repeatKey(String text) {
		int inputLength = text.length();
		int delta = inputLength - key.length();
		String temp = key;
		
		if(delta > key.length()) {			
			//TODO: Could potentially be refactored to be a bit more efficient when the key becomes longer than the text
			for(int i = 0; i < (delta / key.length()) + 1; i++) {
				key += temp;
			}
		}
		else {
			String charToShift = key.substring(0, delta);
			key += charToShift;
		}
	}
}
