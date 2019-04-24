package com.lht.utils;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtils {
	//对称解密
		public static String DesDecrypt(String src,String desKey, String desIv){			
			String decryptStr = "";			
			try {
				byte[] DESkey = desKey.getBytes();
		        byte[] DESIV = desIv.getBytes();
		        DESKeySpec keySpec = new DESKeySpec(DESkey);
				AlgorithmParameterSpec  iv = new IvParameterSpec(DESIV);// 设置向量
		        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
		        SecretKey  key = keyFactory.generateSecret(keySpec);// 得到密钥对象
		        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		        deCipher.init(Cipher.DECRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
		        byte[] pasByte = deCipher.doFinal(Base64Utils.decode(src));	      
		        decryptStr = new String(pasByte, "UTF-8");;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 设置密钥参数   
			return decryptStr;	
		}
		
		
		//对称加密
		public static String DesEncrypt(String src,String desKey, String desIv){			
			String decryptStr = "";			
			try {
				byte[] DESkey = desKey.getBytes();
		        byte[] DESIV = desIv.getBytes();
		        DESKeySpec keySpec = new DESKeySpec(DESkey);
				AlgorithmParameterSpec  iv = new IvParameterSpec(DESIV);// 设置向量
		        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
		        SecretKey  key = keyFactory.generateSecret(keySpec);// 得到密钥对象
		        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		        deCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
		        byte[] pasByte = deCipher.doFinal(src.getBytes("utf-8"));	      
		        decryptStr = Base64Utils.encode(pasByte) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 设置密钥参数   
			return decryptStr;	
		}
}
