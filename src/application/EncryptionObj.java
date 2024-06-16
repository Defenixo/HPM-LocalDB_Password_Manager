package application;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionObj {
	
	
	
	private static SecretKeySpec einsecHOUSAINIPASS, einsec;
	private static byte[] stringtobyte;
	
	
	private static void HashFunc(String keystr) {
		MessageDigest hashval;
		try {
		hashval = MessageDigest.getInstance("SHA-256");
		stringtobyte = Arrays.copyOf(hashval.digest(keystr.getBytes(StandardCharsets.UTF_8)), 32);
		einsecHOUSAINIPASS = new SecretKeySpec(stringtobyte, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	private static void HashFuncOLD(String keystr) throws NoSuchAlgorithmException {
		MessageDigest hashval = MessageDigest.getInstance("SHA-1");
		stringtobyte = Arrays.copyOf(hashval.digest(keystr.getBytes(StandardCharsets.UTF_8)), 32);
		//System.out.println(stringtobyte);
		einsec = new SecretKeySpec(stringtobyte, "AES");
	}
	
	protected static String hashKey(String hashkeystring) throws NoSuchAlgorithmException, InvalidKeySpecException {
		MessageDigest hashval = MessageDigest.getInstance("SHA-256");
		stringtobyte = Arrays.copyOf(hashval.digest(hashkeystring.getBytes(StandardCharsets.UTF_8)), 256);
        BigInteger number = new BigInteger(1, stringtobyte);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
	}
	
	protected static String EncryptFuncHOUSAINIPASS(String toEnc, String theKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		HashFunc(theKey);
		Cipher itemCipher;
		try {
			itemCipher = Cipher.getInstance("AES");
		itemCipher.init(Cipher.ENCRYPT_MODE, einsecHOUSAINIPASS);
		String encoded = Base64.getEncoder().encodeToString(itemCipher.doFinal(toEnc.getBytes("UTF-8")));
		return encoded;
		} catch (Exception e) {
			e.printStackTrace();
		} return null;
	}
	
	protected static String DecryptFuncHOUSAINIPASS(String toDec, String theKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		HashFunc(theKey);
		System.out.println(einsecHOUSAINIPASS.toString());
		Cipher itemCipher;
		try {
			itemCipher = Cipher.getInstance("AES");
		itemCipher.init(Cipher.DECRYPT_MODE, einsecHOUSAINIPASS);
		return new String(itemCipher.doFinal(Base64.getDecoder().decode(toDec.getBytes("UTF-8"))));
		} catch (Exception e) {
			e.printStackTrace();
		} return null;
	}
	
	protected static String EncryptFunc(String toEnc, String theKey) throws NoSuchAlgorithmException {
		HashFuncOLD(theKey);
		Cipher itemCipher;
		try {
			itemCipher = Cipher.getInstance("AES");
		itemCipher.init(Cipher.ENCRYPT_MODE, einsec);
		String encoded = Base64.getEncoder().encodeToString(itemCipher.doFinal(toEnc.getBytes("UTF-8")));
		return encoded;
		} catch (Exception e) {
			//e.printStackTrace();
		} return null;
	}
	
	protected static String DecryptFunc(String toDenc, String theKey) throws NoSuchAlgorithmException {
		HashFuncOLD(theKey);
		Cipher itemCipher;
		try {
			itemCipher = Cipher.getInstance("AES");
		itemCipher.init(Cipher.DECRYPT_MODE, einsec);
		return new String(itemCipher.doFinal(Base64.getDecoder().decode(toDenc)));
		} catch (Exception e) {
			//e.printStackTrace();
		} return null;
	}
}