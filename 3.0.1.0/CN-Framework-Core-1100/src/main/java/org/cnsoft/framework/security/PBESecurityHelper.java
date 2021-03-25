package org.cnsoft.framework.security;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.cnsoft.framework.constants.ICFrameworkConstants;

/**
 * 加密解密工具类
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class PBESecurityHelper implements ICFrameworkConstants {
	private final static int ITERATIONS = 20;

	/**
	 * 加密
	 * 
	 * @param key
	 *            加密密匙
	 * @param plainText
	 *            需加密内容
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String plainText) throws Exception {
		try {
			byte[] salt = new byte[8];
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes(SYSTEM_CHARSET));
			byte[] digest = md.digest();
			for (int i = 0; i < 8; i++) {
				salt[i] = digest[i];
			}
			PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(pbeKeySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes(SYSTEM_CHARSET));
			String saltString = new String(Base64.encodeBase64(salt));
			String ciphertextString = new String(Base64.encodeBase64(cipherText));
			return saltString + ciphertextString;
		} catch (Exception e) {
			throw new Exception("Encrypt Text Error:" + e.getMessage(), e);
		}
	}

	/**
	 * 解密
	 * 
	 * @param key
	 *            解密密匙
	 * @param encryptTxt
	 *            需解密内容
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String key, String encryptTxt) throws Exception {
		int saltLength = 12;
		try {
			String salt = encryptTxt.substring(0, saltLength);
			String ciphertext = encryptTxt.substring(saltLength, encryptTxt.length());
			byte[] saltarray = Base64.decodeBase64(salt.getBytes(SYSTEM_CHARSET));
			byte[] ciphertextArray = Base64.decodeBase64(ciphertext.getBytes(SYSTEM_CHARSET));
			PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(keySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
			byte[] plaintextArray = cipher.doFinal(ciphertextArray);
			return new String(plaintextArray, SYSTEM_CHARSET);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String encryptTxt = "";
		String plainTxt = "{\"from\":0,\"paramValue\":\"浙江杭州市有限公司\",\"size\":0}";
		try {
			System.out.println(plainTxt);
			encryptTxt = encrypt("12345678", plainTxt);
			System.out.println(encryptTxt);
			plainTxt = decrypt("12345678", encryptTxt);
			System.out.println(plainTxt);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
