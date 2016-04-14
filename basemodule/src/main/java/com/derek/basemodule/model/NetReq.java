package com.derek.basemodule.model;

import java.util.Map;

/**
 * Created by derek on 16/2/22.
 */
public class NetReq {
    public enum METHOD_TYPE {
        GET, POST
    }

    private String url;
    private METHOD_TYPE methodType;
    private Map<String, String> params;

    public NetReq(String url, METHOD_TYPE methodType, Map<String, String> params) {
        this.url = url;
        this.methodType = methodType;
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public METHOD_TYPE getMethodType() {
        return methodType;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
