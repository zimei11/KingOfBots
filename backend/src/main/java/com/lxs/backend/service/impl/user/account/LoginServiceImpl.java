package com.lxs.backend.service.impl.user.account;

import com.lxs.backend.pojo.User;
import com.lxs.backend.service.impl.utils.UserDetailsImpl;
import com.lxs.backend.service.user.account.LoginService;
import com.lxs.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    //注入
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {
       //存的明文，先封装
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(username,password);

        //如果登录失败，会自动处理
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //取出用户信息
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticate.getPrincipal();
        User user=loginUser.getUser();

        //生成JWT
        String jwt= JwtUtil.createJWT(user.getId().toString());

        Map<String, String> map = new HashMap<>();
        map.put("error_message","success");
        map.put("token",jwt);

        return map;
    }
}
