package com.example.proto.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.proto.databinding.FragmentPersonBinding;
import com.example.proto.utils.IntentUtil;
import com.google.android.material.tabs.TabLayoutMediator;

public class PersonFragment extends Fragment {

    private final String NAVER_URL = "com.nhn.android.search";
    private FragmentPersonBinding mBinding = null;

    private final View.OnClickListener mOnClickListener = v -> {
        if (IntentUtil.getInstance().checkLaunched(NAVER_URL)) {
            IntentUtil.getInstance().moveToNaverPay();
        } else {
            IntentUtil.getInstance().moveToStore(NAVER_URL);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = FragmentPersonBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        init();
        return mBinding.getRoot();
    }

    private void init() {
        setViewPager();
        mBinding.ivMoveNaver.setOnClickListener(mOnClickListener);
        new TabLayoutMediator(mBinding.tl, mBinding.vp,
                (tab, position) -> tab.setText("")).attach();
    }

    private void setViewPager() {
        mBinding.vp.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new FirstCardFragment();
                    default:
                        return new SecondCardFragment();
                }
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });
        mBinding.vp.setSaveEnabled(false);
    }
}