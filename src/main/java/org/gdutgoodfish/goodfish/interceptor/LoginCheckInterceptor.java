package org.gdutgoodfish.goodfish.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.exception.userException.TokenException;
import org.gdutgoodfish.goodfish.pojo.common.UserContext;
import org.gdutgoodfish.goodfish.pojo.entity.Users;
import org.gdutgoodfish.goodfish.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //1.获取url
        String url = request.getRequestURI();
        log.info("请求路径：{}", url);

        //2.判断是否是登录相关的资源路径
        if (url.contains("login") || url.contains("register")) {
            //是登录相关的资源，放行
            log.info("放行url{}", url);
            return true;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        Claims claims;
        //5.解析token
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            log.info("token校验失败");
            throw new TokenException("请先登录");
        }
        String user = claims.getSubject();
        Users users = JSONObject.parseObject(user, Users.class);
        UserContext.setCurrentId(users.getId());
        //6.放行
        log.info("token验证通过{}", UserContext.getCurrentId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeCurrentId();
    }
}
