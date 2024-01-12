/**
 * 
 */
package com.megainfo.util;

/**
 * @author ramesh
 *
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESPasswordBased {

	private static final String ALGORITHM = "AES";
	//private static final byte[] keyValue = "1234567891234567".getBytes();

	public static void main(String args[]) throws Exception {
		String encriptValue = encrypt("test", AESUtil.key);
		decrypt(encriptValue, AESUtil.key);

	}

	private static SecretKey  generateKey(String key) throws Exception {
		byte[] keyValue = key.getBytes();
		SecretKey  key1 = new SecretKeySpec(keyValue, ALGORITHM);
		return key1;
	}

	public static String encrypt(String valueToEnc, String key) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));

		byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
		byte[] encryptedByteValue = new org.apache.commons.codec.binary.Base64().encode(encValue);
		System.out.println("Encrypted Value :: " + new String(encryptedByteValue));

		return new String(encryptedByteValue);
	}

	public static String decrypt(String encryptedValue, String key) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, generateKey(key));

		byte[] decodedBytes = new org.apache.commons.codec.binary.Base64().decode(encryptedValue.getBytes());
		byte[] enctVal = cipher.doFinal(decodedBytes);

		System.out.println("Decrypted Value :: " + new String(enctVal));
		return new String(enctVal);
	}

}