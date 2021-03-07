package com.demo.cook.modules.praise.controller;


import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.praise.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/praise")
public class PraiseController {

    private IPraiseService praiseService;

    @Autowired
    public void setPraiseService(IPraiseService praiseService) {
        this.praiseService = praiseService;
    }

    @RequestMapping(value = "/addPraise",method = RequestMethod.POST)
    public RtnResult addPraise(HttpServletRequest request){
        try {
            return praiseService.addPraise(request.getParameter("username"),request.getParameter("targetId"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

    @RequestMapping(value = "/cancelPraise",method = RequestMethod.POST)
    public RtnResult cancelPraise(HttpServletRequest request){
        try {
            return praiseService.cancelPraise(request.getParameter("username"),request.getParameter("targetId"));
        } catch (Exception e) {
            e.printStackTrace();
            return new RtnResult<>(Rtn.serviceException);
        }
    }

}
