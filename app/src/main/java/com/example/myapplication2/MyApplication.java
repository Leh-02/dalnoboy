package com.example.myapplication2; // Убедитесь, что package совпадает с вашим!

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}