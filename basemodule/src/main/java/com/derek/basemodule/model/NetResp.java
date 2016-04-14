package com.derek.basemodule.model;

/**
 * Created by derek on 16/2/21.
 */
public class NetResp {
    private int ret;
    private String msg;
    private Object data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
