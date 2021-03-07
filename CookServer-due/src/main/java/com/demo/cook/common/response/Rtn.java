package com.demo.cook.common.response;


public enum Rtn {

    //common Rtn
    serviceException(-2,"server error"),
    missingParameter(-3,"less some important infor"),


    success(0,"success"),

    //business Rtn

    userAlreadyExists(1001,"You already a user"),
    registerFail(1002,"Login failure"),

    userOrPasswordError(1011,"Name does't exist or the password incorrect"),

    editUserInfoError(1021,"Save failure"),
    noData(0001,"No such infor"),

    cannotSubscribe(1031,"cannotSubscribe"),

            ;




    
    private int code;

    private String msg;

    Rtn(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
