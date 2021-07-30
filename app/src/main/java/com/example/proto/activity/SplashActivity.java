package com.example.proto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proto.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH = 1;
    private final int SPLASH_DELAY_TIME = 2000;

    private ActivitySplashBinding mBinding = null;

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SPLASH:
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mHandler.sendEmptyMessageDelayed(SPLASH, SPLASH_DELAY_TIME);
    }

    @Override
    public void onBackPressed() {

    }
}