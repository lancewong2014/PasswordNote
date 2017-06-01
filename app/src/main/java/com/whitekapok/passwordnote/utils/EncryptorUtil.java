package com.whitekapok.passwordnote.utils;


import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncryptorUtil {
	/**
	 * MD5加密
	 *
	 * @param data 明文字符串
	 * @return 16进制密文
	 */
	public static String encryptMD5ToString(String data) {
		return encryptMD5ToString(data.getBytes());
	}

	/**
	 * MD5加密
	 *
	 * @param data 明文字符串
	 * @param salt 盐
	 * @return 16进制加盐密文
	 */
	public static String encryptMD5ToString(String data, String salt) {
		return bytes2HexString(encryptMD5((data + salt).getBytes()));
	}

	/**
	 * MD5加密
	 *
	 * @param data 明文字节数组
	 * @return 16进制密文
	 */
	public static String encryptMD5ToString(byte[] data) {
		return bytes2HexString(encryptMD5(data));
	}

	/**
	 * MD5加密
	 *
	 * @param data 明文字节数组
	 * @param salt 盐字节数组
	 * @return 16进制加盐密文
	 */
	public static String encryptMD5ToString(byte[] data, byte[] salt) {
		if (data == null || salt == null) return null;
		byte[] dataSalt = new byte[data.length + salt.length];
		System.arraycopy(data, 0, dataSalt, 0, data.length);
		System.arraycopy(salt, 0, dataSalt, data.length, salt.length);
		return bytes2HexString(encryptMD5(dataSalt));
	}

	/**
	 * MD5加密
	 *
	 * @param data 明文字节数组
	 * @return 密文字节数组
	 */
	public static byte[] encryptMD5(byte[] data) {
		return hashTemplate(data, "MD5");
	}

	/**
	 * hash加密模板
	 *
	 * @param data      数据
	 * @param algorithm 加密算法
	 * @return 密文字节数组
	 */
	private static byte[] hashTemplate(byte[] data, String algorithm) {
		if (data == null || data.length <= 0) return null;
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(data);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	///////////////////////////////////////////////////////////////////////////
	// DES加密相关
	///////////////////////////////////////////////////////////////////////////

	/**
	 * DES转变
	 * <p>法算法名称/加密模式/填充方式</p>
	 * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
	 * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
	 */
	public static String DES_Transformation = "DES/ECB/NoPadding";
	private static final String DES_Algorithm = "DES";

	/**
	 * DES加密后转为Base64编码
	 *
	 * @param data 明文
	 * @param key  8字节秘钥
	 * @return Base64密文
	 */
	public static byte[] encryptDES2Base64(byte[] data, byte[] key) {
		return base64Encode(encryptDES(data, key));
	}

	/**
	 * DES加密后转为16进制
	 *
	 * @param data 明文
	 * @param key  8字节秘钥
	 * @return 16进制密文
	 */
	public static String encryptDES2HexString(byte[] data, byte[] key) {
		return bytes2HexString(encryptDES(data, key));
	}

	/**
	 * DES加密
	 *
	 * @param data 明文
	 * @param key  8字节秘钥
	 * @return 密文
	 */
	public static byte[] encryptDES(byte[] data, byte[] key) {
		return desTemplate(data, key, DES_Algorithm, DES_Transformation, true);
	}

	/**
	 * DES解密Base64编码密文
	 *
	 * @param data Base64编码密文
	 * @param key  8字节秘钥
	 * @return 明文
	 */
	public static byte[] decryptBase64DES(byte[] data, byte[] key) {
		return decryptDES(base64Decode(data), key);
	}

	/**
	 * DES解密16进制密文
	 *
	 * @param data 16进制密文
	 * @param key  8字节秘钥
	 * @return 明文
	 */
	public static byte[] decryptHexStringDES(String data, byte[] key) {
		return decryptDES(hexString2Bytes(data), key);
	}

	/**
	 * DES解密
	 *
	 * @param data 密文
	 * @param key  8字节秘钥
	 * @return 明文
	 */
	public static byte[] decryptDES(byte[] data, byte[] key) {
		return desTemplate(data, key, DES_Algorithm, DES_Transformation, false);
	}

	///////////////////////////////////////////////////////////////////////////
	// AES加密相关
	///////////////////////////////////////////////////////////////////////////

	/**
	 * AES转变
	 * <p>法算法名称/加密模式/填充方式</p>
	 * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
	 * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
	 */
	public static String AES_Transformation = "AES/ECB/NoPadding";
	private static final String AES_Algorithm = "AES";


	/**
	 * AES加密后转为Base64编码
	 *
	 * @param data 明文
	 * @param key  16、24、32字节秘钥
	 * @return Base64密文
	 */
	public static byte[] encryptAES2Base64(byte[] data, byte[] key) {
		return base64Encode(encryptAES(data, key));
	}

	/**
	 * AES加密后转为16进制
	 *
	 * @param data 明文
	 * @param key  16、24、32字节秘钥
	 * @return 16进制密文
	 */
	public static String encryptAES2HexString(byte[] data, byte[] key) {
		return bytes2HexString(encryptAES(data, key));
	}

	/**
	 * AES加密
	 *
	 * @param data 明文
	 * @param key  16、24、32字节秘钥
	 * @return 密文
	 */
	public static byte[] encryptAES(byte[] data, byte[] key) {
		return desTemplate(data, key, AES_Algorithm, AES_Transformation, true);
	}

	/**
	 * AES解密Base64编码密文
	 *
	 * @param data Base64编码密文
	 * @param key  16、24、32字节秘钥
	 * @return 明文
	 */
	public static byte[] decryptBase64AES(byte[] data, byte[] key) {
		return decryptAES(base64Decode(data), key);
	}

	/**
	 * AES解密16进制密文
	 *
	 * @param data 16进制密文
	 * @param key  16、24、32字节秘钥
	 * @return 明文
	 */
	public static byte[] decryptHexStringAES(String data, byte[] key) {
		return decryptAES(hexString2Bytes(data), key);
	}

	/**
	 * AES解密
	 *
	 * @param data 密文
	 * @param key  16、24、32字节秘钥
	 * @return 明文
	 */
	public static byte[] decryptAES(byte[] data, byte[] key) {
		return desTemplate(data, key, AES_Algorithm, AES_Transformation, false);
	}

	/**
	 * DES加密模板
	 *
	 * @param data           数据
	 * @param key            秘钥
	 * @param algorithm      加密算法
	 * @param transformation 转变
	 * @param isEncrypt      {@code true}: 加密 {@code false}: 解密
	 * @return 密文或者明文，适用于DES，3DES，AES
	 */
	public static byte[] desTemplate(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt) {
		if (data == null || data.length == 0 || key == null || key.length == 0) return null;
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
			Cipher cipher = Cipher.getInstance(transformation);
			SecureRandom random = new SecureRandom();
			cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, random);
			return cipher.doFinal(data);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	/**
	 * byteArr转hexString
	 * <p>例如：</p>
	 * bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
	 *
	 * @param bytes 字节数组
	 * @return 16进制大写字符串
	 */
	private static String bytes2HexString(byte[] bytes) {
		if (bytes == null) return null;
		int len = bytes.length;
		if (len <= 0) return null;
		char[] ret = new char[len << 1];
		for (int i = 0, j = 0; i < len; i++) {
			ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
			ret[j++] = hexDigits[bytes[i] & 0x0f];
		}
		return new String(ret);
	}


	/**
	 * hexString转byteArr
	 * <p>例如：</p>
	 * hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
	 *
	 * @param hexString 十六进制字符串
	 * @return 字节数组
	 */
	private static byte[] hexString2Bytes(String hexString) {
		if (isSpace(hexString)) return null;
		int len = hexString.length();
		if (len % 2 != 0) {
			hexString = "0" + hexString;
			len = len + 1;
		}
		char[] hexBytes = hexString.toUpperCase().toCharArray();
		byte[] ret = new byte[len >> 1];
		for (int i = 0; i < len; i += 2) {
			ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
		}
		return ret;
	}

	/**
	 * hexChar转int
	 *
	 * @param hexChar hex单个字节
	 * @return 0..15
	 */
	private static int hex2Dec(char hexChar) {
		if (hexChar >= '0' && hexChar <= '9') {
			return hexChar - '0';
		} else if (hexChar >= 'A' && hexChar <= 'F') {
			return hexChar - 'A' + 10;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Base64编码
	 *
	 * @param input 要编码的字节数组
	 * @return Base64编码后的字符串
	 */
	private static byte[] base64Encode(byte[] input) {
		return Base64.encode(input, Base64.NO_WRAP);
	}

	/**
	 * Base64解码
	 *
	 * @param input 要解码的字符串
	 * @return Base64解码后的字符串
	 */
	private static byte[] base64Decode(byte[] input) {
		return Base64.decode(input, Base64.NO_WRAP);
	}

	private static boolean isSpace(String s) {
		if (s == null) return true;
		for (int i = 0, len = s.length(); i < len; ++i) {
			if (!Character.isWhitespace(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
