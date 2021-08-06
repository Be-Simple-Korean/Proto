package com.example.proto.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proto.Constants;
import com.example.proto.R;
import com.example.proto.databinding.ActivityBusinessCardBinding;
import com.example.proto.utils.IntentUtil;

public class BusinessCardActivity extends AppCompatActivity {

    private final int START_SPANNABLE_POSITION = 3;
    private final int END_SPANNABLE_POSITION = 5;
    private final String SIMPLE_SEND="간편 발송하기";
    private final String SIMPLE_SAVE="간편 저장하기";

    private ActivityBusinessCardBinding mBinding = null;

    private View.OnClickListener mOnClickListener = v -> {
        switch (v.getId()) {
            case R.id.ll_my_card:
                if (IntentUtil.getInstance().checkLaunched(Constants.NAMEE_PACKAGE_NAME)) {
                    IntentUtil.getInstance().moveToNamee();
                }else{
                    IntentUtil.getInstance().moveToStore(Constants.NAMEE_PACKAGE_NAME);
                }
                break;
            case R.id.ll_other_card:
                startActivity(new Intent(this,SaveBCardActivity.class));
                break;

        }
    };

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBusinessCardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        init();

    }

    private void init() {
        initTextView();
        mBinding.llMyCard.setOnClickListener(mOnClickListener);
        mBinding.llOtherCard.setOnClickListener(mOnClickListener);
    }

    private void initTextView() {
        mBinding.tvSendMycard.setText(setMiddleTextColor(SIMPLE_SEND));
        mBinding.tvSaveCard.setText(setMiddleTextColor(SIMPLE_SAVE));
    }

    private SpannableStringBuilder setMiddleTextColor(String text) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        stringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE), START_SPANNABLE_POSITION, END_SPANNABLE_POSITION, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

}