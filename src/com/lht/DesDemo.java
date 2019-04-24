package com.lht;

import com.lht.utils.DesUtils;

public class DesDemo {
	public static void main(String[] args)
	{			
		String desKey = "ajdj21od";
		String desIv ="asdf333s";
		String src="Hello 中国";
		System.out.println("原文："+src);
		String enc = DesUtils.DesEncrypt(src, desKey, desIv);
		System.out.println("密文："+enc);
		String dec = DesUtils.DesDecrypt(enc, desKey, desIv);
		System.out.println("解码："+dec);		
	}
}
