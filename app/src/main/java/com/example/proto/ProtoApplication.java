package com.example.proto;

import android.app.Application;

import com.example.proto.utils.IntentUtil;
import com.example.proto.utils.PermissionUtil;

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
