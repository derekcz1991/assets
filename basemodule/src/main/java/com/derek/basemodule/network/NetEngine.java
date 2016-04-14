package com.derek.basemodule.network;

import android.util.Log;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by derek on 16/1/22.
 */
public class NetEngine {
    private final String TAG = "NetEngine";

    private static NetEngine netEngine;
    private static OkHttpClient client;

    public static NetEngine getInstance() {
        if (netEngine == null) {
            netEngine = new NetEngine();
        }
        return netEngine;
    }

    public void init() {
        client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    }

    public String post(String url, Map<String, String> params) {
        Log.d(TAG, "post url = " + url);
        Iterator<String> iterator = params.keySet().iterator();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        while (iterator.hasNext()) {
            String key = iterator.next();
            formBodyBuilder.add(key, params.get(key));
        }
        Request request = new Request.Builder().url(url).post(formBodyBuilder.build()).build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String get(String url) {
        Log.d(TAG, "get rurl = " + url);
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String get(String url, String... postfix) {
        for (int i = 0; i < postfix.length; i++) {
            url += "/" + postfix[i];
        }
        return get(url);
    }
}
