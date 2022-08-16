package com.lxs.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 地图类回合制游戏
 * 作用：维护玩家的位置信息
 * 字段：(sx,sy),起点的坐标
 *      step,玩家的每次指令是什么
 *           路径是什么
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer id;
    private Integer sx;
    private Integer sy;
    private List<Integer> step;
}
