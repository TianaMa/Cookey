package com.demo.cook.modules.praise.service;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.praise.mapper.PraiseMapper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PraiseServiceImpl implements IPraiseService {

    private PraiseMapper praiseMapper;

    @Autowired
    public void setPraiseProductMapper(PraiseMapper praiseMapper) {
        this.praiseMapper = praiseMapper;
    }

    @Override
    public RtnResult addPraise(String username, String targetId) throws Exception {
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(targetId)){
            return new RtnResult(Rtn.missingParameter);
        }
        return new RtnResult(Rtn.success, praiseMapper.addPraise(username,targetId));
    }

    @Override
    public RtnResult cancelPraise(String username, String targetId) throws Exception {
        if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(targetId)){
            return new RtnResult(Rtn.missingParameter);
        }
        return new RtnResult(Rtn.success, praiseMapper.cancelPraise(username,targetId));
    }
}
