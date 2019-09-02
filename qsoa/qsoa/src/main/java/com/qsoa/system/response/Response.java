package com.qsoa.system.response;

import java.util.Map;

public class Response {
	private static Response response;

	private int status;
	
	private int code;

	private String message;

	private Map<String, Object> data;

	public Response() {
		// TODO Auto-generated constructor stub
	}

	public static Response getResponse() {
		if (response == null) {
			response = new Response();
		}
		return response;
	}

	public static Response getResponse(int status,int code,String message,Map<String, Object> data) {
		Response response = new Response();
		response.status = status;
		response.code = code;
		response.message = message;
		response.data = data;
		return response;	
	}
	
	public static Response success(int status,int code) {
		return getResponse(status,code, "成功", null);
	}
	
	
	public static Response success(Map<String, Object> data) {
		return getResponse(200,200,"成功",data);
	}
	
	public static Response success(String message,Map<String, Object> data) {
		return getResponse(200,200, message, data);
	}
	
	public static Response error() {
		return getResponse(500,500, "失败", null);
	}
	
	public static Response error(int status,int code,String message) {
		return getResponse(status,code, message, null);
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public static void setResponse(Response response) {
		Response.response = response;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
}
