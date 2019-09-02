package com.qsoa.system.response;

import java.util.Map;

public class Response {
	private static Response response;

	private int status;

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

	public static Response getResponse(int status,String message,Map<String, Object> data) {
		Response response = new Response();
		response.status = status;
		response.message = message;
		response.data = data;
		return response;	
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
	
	
	
}
