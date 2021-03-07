package com.demo.cook.modules.test.controller;

import com.demo.cook.common.response.Rtn;
import com.demo.cook.common.response.RtnResult;
import com.demo.cook.modules.test.model.TestBodyBean;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/hello")
    public String hello(){

        return "Hello Spring-boot Test ";
    }


    @RequestMapping(value = "/body", method = RequestMethod.POST ,consumes = "application/json", produces = "application/json")
    public Object testBodyParams(@RequestBody TestBodyBean params){

        System.out.println(new Gson().toJson(params));

        return params;
    }


    /**
     *
     * 测试返回 Map 对象
     * */
    @RequestMapping(value = "/info")
    public Map<String, Object> getInfo(@RequestParam String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", 12);
        map.put("height",178);
        return map;
    }

    /**
     *
     * 测试根据参数返回列表
     * 参数：pageNum 、pageSize
     * */
    @RequestMapping("/list")
    public List<Map<String, String>> getList(HttpServletRequest request) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map;


        int pageNum= ServletRequestUtils.getIntParameter(request,"pageNum",1);
        int pageSize= ServletRequestUtils.getIntParameter(request,"pageSize",20);
//        String pageNumStr=request.getParameter("pageNum");
//        int pageNum=Integer.parseInt(StringUtils.isEmpty(pageNumStr)?"0":pageNumStr);
//        String pageSizeStr=request.getParameter("pageSize");
//        int pageSize=Integer.parseInt(StringUtils.isEmpty(pageSizeStr)?"5":pageSizeStr);
        for (int i = 1; i <= pageSize; i++) {
            map = new HashMap<>();
            map.put("name", "名字-"+pageNum+"-" + i);
            map.put("age",pageNum+""+i);
            list.add(map);
        }
        return list;
    }


    /**
     *
     * 用javaBean 来统一和规范返回格式
     * success(执行成功与否，增删改时尤为重要)
     * msg (执行信息，执行失败时尤为重要)
     * data(执行结果)
     * 该方法主要讲解 返回对象RtnResult的用法
     * */
    @RequestMapping("/login")
    public RtnResult<Map<String,Object>> testLogin(HttpServletRequest request){
        String userName =request.getParameter("userName");
        if (StringUtils.isEmpty(userName)){
            return new RtnResult<>(-1,"用户名不能为空");
        }
        String [] userArray={"Tom","David","Evan","Jack","Jim"};
        List<String> userList= Arrays.asList(userArray);// 数组转list 的方法
        if(!userList.contains(userName)){
            return new RtnResult<>(-1,"用户不存在");
        }
        String passWord=request.getParameter("passWord");
        if(StringUtils.isEmpty(passWord)){
            return new RtnResult<>(-1,"密码不能为空");
        }
        if(!"123456".equals(passWord)){
            return new RtnResult<>(-1,"密码错误");
        }
        Map<String ,Object> user=new HashMap<>();
        user.put("name",userName);
        user.put("age",12);
        user.put("height",178);
        user.put("weight",61.50);
        user.put("sex",1);
        return new RtnResult<>(Rtn.success,user);
    }







}
