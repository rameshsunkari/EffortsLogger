package com.test.encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestEncryption {

	private static Key key;

	/**
	 * Generates the encryption key. using "des" algorithm
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	private static void generateKey() throws NoSuchAlgorithmException {
		KeyGenerator generator;
		generator = KeyGenerator.getInstance("DES");
		generator.init(new SecureRandom());
		key = generator.generateKey();
	}

	private String encrypt(String message) throws IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			UnsupportedEncodingException {
		// Get a cipher object.
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// Gets the raw bytes to encrypt, UTF8 is needed for
		// having a standard character set
		byte[] stringBytes = message.getBytes("UTF8");

		// encrypt using the cypher
		byte[] raw = cipher.doFinal(stringBytes);

		// converts to base64 for easier display.
		BASE64Encoder encoder = new BASE64Encoder();
		String base64 = encoder.encode(raw);

		return base64;
	}

	private String decrypt(String encrypted) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, IOException {

		// Get a cipher object.
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);

		// decode the BASE64 coded message
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] raw = decoder.decodeBuffer(encrypted);

		// decode the message
		byte[] stringBytes = cipher.doFinal(raw);

		// converts the decoded message to a String
		String clear = new String(stringBytes, "UTF8");
		return clear;
	}

	public static void main(String[] args) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, IOException {
		TestEncryption test = new TestEncryption();
		generateKey();
		String encrypt = test.encrypt("ramesh");
		System.out.println(encrypt);

		System.out.println(test.decrypt("a5dc8fa4b25a257a4a45718c4a5df20ad670a934"));

	}

}
