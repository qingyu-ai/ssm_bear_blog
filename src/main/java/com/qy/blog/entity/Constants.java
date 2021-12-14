package com.qy.blog.entity;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";
    /**
     *
     */
    public static final String COMMON = "common";
    /**
     * 本地ipv6
     */
    public static final String LOCAL_IPV6 = "0:0:0:0:0:0:0:1";
    /**
     * 本地ipv4
     */
    public static final String LOCAL_IPV4 = "127.0.0.1";
    /**
     * 返回json请求头
     */
    public static final String JSON_CONTENT_TYPE = "application/json;charset=utf-8";

    /**
     * 访问权限 redis key
     */
    public static final String PERMISSION_PATH = "permission_path:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

}
