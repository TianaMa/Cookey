package com.demo.cook.base.http;

import com.demo.baselib.http.RetrofitUtil;

public class HttpConfig {

    public static final String BASE_URL="http://10.12.17.152:8092/cook/";

    public static <T>T getHttpServe(final Class <T> httpServe) {
        //Retrofit builder through URL build Retrofit object; Retrofit object  create the Network service entity class
        return RetrofitUtil.getHttpServe(BASE_URL,httpServe);
    }

}
