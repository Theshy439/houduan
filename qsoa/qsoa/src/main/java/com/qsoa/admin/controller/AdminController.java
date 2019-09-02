package com.qsoa.admin.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qsoa.admin.pojo.AdminInfo;
import com.qsoa.admin.servier.AdminService;
import com.qsoa.system.response.Response;

@Controller
@RequestMapping("/api/admin")
@RestController
public class AdminController {
	
	@Resource
	private AdminService adminService;
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@PostMapping("/register")
	public Response register(AdminInfo adminInfo) {
		try {
			adminInfo.setCreateBy("高德鹏");
			adminInfo.setCreateDate(System.currentTimeMillis());
			adminInfo.setUpdateBy("高德鹏");
			adminInfo.setUpdateDate(System.currentTimeMillis());
			adminService.insert(adminInfo);
			return Response.getResponse(200,200, "成功", null);	
		}catch(Exception e) {
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
				return Response.success(null);
			}
			return Response.success(200, 201);
		}catch(Exception e) {
			return Response.error(500, 500, "服务异常");
		}
	}
}
