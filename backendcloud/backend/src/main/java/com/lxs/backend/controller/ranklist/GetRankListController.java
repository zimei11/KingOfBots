package com.lxs.backend.controller.ranklist;

import com.alibaba.fastjson.JSONObject;
import com.lxs.backend.service.ranklist.GetRankListService;
import com.lxs.backend.service.record.GetRecordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetRankListController {
    @Autowired
    private GetRankListService getRankListService;

    @GetMapping("/ranklist/getlist/")
    public JSONObject getList(@RequestParam Map<String,String> data){
        Integer page=Integer.parseInt(data.get("page"));
        return getRankListService.getList(page);
    }
}
