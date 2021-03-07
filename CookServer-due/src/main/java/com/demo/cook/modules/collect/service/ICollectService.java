package com.demo.cook.modules.collect.service;

import com.demo.cook.common.response.RtnResult;

public interface ICollectService {

    RtnResult addCollect(String username, String targetId) throws Exception;

    RtnResult cancelCollect(String username, String targetId) throws Exception;
}
