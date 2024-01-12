/**
 * 
 */
package com.megainfo.util;

/**
 * @author Ramesh Karuti
 *
 */
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESEncryption {

	private static final int KEY_LENGTH = 256;
	private static final int ITERATION_COUNT = 65536;

	public static String encrypt(String strToEncrypt, String secretKey, String salt) {

		try {

			SecureRandom secureRandom = new SecureRandom();
			byte[] iv = new byte[16];
			secureRandom.nextBytes(iv);
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);

			byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
			byte[] encryptedData = new byte[iv.length + cipherText.length];
			System.arraycopy(iv, 0, encryptedData, 0, iv.length);
			System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

			return Base64.getEncoder().encodeToString(encryptedData);
		} catch (Exception e) {
			// Handle the exception properly
			e.printStackTrace();
			return null;
		}
	}

	public static SecretKey getKeyFromPassword(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
		return secret;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// String to be encrypted
		String originalString = "rose@gmail.com";

		// Encrypt the string
		String encryptedString = AESEncryption.encrypt(originalString, AESUtil.key, AESUtil.salt);
		if (encryptedString != null) {
			System.out.println("Encrypted: " + encryptedString);
		} else {
			System.err.println("Encryption failed.");
			return;
		}

	}

}
