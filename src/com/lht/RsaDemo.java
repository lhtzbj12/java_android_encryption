package com.lht;

import java.security.KeyPair;

import com.lht.utils.Base64Utils;
import com.lht.utils.RSAUtils;

public class RsaDemo {
	public static void main(String[] args) {
		KeyPair keyPair = RSAUtils.generateRSAKeyPair();
		String src="Hello 中国";
		System.out.println("原文："+src);
		String enc =Base64Utils.encode(RSAUtils.encryptData(src.getBytes(),keyPair.getPublic()));
		System.out.println("密文："+enc);
		String dec =new String(RSAUtils.decryptData(Base64Utils.decode(enc), keyPair.getPrivate()));
		System.out.println("解码："+dec);		
	}
}
