package com.lxs.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxs.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
