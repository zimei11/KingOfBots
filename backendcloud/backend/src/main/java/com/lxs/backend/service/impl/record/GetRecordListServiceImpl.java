package com.lxs.backend.service.impl.record;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxs.backend.mapper.RecordMapper;
import com.lxs.backend.mapper.UserMapper;
import com.lxs.backend.pojo.Record;
import com.lxs.backend.pojo.User;
import com.lxs.backend.service.record.GetRecordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetRecordListServiceImpl implements GetRecordListService {
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public JSONObject getList(Integer page) {
        //分页
        IPage<Record> recordIPage=new Page<>(page,10);

        //从数据库查询
        QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        List<Record> records=recordMapper.selectPage(recordIPage,queryWrapper).getRecords();

        //存到前端
        JSONObject resp=new JSONObject();
        List<JSONObject> items=new ArrayList<>();
        for (Record record:records){
            User userA=userMapper.selectById(record.getAId());
            userA.setPassword(null);
            User userB=userMapper.selectById(record.getBId());
            userB.setPassword(null);
            JSONObject item=new JSONObject();
            item.put("a_photo",userA.getPhoto());
            item.put("a_username",userA.getUsername());
            item.put("b_photo",userB.getPhoto());
            item.put("b_username",userB.getUsername());
            String result="平局";
            if("A".equals(record.getLoser())) result="B胜";
            else if ("B".equals(record.getLoser())) result="A胜";
            item.put("result",result);
            item.put("record",record);
            items.add(item);
        }
        resp.put("records",items);
        resp.put("records_count",recordMapper.selectCount(null));
        return resp;
    }
}
