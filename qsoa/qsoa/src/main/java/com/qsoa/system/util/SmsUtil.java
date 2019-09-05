package com.qsoa.system.util;

import java.util.HashMap;
import java.util.Map;

public class SmsUtil {
	public static String sendMsg(String phone,String msg) {
		HttpClientUtil hcu = new HttpClientUtil();
		String uri = "http://www.qybor.com:8500/shortMessage";
		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		Map<String, String> params = new HashMap<>();
		params.put("username", "hema01");
		params.put("passwd", "hmjj190215");
		params.put("phone", phone);
		params.put("msg", msg);
		params.put("needstatus", "true");
		String result=hcu.postSend(uri, contentType, params);
		System.out.println(result);
		return result;
	}

	public static void main(String[] args) {
		SmsUtil.sendMsg("13268515903", "【河马家居】验证码："+RandomUtil.getVerificationCode(6)+"，请于5分钟内输入，在页面填写验证码完成验证。如非本人操作，可不予理会");
	}
}
