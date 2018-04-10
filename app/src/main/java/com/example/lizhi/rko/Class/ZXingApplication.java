package com.example.lizhi.rko.Class;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class ZXingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
