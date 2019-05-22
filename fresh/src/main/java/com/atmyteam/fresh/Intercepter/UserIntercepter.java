package com.atmyteam.fresh.Intercepter;

import com.alibaba.fastjson.JSON;
import com.atmyteam.fresh.Mode.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserIntercepter implements HandlerInterceptor {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object handler) throws Exception{
        HttpSession httpSession = httpServletRequest.getSession();
        if(httpSession.getAttribute("Userid")!=null){
            try {
                String sessionid =stringRedisTemplate.opsForValue().get(httpSession.getAttribute("Userid"));
                System.out.println("从Redis取出的SessionID:"+sessionid);
                if (sessionid!=null && httpSession.getId().equals(sessionid)){
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        responsefalse(httpServletResponse);
        return false;

    }
    public void  responsefalse(HttpServletResponse httpServletResponse){
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try{
           httpServletResponse.getWriter().print(JSON.toJSONString(new Response<>(500,"用户尚未登录")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
