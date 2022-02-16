package com.cunchugui.houtai;

import android.app.Application;

import com.tencent.bugly.Bugly;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "704ee09b6c", false);
    }
}
