package com.derek.basemodule.manager;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.derek.basemodule.model.NetReq;
import com.derek.basemodule.model.NetResp;
import com.derek.basemodule.model.UIResult;
import com.derek.basemodule.network.NetConstants;
import com.derek.basemodule.network.NetEngine;
import com.derek.basemodule.network.NetUtils;
import com.derek.basemodule.utils.ParseUtils;
import java.util.Iterator;
import rx.Observable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by derek on 16/2/22.
 */
public class NetObservableImpl implements NetObservable {
    private final String TAG = "NetObservableImpl";

    @Override
    public Observable<String> getStringObservable(final NetReq netReq) {
        return Observable.fromCallable(new Func0<String>() {
            @Override
            public String call() {
                if (netReq.getMethodType() == NetReq.METHOD_TYPE.GET) {
                    StringBuffer sb = new StringBuffer();
                    if (netReq.getParams() != null) {
                        sb.append("?");
                        String key;
                        Iterator<String> it = netReq.getParams().keySet().iterator();
                        while (it.hasNext()) {
                            key = it.next();
                            sb.append(key + "=" + netReq.getParams().get(key));
                            sb.append("&");
                        }
                    }
                    return NetEngine.getInstance().get(netReq.getUrl() + sb);
                } else {
                    return NetEngine.getInstance().post(netReq.getUrl(), netReq.getParams());
                }
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<NetResp> getNetRespObservable(final String s) {
        return Observable.fromCallable(new Func0<NetResp>() {
            @Override
            public NetResp call() {
                Log.d(TAG, "net response = " + s);
                NetResp netResp = null;
                try {
                    netResp = JSON.parseObject(s, NetResp.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return netResp;
            }
        });
    }

    @Override
    public Observable<UIResult> getUIResultObservable(final NetResp netResp, final Class clz) {
        return Observable.fromCallable(new Func0<UIResult>() {
            @Override
            public UIResult call() {
                UIResult uiResult = NetUtils.netResult2UIResult(netResp);
                if (uiResult.getRet() == NetConstants.UI_NEW_DATA) {
                    if (netResp.getData() instanceof JSONArray) {
                        uiResult.setData(
                            ParseUtils.parseJSONArray((JSONArray) netResp.getData(), clz));
                    } else if (netResp.getData() instanceof JSONObject) {
                        uiResult.setData(
                            ParseUtils.parseObject((JSONObject) netResp.getData(), clz));
                    }
                }
                return uiResult;
            }
        });
    }
}
