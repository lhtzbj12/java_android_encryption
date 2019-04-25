package com.lht;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.lht.utils.*;

public class RsaDemo {
	// 字符串形式 的 公钥
	public static final String publicKey ="MIGdMA0GCSqGSIb3DQEBAQUAA4GLADCBhwKBgQCp5yx4iMT2A1eb4VLg2JuR5VQR4nGjuWD46ctopbEaW59a5LZ3X4d0M7i93dNDQUiWrbHVKJSpGr7tvGBEqFaLbhTswudBXyk7/5tmaVDQ0bW6IvtgEUnHmxvYP/q/vOvyXa+h2qmJTrxZcsMtCJ7O46R6e0g+40rbgFZsk3GGjQIBAw==";
			
	// 字符串形式 的 Pkcs8格式的私钥
	public static final String privateKeyPk8="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKnnLHiIxPYDV5vhUuDYm5HlVBHicaO5YPjpy2ilsRpbn1rktndfh3QzuL3d00NBSJatsdUolKkavu28YESoVotuFOzC50FfKTv/m2ZpUNDRtboi+2ARScebG9g/+r+86/Jdr6HaqYlOvFlywy0Ins7jpHp7SD7jStuAVmyTcYaNAgEDAoGAASH3w10SYTait6cMf8rUqe7Bae68fa2b9AGPAmeBLkF20oalDnL7yHRl7MkoqWiKuVMT9XlxoY3U5kc6qx/Xne6XxlOaiJMpklcXo2jAFWrGjz5+3ifIK0IhYGDyJrrsKECycApVUuBCpylqxCe7TRQ4V7K9cTL74mJeWlmT+MUCQQDTgsqwmvfqTffEKPb9r4y5pRLooc/ndqPmVZ+r09rm56LGA4GDp/uuTrAMdnF/708jfuMV59cqNRPROgXCxIbnAkEAzaPtFcBDOn+FM5iwCxS3h7i4nANk2onK9Te/xkQzT6S5rx56SQL+OV+3Ib3MQPBV5yI0qmAT2X8iC9N2U/k8awJBAI0B3HW8pUbepS1wpKkfsyZuDJsWippPF+7jv8fike9FFy6tAQJv/R7fIAhO9lVKNMJUl2Pv5MbODTYmroHYWe8CQQCJF/Nj1YIm/64iZcqyDc+v0HsSrO3nBodOJSqELXeKbdEfaabbV1QmP89r092AoDlEwXhxlWKQ/2wH4k7ipihHAkEAqC6106DHu92pUucma+5F9rU1s3VKd3wSwFcsYh4ITbvHX+TIt2SgTnpjj1Z6fBMZ8eK89lKPu7X2rBVbYMxKZQ==";
		
	public static void main(String[] args) throws Exception {
		RasTest();
		RsaTestWithCsharp();
		
		RsaSignTest();
		RsaSignTestWithCsharp();
	}
	
	public static void RasTest(){		
		// 可以自己生成密钥
		//KeyPair keyPair = RsaKeyHelper.generateRSAKeyPair(1024);		
		try {
			PrivateKey priKey = RsaKeyHelper.loadPrivateKey(privateKeyPk8);
			PublicKey pubKey = RsaKeyHelper.loadPublicKey(publicKey);
			String src="Hello 中国";
			 System.out.println("--------加密 解密---------");	
			System.out.println("原文："+src);
			String enc =Base64Utils.encode(RsaUtils.encryptData(src.getBytes(),pubKey));
			System.out.println("密文："+enc);
			String dec =new String(RsaUtils.decryptData(Base64Utils.decode(enc), priKey));
			System.out.println("解码："+dec);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void RsaSignTest() {
		
		try {
			PrivateKey priKey = RsaKeyHelper.loadPrivateKey(privateKeyPk8);
			PublicKey pubKey = RsaKeyHelper.loadPublicKey(publicKey);
			String src = "Hello 中国，这是一段很神奇的代码";
	        byte[] signedData = RsaUtils.Sign(src.getBytes(),priKey);
	        Boolean success = RsaUtils.Verify(src.getBytes(), signedData, pubKey);
	        System.out.println("--------签名  验签---------");	
	        System.out.println("原文：" + src);
	        System.out.println("签名：" + Base64Utils.encode(signedData)); // 签名生成byte[]经过Base64编码后显示或者传输
	        System.out.println("验签：" + success);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
	public static void RsaTestWithCsharp(){
		// C#使用上述publicKey加密生成的密文
		String enc="G0nfGX4KtFShih9TXA4IaBC9lMwcW4Zz2Ch+jCfdVQRUkPRrT9nQei3x89KZ2rHpR3kApGQbbxT4TWvGTx9TN00HpRULk6jGbd+zPR+B+ktauaWrD37h816k+Ry++5oln5iC6lPH2rGLY7PEVFHryesu1dCZ84MPfIHHW1X9vBU=";
		try {
			PrivateKey privateKey = RsaKeyHelper.loadPrivateKey(privateKeyPk8);
			String dec = new String(RsaUtils.decryptData(Base64Utils.decode(enc), privateKey)); // 需要将密文进行Base64解码后再进行解密
			System.out.println("--------解密C#加密的内容---------");			
			System.out.println("解码："+dec);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void RsaSignTestWithCsharp(){
		// C#使用上述privateKey 生成的签名
		String src = "Hello 中国，这是一段很神奇的代码";
		String sign="R9SpLyk04Oq0/GLlnR8rJEHIsXu06c/ZGwBDiikbPDcQcM/oNwuzoLjfCLVfkv5MoBW9RtnmRnv9AdkHX+5IH/xBlL1PjT3nG5XVR1xT9TUmXX39UqzFu+TsrH3AVfKIj9xmdsxB62DeiapqkpOWIHVPwFQtsF95N++z1jC3CdE=";
		try{
		PublicKey pubKey = RsaKeyHelper.loadPublicKey(publicKey);	        
        Boolean success = RsaUtils.Verify(src.getBytes(), Base64Utils.decode(sign), pubKey); // 需要将签名进行Base64解码后再进行验证
		System.out.println("--------验证C#签名的内容---------");	
        System.out.println("原文：" + src);       
        System.out.println("验签：" + success);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
