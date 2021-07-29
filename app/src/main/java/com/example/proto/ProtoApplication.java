package com.example.proto;

import android.app.Application;

import com.example.proto.utils.IntentUtil;

public class ProtoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        IntentUtil.create(this);
    }
}
