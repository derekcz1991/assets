package com.derek.assetscontrol;

import android.app.Application;
import com.derek.basemodule.network.NetEngine;

/**
 * Created by derek on 16/4/9.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        NetEngine.getInstance().init();
    }
}
