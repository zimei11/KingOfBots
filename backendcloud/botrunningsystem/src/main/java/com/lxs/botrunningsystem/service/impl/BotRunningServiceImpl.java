package com.lxs.botrunningsystem.service.impl;

import com.lxs.botrunningsystem.service.BotRunningService;
import org.springframework.stereotype.Service;

@Service
public class BotRunningServiceImpl implements BotRunningService {
    @Override
    public String addBot(Integer userId, String botCode, String input) {
        System.out.println("add bot:" + userId+" "+botCode+" "+input);
        return "add bot success";
    }
}
