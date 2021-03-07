package com.demo.cook.common.response;

public class RtnResult<T> {
    private int code = -1;
    private String msg;
    private T data;
    public void setCode(int code) {
        this.code = code;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public RtnResult(){
    }

    public RtnResult(int code, String msg){
        this.code = code;
        this.msg=msg;
    }

    public RtnResult(int code, String msg, T data){
        this.code = code;
        this.msg=msg;
        this.data=data;
    }

    public RtnResult(Rtn rtn){
        this.code = rtn.getCode();
        this.msg=rtn.getMsg();
    }

    public RtnResult(Rtn rtn, T data){
        this.code = rtn.getCode();
        this.msg=rtn.getMsg();
        this.data=data;
    }
}
