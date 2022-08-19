package com.lxs.backend.consumer;

import com.alibaba.fastjson.JSONObject;
import com.lxs.backend.consumer.utils.Game;
import com.lxs.backend.consumer.utils.JwtAuthentication;
import com.lxs.backend.mapper.RecordMapper;
import com.lxs.backend.mapper.UserMapper;
import com.lxs.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    public static final ConcurrentHashMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
    private User user;
    private Session session = null;
    private static UserMapper userMapper;
    public static RecordMapper recordMapper;
    private static RestTemplate restTemplate;
    private Game game = null;
    private static final String addPlayerUrl = "http://127.0.0.1:3001/player/add/";
    private static final String removePlayerUrl = "http://127.0.0.1:3001/player/remove/";

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }

    @Autowired
    public void setRecordMapper(RecordMapper recordMapper) {
        WebSocketServer.recordMapper = recordMapper;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        WebSocketServer.restTemplate = restTemplate;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session = session;
        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);

        if (this.user != null) {
            users.put(userId, this);
        } else {
            this.session.close();
        }
    }

    @OnClose
    public void onClose() {
        // 关闭链接
        if (this.user != null) {
            users.remove(this.user.getId());
        }
    }

    public static void startGame(Integer aId,Integer aBotId, Integer bId ,Integer bBotId) {
        User a = userMapper.selectById(aId);
        User b = userMapper.selectById(bId);

        Game game = new Game(13, 14, 20, a.getId(), b.getId());
        game.createMap();

        if (users.get(a.getId()) != null)
            users.get(a.getId()).game = game;
        if (users.get(b.getId()) != null)
            users.get(b.getId()).game = game;

        game.start();

        JSONObject respGame = new JSONObject();
        respGame.put("a_id", game.getPlayerA().getId());
        respGame.put("a_sx", game.getPlayerA().getSx());
        respGame.put("a_sy", game.getPlayerA().getSy());
        respGame.put("b_id", game.getPlayerB().getId());
        respGame.put("b_sx", game.getPlayerB().getSx());
        respGame.put("b_sy", game.getPlayerB().getSy());
        respGame.put("map", game.getG());

        JSONObject respA = new JSONObject();
        respA.put("event", "start-matching");
        respA.put("opponent_username", b.getUsername());
        respA.put("opponent_photo", b.getPhoto());
        respA.put("game", respGame);
        if (users.get(a.getId()) != null)
            users.get(a.getId()).sendMessage(respA.toJSONString());

        JSONObject respB = new JSONObject();
        respB.put("event", "start-matching");
        respB.put("opponent_username", a.getUsername());
        respB.put("opponent_photo", a.getPhoto());
        respB.put("game", respGame);
        if (users.get(b.getId()) != null)
            users.get(b.getId()).sendMessage(respB.toJSONString());

    }

    private void startMatching(Integer botId) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        data.add("rating", this.user.getRating().toString());
        data.add("bot_id",botId.toString());
        restTemplate.postForObject(addPlayerUrl, data, String.class);
    }

    private void stopMatching() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        restTemplate.postForObject(removePlayerUrl, data, String.class);
    }

    private void move(int direction) {
        if (game.getPlayerA().getId().equals(user.getId())) {
            game.setNextStepA(direction);
        } else if (game.getPlayerB().getId().equals(user.getId())) {
            game.setNextStepB(direction);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
        JSONObject data = JSONObject.parseObject(message);
        String event = data.getString("event");
        if ("start-matching".equals(event)) {
            startMatching(data.getInteger("bot_id"));
        } else if ("stop-matching".equals(event)) {
            stopMatching();
        } else if ("move".equals(event)) {
            move(data.getInteger("direction"));
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
