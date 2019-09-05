package com.qsoa.system.config.fifter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qsoa.system.session.MySession;



public class LoginInterceptor implements HandlerInterceptor	 {

   private  Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        logger.info("---------------------开始进入请求地址拦截----------------------------");
        //System.out.println(httpServletRequest.getSession().getAttribute("userId"));
        System.out.println(MySession.getSession());
        if(MySession.getSession() == null) {
        	logger.info("---------------------服务器中没有用户的有关标识，不能放行，重定向到返回重新登录信息的接口----------------------------");
        	httpServletResponse.sendRedirect("/api/admin/obsole");
        	return false;
        }
        logger.info("---------------------服务器中有用户的有关标识，放行----------------------------");
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("---------------视图渲染之后的操作-------------------------0");
    }

	

	
}
