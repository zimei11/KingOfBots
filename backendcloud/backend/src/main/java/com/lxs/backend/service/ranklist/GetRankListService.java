package com.lxs.backend.service.ranklist;

import com.alibaba.fastjson.JSONObject;

public interface GetRankListService {
    JSONObject getList(Integer page);
}
