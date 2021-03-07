package com.demo.cook.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ReqUtil {

    public static Map<String,String> getParams(HttpServletRequest request){
        Map<String,String> params=new HashMap<>();
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            params.put(paraName,request.getParameter(paraName));
        }
        return params;
    }
}
