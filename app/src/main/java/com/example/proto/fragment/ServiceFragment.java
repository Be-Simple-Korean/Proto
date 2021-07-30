package com.example.proto.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.proto.R;
import com.example.proto.activity.BusinessCardActivity;
import com.example.proto.databinding.FragmentServiceBinding;
import com.example.proto.utils.IntentUtil;

public class ServiceFragment extends Fragment {

    private FragmentServiceBinding mBinding = null;

    private View.OnClickListener mOnClickListener = v -> {
        switch (v.getId()) {
            case R.id.cv_business_card:
                startActivity(new Intent(getActivity(), BusinessCardActivity.class));
                break;
            case R.id.cv_h_order:
                IntentUtil.getInstance().moveToBrowser("https://naver.com");
                break;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = FragmentServiceBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        init();
        return mBinding.getRoot();
    }

    private void init() {
        mBinding.cvBusinessCard.setOnClickListener(mOnClickListener);
        mBinding.cvHOrder.setOnClickListener(mOnClickListener);
    }

}