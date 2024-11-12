package com.nyinst.farmease.app;

import android.app.Application;

import com.nyinst.farmease.network.SingletonRequest;

/**
 * Proprietary of Xcel Solution Corp
 * <p>
 * Author : Avinash Kumar Singh, 21-Jun-2024
 * <p>
 * Description :
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new SingletonRequest(getApplicationContext());
    }
}
