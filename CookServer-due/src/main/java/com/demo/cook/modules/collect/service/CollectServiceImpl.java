package com.demo.cook.modules.collect.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.collect.mapper.CollectMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CollectServiceImpl implements ICollectService {

    private CollectMapper collectMapper;

    @Autowired
    public void setPraiseProductMapper(CollectMapper praiseMapper) {
        this.collectMapper = praiseMapper;
    }

    @Override
    public RtnResult addCollect(String username, String targetId) throws Exception {
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(targetId)){
            return new RtnResult(Rtn.missingParameter);
        }
        return new RtnResult(Rtn.success, collectMapper.addCollect(username,targetId));
    }

    @Override
    public RtnResult cancelCollect(String username, String targetId) throws Exception {
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(targetId)){
            return new RtnResult(Rtn.missingParameter);
        }
        return new RtnResult(Rtn.success, collectMapper.cancelCollect(username,targetId));
    }
}
