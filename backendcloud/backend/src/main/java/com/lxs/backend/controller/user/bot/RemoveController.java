package com.lxs.backend.controller.user.bot;

import com.lxs.backend.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveController {
    @Autowired
    RemoveService removeService;

    @PostMapping("/api/user/bot/remove/")
    public Map<String,String> remove(@RequestParam Map<String,String> data){
        return removeService.remove(data);
    }
}
