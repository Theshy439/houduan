package com.qsoa.system.util;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5Util {
	/**
	 * MD5加密字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	/**
	 * 生成密码，双md5+盐
	 * 
	 * @param pwd
	 * @param salt
	 * @return
	 */
	public static String makePwd(String pwd, String salt) {
		pwd = md5Hex(pwd +salt);
//		char[] chrs = new char[48];
//		for (int i = 0; i < chrs.length; i += 3) {
//			chrs[i] = pwd.charAt(i / 3 * 2);
//			chrs[i + 1] = salt.charAt(i / 3);
//			chrs[i + 2] = pwd.charAt(i / 3 * 2 + 1);
//		}
		return new String(pwd);
	}

	public static boolean verifyPwd(String pwd, String md5) {
		char[] ch1 = new char[32];
		char[] ch2 = new char[16];
		for (int i = 0; i < 48; i += 3) {
			ch1[i / 3 * 2] = md5.charAt(i);
			ch1[i / 3 * 2 + 1] = md5.charAt(i + 2);
			ch2[i / 3] = md5.charAt(i + 1);
		}
		String salt = new String(ch2);
		System.out.println(salt);
		System.out.println(md5Hex(pwd + salt));
		System.out.println(new String(ch1));
		return md5Hex(pwd + salt).equals(new String(ch1));
	}
	
	public static void main(String[] args) {
		String salt = RandomUtil.makeSalt();
		/*System.out.println(salt);
		String pwd=MD5Util.makePwd("123456",salt);
		System.out.println(pwd);
		System.out.println(MD5Util.verifyPwd("123456", pwd));*/
		System.out.println(MD5Util.makePwd("zzxx2580@", salt));
		System.out.println(salt);
	}
}