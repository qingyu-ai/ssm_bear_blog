package com.qy.blog.filter;

import com.qy.blog.entity.Constants;
import com.qy.blog.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * token拦截校验器
 */
public class HeaderTokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头为Authorization的内容
        String header = request.getHeader("Authorization");
        if (header == null) {
            header = request.getParameter("token");
        }

        // 拦截请求头有common的放行
       /* if (header.equals(Constants.COMMON)) {
            chain.doFilter(request, response);
        } */
        // 校验开头是否为bearer
        if (!header.startsWith(Constants.TOKEN_PREFIX)) {
            //如果携带错误的token，则给用户提示请登录！
            System.out.println("============错误token");
            return false;
        }

        // 如果携带了正确格式的token要先得到token,
        String token = header.replace(Constants.TOKEN_PREFIX, "");
        //验证token是否正确，无法解析则是token过期
        try {
            JwtUtil.parseTokenHasClaims(token);
        } catch (Exception e) {
            System.out.println("============过期token");
            return false;
        }

        return true;

    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
