package com.lht.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.Cipher;

public class RsaUtils {    
    
	public static final String SIGNATURE = "SHA256withRSA";//加密填充方式 
	
	public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";//加密填充方式 
    

    /**
     * 用公钥加密 <br>
     * 每次加密的字节数，不能超过密钥的长度值减去11
     *
     * @param data 需加密数据的byte数据
     *             公钥
     * @return 加密后的byte型数据
     */
    public static byte[] encryptData(byte[] data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            // 编码前设定编码方式及密钥
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 传入编码数据并返回编码结果
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用私钥解密
     *
     * @param encryptedData 经过encryptedData()加密返回的byte数据
     * @param privateKey    私钥
     * @return
     */
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKeyPkcs8) {
        try {
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKeyPkcs8);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 用私钥签名
     *
     * @param data 需要进行签名的数据
     * @param privateKey    私钥
     * @return 签名数据 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws SignatureException 
     */
    public static byte[] Sign(byte[] data, PrivateKey privateKeyPkcs8) {    
    	try{
    		 Signature signature = Signature.getInstance(SIGNATURE);
             signature.initSign(privateKeyPkcs8);
             signature.update(data);
             return signature.sign();
    	}catch(Exception e){
    		return null;
    	}        
    }

    /**
     * 用公钥验签
     *
     * @param data 需要进行签名的数据
     * @param publicKey    公钥
     * @return 
     */
    public static boolean Verify(byte[] data, byte[] sign, PublicKey publicKey){    	
    	try{
         Signature signature = Signature.getInstance(SIGNATURE);
         signature.initVerify(publicKey);
         signature.update(data);
         return signature.verify(sign);
    	}catch(Exception e){
    		return false;
    	}  
    }
}
