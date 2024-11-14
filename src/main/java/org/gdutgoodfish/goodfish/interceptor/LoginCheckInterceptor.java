package org.gdutgoodfish.goodfish.interceptor;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.entity.Result;
import org.gdutgoodfish.goodfish.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    /**
     * 拦截器的前置方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.获取url
        String url = request.getRequestURI();
        log.info("请求路径：{}",url);

        //2.判断是否是登录相关的资源路径
        if (url.contains("login")){
            //是登录相关的资源，放行
            log.info("登录操作，放行");
            return true;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在
        if (!StringUtils.hasLength(token)){
            log.info("未登录，请先登录");
            Result result = Result.error("未登录，请先登录");

            //手动转换为json字符串
            String notLogin = JSONObject.toJSONString(result);
            response.getWriter().write(notLogin);
            return false;
        }

        //5.解析token
        try {
            JwtUtil.phaseJwt(token);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = Result.error("未登录，请先登录");

            //手动转换为json字符串
            String notLogin = JSONObject.toJSONString(result);
            response.getWriter().write(notLogin);
            return false;
        }

        //6.检查是否在黑名单中
        if (JwtUtil.isTokenBlacklisted(token)) {
            log.info("token已失效");
            Result result = Result.error("token已失效");

            //手动转换为json字符串
            String notLogin = JSONObject.toJSONString(result);
            response.getWriter().write(notLogin);
            return false;
        }

        //7.放行
        log.info("token验证通过");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle");
        return;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("aftercompletion");
        return;
    }
}
