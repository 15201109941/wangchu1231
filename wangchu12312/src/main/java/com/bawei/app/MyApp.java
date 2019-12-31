package com.bawei.app;

import android.app.Application;
import android.content.Context;

/*
 *@auther:王楚
 *@Date: 2019/12/31
 *@Time:8:44
 *@Description:${DESCRIPTION}
 **/
public class MyApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
