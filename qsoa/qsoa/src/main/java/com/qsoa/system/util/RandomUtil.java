package com.qsoa.system.util;





import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomUtil {
    /**
     * 随机生成验证码
     * @param digit 位数 至少是1位数
     */
	private static Logger logger = LoggerFactory.getLogger(Random.class);
    public static StringBuffer getVerificationCode(int digit){
        if(digit <= 0){
            logger.warn("未生成验证码的原因:输入位数小于等于0",digit);           
            return null;
        }
        StringBuffer verificationCode = new StringBuffer(digit);
        String[][] _verificationCode = new String[digit][10];
        for(int i=0;i<digit;i++){
            int length = _verificationCode[i].length;
            for(int j=0;j<length;j++){
                _verificationCode[i][j] = String.valueOf((int)(1+Math.random()*9));
            }
        }
       for(int i=0;i<digit;i++){
            int suiji = (int)(1+Math.random()*9);
            verificationCode.append(_verificationCode[i][suiji]);
        }
        return verificationCode;
    }
    
    /**
	 * 生成uuid
	 * 
	 * @return
	 */
	public static String uuid(boolean is32bit) {
		String result = UUID.randomUUID().toString();
		if (is32bit) {
			result = result.replaceAll("-", "");
		}
		return result;
	}

	/**
	 * 生成加密盐
	 * 
	 * @return
	 */
	public static String makeSalt() {
		String[] salts = { "qwertyuiopasdfghjklzxcvbnm", "QWERTYUIOPLKJHGFDSAZXCVBNM", "1234567890",
				"~!@#$%^&*()_+{}|:?><.,;'][" };
		int saltLen = 16;
		char[] chrs = new char[saltLen];
		// 从左边生成4位盐值
		for (int i = 0; i < salts.length; i++) {
			int index = (int) (Math.random() * salts.length);
			chrs[i] = salts[i].charAt(index);
		}
		// 从右边生成补偿盐值
		for (int i = salts.length; i < saltLen; i++) {
			int arrInd = (int) (Math.random() * salts.length);
			int saltInd = (int) (Math.random() * salts[arrInd].length());
			chrs[i] = salts[arrInd].charAt(saltInd);
		}
		for (int i = 0; i < 1000; i++) {
			int indx1 = (int) (Math.random() * saltLen);
			int indx2 = (int) (Math.random() * saltLen);
			if (indx1 == indx2) {
				continue;
			}
			char temp = chrs[indx1];
			chrs[indx1] = chrs[indx2];
			chrs[indx2] = temp;
		}
		return new String(chrs);
	}
    
    public static void main(String[] args) {
        System.out.println(getVerificationCode(4));
    }
}
