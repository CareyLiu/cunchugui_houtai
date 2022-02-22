package com.cunchugui.houtai.app;

import android.app.Application;

import com.bulong.rudeness.RudenessScreenHelper;
import com.tencent.bugly.Bugly;

public class MyApplication extends Application {

    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Bugly.init(getApplicationContext(), "704ee09b6c", false);
        new RudenessScreenHelper(this, 720).activate();
    }


    public static MyApplication getInstance() {
        return application;
    }
}
