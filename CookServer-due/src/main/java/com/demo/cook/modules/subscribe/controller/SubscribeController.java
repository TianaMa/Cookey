package com.demo.cook.modules.subscribe.controller;


import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.subscribe.service.ISubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/subscribe")
public class SubscribeController {

    private ISubscribeService subscribeService;

    @Autowired
    public void setSubscribeService(ISubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @RequestMapping(value = "/addSubscribe",method = RequestMethod.POST)
    public RtnResult addSubscribe(HttpServletRequest request){
        try {
            return subscribeService.addSubscribe(request.getParameter("username"),request.getParameter("targetUser"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/cancelSubscribe",method = RequestMethod.POST)
    public RtnResult cancelSubscribe(HttpServletRequest request){
        try {
            return subscribeService.cancelSubscribe(request.getParameter("username"),request.getParameter("targetUser"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

}
