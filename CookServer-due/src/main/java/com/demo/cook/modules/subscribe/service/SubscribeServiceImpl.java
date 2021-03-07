package com.demo.cook.modules.subscribe.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.subscribe.mapper.SubscribeMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscribeServiceImpl implements ISubscribeService {

    private SubscribeMapper subscribeMapper;

    @Autowired
    public void setPraiseProductMapper(SubscribeMapper subscribeMapper) {
        this.subscribeMapper = subscribeMapper;
    }

    @Override
    public RtnResult addSubscribe(String username, String targetUser) throws Exception {
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(targetUser)){
            return new RtnResult(Rtn.missingParameter);
        }
        if(username.equals(targetUser)){
            return new RtnResult(Rtn.cannotSubscribe);
        }
        return new RtnResult(Rtn.success, subscribeMapper.addSubscribe(username, targetUser));
    }

    @Override
    public RtnResult cancelSubscribe(String username, String targetUser) throws Exception {
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(targetUser)){
            return new RtnResult(Rtn.missingParameter);
        }
        return new RtnResult(Rtn.success, subscribeMapper.cancelSubscribe(username, targetUser));
    }
}
