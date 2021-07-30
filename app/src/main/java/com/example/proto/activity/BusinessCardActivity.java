package com.example.proto.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    }

    private void initTextView() {
        mBinding.tvSendMycard.setText(setMiddleTextColor("간편 발송하기"));
        mBinding.tvSaveCard.setText(setMiddleTextColor("간편 저장하기"));
    }

    private SpannableStringBuilder setMiddleTextColor(String text) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(text);
        stringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE), 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stringBuilder;
    }

}