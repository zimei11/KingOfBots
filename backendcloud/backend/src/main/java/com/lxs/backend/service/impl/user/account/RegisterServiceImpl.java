package com.lxs.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxs.backend.mapper.UserMapper;
import com.lxs.backend.pojo.User;
import com.lxs.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();

        //判断有没有这个参数
        if (username == null) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if (password == null || confirmedPassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        //去除空格回车
        username = username.trim();

        //判断读入的数据是否为空
        if (username.length() == 0) {
            map.put("error_message", "用户名不能为空");
            return map;
        }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error_message", "密码不能为空");
            return map;
        }

        if (username.length() > 100) {
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }

        if (password.length() > 100 || confirmedPassword.length() > 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if (password.length() < 6 || confirmedPassword.length() < 6){
            map.put("error_message", "密码长度不能小于6位");
            return map;
        }

            if (!password.equals(confirmedPassword)) {
                map.put("error_message", "两次输入的密码不一致");
                return map;
            }

        //查询数据库里是否有用户名等于username的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(userQueryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已经存在");
            return map;
        }

        String encodedPassword = passwordEncoder.encode(password);
        //默认用户头像
        String photo = "https://cdn.acwing.com/media/user/profile/photo/117702_lg_2e8c370518.jpg";
        User user = new User(null, username, encodedPassword, photo,1500);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}
