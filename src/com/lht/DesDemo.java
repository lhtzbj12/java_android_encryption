package com.lht;

import com.lht.utils.DesUtils;

public class DesDemo {
	public static void main(String[] args)
	{			
		String key = "aksjwj2w";
		String iv = "jahajhgj";
		String src="Hello 中国";
		System.out.println("原文："+src);
		String enc = DesUtils.DesEncrypt(src, key, iv);
		System.out.println("密文："+enc);
		// 下面这段是来自C#加密的代码， key iv相同时可直接进行解密
		// enc = "0Ur3OuYW25GHVGkmLE0kdPo1UY9RchTt6MTEtHKYiAl43Cv1BbhPUPVYFdawMd7+";
		String dec = DesUtils.DesDecrypt(enc, key, iv);
		System.out.println("解码："+dec);		
	}
}
