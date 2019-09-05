package com.qsoa.system.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MySession {
	public static void saveSession(Object data){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*120);
		session.setAttribute("admin", data);
	}
	public static Object getSession(){
		try {
		    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		    HttpSession session = request.getSession();
		    return session.getAttribute("admin");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToken(Object data){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*60);
		session.setAttribute("token", data);
	}
	public static Object getToken(){
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			return session.getAttribute("token");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}