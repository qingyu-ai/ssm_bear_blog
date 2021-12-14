package com.qy.blog.handler;

import com.qy.blog.entity.Constants;
import com.qy.blog.entity.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功逻辑
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取权限信息
        String authorization = request.getHeader("Authorization");
        // 比对token开头
        if (!authorization.startsWith(Constants.TOKEN_PREFIX)) {
            Result.ResultError("请登录", response);
        } else {
            Result.ResultSuccess("退出成功", response);
        }

    }

}
