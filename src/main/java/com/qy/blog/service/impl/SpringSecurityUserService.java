package com.qy.blog.service.impl;

import com.qy.blog.pojo.Permission;
import com.qy.blog.pojo.Role;
import com.qy.blog.pojo.User;
import com.qy.blog.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import static com.qy.blog.utils.Rc4Util.toRC4;

/**
 * springsecurity实现类
 */
@Service
public class SpringSecurityUserService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    private final static String RC4_KEY = "189100";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名查询用户信息
        User user = userService.findByUsername(username);
        if (user == null){
            // 用户名不存在
            return null;
        }
        // String password = "{noop}" + user.getPassword(); // 无加密配置需要加 "{noop}"

        // base64解密
        byte[] decode = Base64.getDecoder().decode(user.getPassword());
        // 再通过rc4解密
        String pwd = toRC4(new String(decode), RC4_KEY);
        // 通过security自带加密
        String password = passwordEncoder.encode(pwd);

        //创建list集合
        List<GrantedAuthority> list = new ArrayList<>();
        // 动态为当前用户授权
        List<Role> roles = user.getRoles();
        for (Role role : roles){
            // 遍历角色集合，为用户授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            // 获取角色对应的权限
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions){
                // 遍历集合为用户授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        // 返回用户名，密码，权限集合
        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username,password,list);
        return securityUser;
    }
}
