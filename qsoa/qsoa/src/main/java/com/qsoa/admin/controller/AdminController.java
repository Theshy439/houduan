package com.qsoa.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qsoa.admin.pojo.AdminInfo;
import com.qsoa.admin.servier.AdminService;
import com.qsoa.system.redis.util.RedisUtil2;
import com.qsoa.system.response.Response;
import com.qsoa.system.session.MySession;
import com.qsoa.system.util.RandomUtil;
import com.qsoa.system.util.SmsUtil;

@Controller
@RequestMapping("/api/admin")
@RestController
public class AdminController {
	
	@Resource
	private AdminService adminService;
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	 @Autowired
	StringRedisTemplate stringRedisTemplate;
	 
	@Autowired
	private RedisUtil2 redisUtil2;
	
	@PostMapping("/register")
	public Response register(AdminInfo adminInfo,@RequestParam(name="yzm")String yzm) {
		try {
			String ryzm = redisUtil2.get("yzm"+adminInfo.getPhone(),String.class);
			if(ryzm == null) {
				return Response.success(200, 202, "验证码已过期，请重新获取验证码");
			}
			if(!ryzm.equals(yzm)) {
				return Response.success(200, 202, "验证码不正确，请重新输入验证码");
			}
			adminInfo.setCreateBy("高德鹏");
			adminInfo.setCreateDate(System.currentTimeMillis());
			adminInfo.setUpdateBy("高德鹏");
			adminInfo.setUpdateDate(System.currentTimeMillis());
			if(adminService.insert(adminInfo) == 1) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", adminInfo.getId());
				return Response.getResponse(200,200, "成功", map);	
			}else {
				return Response.getResponse(200,202, "注册失败", null);	
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.getResponse(500,500, "失败", null);
		}
	}
	
	@PostMapping("/login")
	public Response login(String username,String password) {
		if(username == null) {
			return Response.error(501,501, "用户名为空");
		}
		if(password == null) {
			return Response.error(501,501, "密码为空");
		}
		try {
			if(adminService.checkInfo(username, password)) {
				MySession.saveSession(adminService.getAdminInfoByUsernameAndPassword(username, password));
				return Response.success(null);
			}
			return Response.success(200, 201);
		}catch(Exception e) {
			e.printStackTrace();
			return Response.error(500, 500, "服务异常");
		}
	}
	@PostMapping("/getVC")
	public Response getVC(String phone) {
		if(phone == null) {
			return Response.error(500,501,"手机号码为空");
		}
		try {
			StringBuffer yzm = RandomUtil.getVerificationCode(6);
			redisUtil2.set("yzm"+phone, yzm,60);
			//stringRedisTemplate.opsForValue().set("yzm","23443");  
			//String result = SmsUtil.sendMsg(phone, "【河马家居】验证码："+yzm+"，请于5分钟内输入，在页面填写验证码完成验证。如非本人操作，可不予理会");
			//JSONObject result_json = JSONObject.parseObject(result);
			//if(result_json.get("respcode").equals("0")) {
				return Response.success(200,200);
			//}else {
				//return Response.success(200, 201);
			//}
		}catch(Exception e) {
			e.printStackTrace();
			return Response.error(500,502,"服务异常");
		}
	}
	@PostMapping("/setPassword")
	public Response setPassword(Integer id,String password) {
		try {
			if(adminService.setPassword(password, id)) {
				return Response.success(200, 200);
			}
			return Response.success(200, 201, "密码设置失败");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("服务异常",e,e.getMessage());
			return Response.error(500,500,"服务异常");
		}
	}
	
	@GetMapping("/getAdminInfo")
	public Response getAdminInfo() {
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("adminInfo",MySession.getSession());
			return Response.success(map);
		}catch(Exception e) {
			e.printStackTrace();
			return Response.error();
		}
	}
	
	@GetMapping("/obsole")
	public Response obsole() {
		return Response.error(500,505,"用户信息已失效，请重新登录");
	}
}
