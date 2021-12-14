package com.qy.blog.utils;

import com.qy.blog.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JwtUtil{

    private static final String SALT = "189100";

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoi566h55CG5ZGYIiwiaWF0IjoxNjM5MjAzNzUxLCJ1c2VySW5mbyI6eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIiwicGFzc3dvcmQiOiJ3cWZDdGhmQ3JzTzEiLCJhdmF0YXIiOiJodHRwczovL3MzLmJtcC5vdmgvaW1ncy8yMDIxLzExL2Q3MzA5ZjRhMzUzZTZkNWUuanBnIiwicm9sZXMiOlt7ImlkIjoxLCJuYW1lIjoiYWRtaW4iLCJrZXl3b3JkIjoiUk9MRV9BRE1JTiIsInVzZXJzIjpbXSwicGVybWlzc2lvbnMiOlt7ImlkIjoxLCJuYW1lIjoi5paw5aKe5paH56ugIiwia2V5d29yZCI6IkFSVElDTEVfQUREIiwiZGVzY3JpcHRpb24iOm51bGwsInJvbGVzIjpbXX1dfV19LCJyb2xlTGlzdCI6WyJBUlRJQ0xFX0FERCIsIlJPTEVfQURNSU4iXSwicGVybWlzc2lvblBhdGhzIjpudWxsLCJlbmFibGVkIjp0cnVlLCJwYXNzd29yZCI6IndxZkN0aGZDcnNPMSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJBUlRJQ0xFX0FERCJ9LHsiYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwidXNlcm5hbWUiOiJhZG1pbiIsImFjY291bnROb25FeHBpcmVkIjp0cnVlLCJhY2NvdW50Tm9uTG9ja2VkIjp0cnVlLCJjcmVkZW50aWFsc05vbkV4cGlyZWQiOnRydWV9LCJleHAiOjE2MzkyMDQzNTF9.02zeYMzJBbgqktbSvmGQCjV57qhAkVuUod-fa00fkrM";
        parseTokenHasClaims(token);
    }


    /**
     * 创建token（自定义声明）
     */
    public static <T> String signToken(String username){

        long now = System.currentTimeMillis();
        // 过期时间，60分钟过期
        long exp = now + 60 * 100000;

        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置id， {"jti":"888"}
                .setId("1")
                // 签发用户，{"sub":"Rose"}
                .setSubject("管理员")
                // 签发时间，{"iat":"xxx"}
                .setIssuedAt(new Date())

                // 签发加密，xxxx是盐（salt）
                .signWith(SignatureAlgorithm.HS256, SALT)
                .claim("username",username)
                // 设置过期时间, {"exp":"xxx"}
                .setExpiration(new Date(exp));
        // 生成token
        String token = jwtBuilder.compact();
        System.out.println(token);

        return token;
    }

    /**
     * 解析token（自定义声明）
     */
    public static Claims parseTokenHasClaims(String token){

        // 获取claims(荷载)
        Claims claims = Jwts.parser()
                .setSigningKey(SALT)
                .parseClaimsJws(token)
                .getBody();

      /*  System.out.println("jti:" + claims.getId());
        System.out.println("sub:" + claims.getSubject());
        System.out.println("iat:" + claims.getIssuedAt());
        System.out.println("username:" + claims.get("username"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间:" + format.format(claims.getIssuedAt()));
        System.out.println("当前时间:" + format.format(new Date()));
        System.out.println("过期时间:" + format.format(claims.getExpiration()));*/

        return claims;
    }


}