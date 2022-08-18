package com.lxs.backend.service.impl.user.bot;

import com.lxs.backend.mapper.BotMapper;
import com.lxs.backend.pojo.Bot;
import com.lxs.backend.pojo.User;
import com.lxs.backend.service.impl.utils.UserDetailsImpl;
import com.lxs.backend.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    BotMapper botMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Integer bot_id = Integer.parseInt(data.get("bot_id"));
        String title=data.get("title");
        String description=data.get("description");
        String content=data.get("content");

        Bot bot = botMapper.selectById(bot_id);

        Map<String, String> map = new HashMap<>();

        if (bot == null) {
            map.put("error_message", "bot不存在或者已经被删除");
            return map;
        }

        if (!bot.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限修改该bot");
            return map;
        }

        if (title == null || title.length() == 0) {
            map.put("error_message", "标题不能为空");
            return map;
        }

        if (title.length() > 100) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }

        if (description == null || description.length() == 0) {
            description = "这个用户很懒，什么都没有写~";
        }

        if (description.length() > 300) {
            map.put("error_message", "Bot描述的长度不能大于300");
            return map;
        }

        if (content == null || content.length() == 0) {
            map.put("error_message", "代码不能为空");
            return map;
        }

        if (content.length() > 10000) {
            map.put("error_message", "代码长度不能超过10000");
            return map;
        }

        Bot new_bot = new Bot(
                bot.getId(),
                user.getId(),
                title,
                description,
                content,
                bot.getCreateTime(),
                new Date()
        );

        botMapper.updateById(new_bot);

        map.put("error_message","success");
        return map;
    }
}
