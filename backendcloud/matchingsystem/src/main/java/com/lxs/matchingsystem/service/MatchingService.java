package com.lxs.matchingsystem.service;

public interface MatchingService {
    String addPlayer(Integer userId,Integer rating,Integer botId);
    String removePlayer(Integer userId);
}
