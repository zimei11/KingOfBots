package com.lxs.backend.service.impl.user.bot;

import com.lxs.backend.mapper.BotMapper;
import com.lxs.backend.pojo.Bot;
import com.lxs.backend.pojo.User;
import com.lxs.backend.service.impl.utils.UserDetailsImpl;
import com.lxs.backend.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authenticationToken.getPrincipal();
        User user=loginUser.getUser();

        Integer bot_id=Integer.parseInt(data.get("bot_id"));
        Bot bot=botMapper.selectById(bot_id);

        Map<String,String > map=new HashMap<>();

        if(bot==null){
            map.put("error_message","Bot不存在或者已经被删除");
            return map;
        }

        if(!bot.getUserId().equals(user.getId())){
            map.put("error_message","没有权限删除该Bot");
            return map;
        }

        botMapper.deleteById(bot_id);

        map.put("error_message","success");
        return map;
    }
}
