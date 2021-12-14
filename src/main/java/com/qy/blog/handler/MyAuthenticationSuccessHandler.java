package com.qy.blog.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qy.blog.entity.Constants;
import com.qy.blog.entity.UserResult;
import com.qy.blog.pojo.User;
import com.qy.blog.service.UserService;
import com.qy.blog.utils.JwtUtil;
import com.qy.blog.utils.RedisUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 登录成功逻辑
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private UserService userService;

    @Resource
    private JedisPool jedisPool;

    @Resource
    private RedisUtil redisUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 获取前端数据
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        // 根据前端传回的用户名，查询数据
        User user = userService.findByUsername(principal.getUsername());

        // 获取用户权限
        Object[] authoritiesObj = authentication.getAuthorities().toArray();
        // 创建list集合
        List<String> authorities = new ArrayList<>();
        // 遍历权限集合
        for (Object o : authoritiesObj) {
            if (o.toString().contains("ADMIN")){
                //Collections.addAll(authorities, o.toString());
                // 将权限存入list集合
                Collections.addAll(authorities,"管理员");
            }

        }

        // 将权限信息存入redis
        UserResult userResult = new UserResult();
        userResult.setId(user.getId());
        userResult.setAvatar(user.getAvatar());
        userResult.setUsername(user.getUsername());
        userResult.setRoleList(authorities);

        // 过期时间60分钟
        redisUtil.setCacheObject("userInfo", userResult,60 * 60, TimeUnit.SECONDS);

        // 生成token，过期时间为60分钟
        String token = JwtUtil.signToken(principal.getUsername());

        // token存入redis 60分钟
        jedisPool.getResource().setex(
                "adminToken",60 * 60,token);

        // 返回json给前端
        try {
            response.setContentType(Constants.JSON_CONTENT_TYPE);
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("code", 200);
            resultMap.put("msg", "认证通过！");
            resultMap.put("token", Constants.TOKEN_PREFIX + token);
            out.write(new ObjectMapper().writeValueAsString(resultMap));
            out.flush();
            out.close();
        } catch (Exception outEx) {
            outEx.printStackTrace();
        }
    }

}
