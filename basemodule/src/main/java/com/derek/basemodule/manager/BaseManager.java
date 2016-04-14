package com.derek.basemodule.manager;

import android.os.Handler;
import android.os.Looper;
import com.derek.basemodule.model.NetReq;
import com.derek.basemodule.model.NetResp;
import com.derek.basemodule.model.UIObserver;
import com.derek.basemodule.model.UIResult;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by derek on 16/2/4.
 */
public abstract class BaseManager extends android.database.Observable<UIObserver> {
    protected final String TAG = BaseManager.class.getSimpleName();

    protected Handler uiHandler = new Handler(Looper.getMainLooper());

    protected Subscription subscribe(final String key, final NetReq netReq, final Class clz,
        final NetObservable netObservable) {
        return netObservable.getStringObservable(netReq)
            .concatMap(new Func1<String, Observable<NetResp>>() {
                @Override
                public Observable<NetResp> call(String s) {
                    return netObservable.getNetRespObservable(s);
                }
            })
            .concatMap(new Func1<NetResp, Observable<UIResult>>() {
                @Override
                public Observable<UIResult> call(NetResp netResp) {
                    return netObservable.getUIResultObservable(netResp, clz);
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<UIResult>() {
                @Override
                public void call(UIResult uiResult) {
                    notifyUI(key, uiResult);
                }
            });
    }

    //回调到UI
    protected void notifyUI(final String key, final UIResult uiResult) {
        for (final UIObserver uiObserver : mObservers) {
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    uiObserver.onUIResult(key, uiResult);
                }
            });
        }
    }
}
