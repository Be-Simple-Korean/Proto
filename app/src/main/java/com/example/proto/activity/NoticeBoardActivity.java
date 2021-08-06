package com.example.proto.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proto.R;
import com.example.proto.databinding.ActivityNoticeBoardBinding;

public class NoticeBoardActivity extends AppCompatActivity {

    private ActivityNoticeBoardBinding mBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityNoticeBoardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


    }
}