package com.demo.cook.modules.subscribe.service;

import com.demo.cook.common.response.RtnResult;

public interface ISubscribeService {

    RtnResult addSubscribe(String username, String targetUser) throws Exception;


    RtnResult cancelSubscribe(String username, String targetUser) throws Exception;
}
