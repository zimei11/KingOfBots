package com.lxs.botrunningsystem.service.impl.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BotPool extends Thread{
    private final ReentrantLock lock=new ReentrantLock();
    private final Condition condition=lock.newCondition();
    private final Queue<Bot> bots=new LinkedList<>();

    public void addBot(Integer userId,String botCode,String input){
        lock.lock();//对queue修改，所以加锁
        try{
            bots.add(new Bot(userId,botCode,input));
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     *这里做了简化，只支持Java代码
     * 以后升级
     * 为了防止用户搞破坏，可以放到沙箱里去运行
     *
     * 同时为了支持其他语言，放到Docker里
     * Java代码里运行终端命令，开启一个限制空间的Docker
     */
    private void consume(Bot bot){

    }

    @Override
    public void run() {
        while(true){
            lock.lock();
            if(bots.isEmpty()){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    lock.unlock();
                    break;
                }
            }else {
                Bot bot=bots.remove();
                lock.unlock();
                consume(bot);//比较耗时，可能会执行几秒钟
            }
        }
    }
}
