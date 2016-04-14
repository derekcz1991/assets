package com.derek.basemodule.manager;

import com.derek.basemodule.model.NetReq;
import com.derek.basemodule.model.NetResp;
import com.derek.basemodule.model.UIResult;
import rx.Observable;

/**
 * Created by derek on 16/2/22.
 */
public interface NetObservable {
    /**
     * @param netReq http request
     */
    Observable<String> getStringObservable(NetReq netReq);

    Observable<NetResp> getNetRespObservable(String s);

    Observable<UIResult> getUIResultObservable(NetResp netResp, Class clz);
}
