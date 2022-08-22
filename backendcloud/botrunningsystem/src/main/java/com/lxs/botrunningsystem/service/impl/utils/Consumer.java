package com.lxs.botrunningsystem.service.impl.utils;

import com.lxs.botrunningsystem.utils.BotInterface;
import org.joor.Reflect;

import java.util.UUID;

public class Consumer extends Thread {
    private Bot bot;

    public void startTimeout(long timeout, Bot bot) {
        this.bot = bot;
        this.start();

        try {
            this.join(timeout);//最多等待timeout秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();//中断当前线程
        }
    }

    private String addUid(String code,String uid){//在code中的Bot类名后添加uid
        int k=code.indexOf("implements com.lxs.botrunningsystem.utils.BotInterface");
        return code.substring(0,k)+uid+code.substring(k);

    }

    @Override
    public void run() {
        //同名类只编译一次，加入随机类名
        UUID uuid=UUID.randomUUID();
        String uid=uuid.toString().substring(0,8);

        BotInterface botInterface = Reflect.compile(
                "com.lxs.botrunningsystem.utils.Bot"+uid,
                addUid(bot.getBotCode(),uid)

        ).create().get();
        System.out.println(botInterface.nextMove(bot.getInput()));
    }
}
