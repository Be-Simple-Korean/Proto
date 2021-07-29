package com.example.proto.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.proto.R;
import com.example.proto.databinding.FragmentPersonBinding;
import com.example.proto.utils.IntentUtil;
import com.google.android.material.tabs.TabLayoutMediator;

public class PersonFragment extends Fragment {

    private FragmentPersonBinding mBinding = null;

    private final View.OnClickListener mOnClickListener = v -> {
        switch (v.getId()) {
            case R.id.iv_move_naver:
                String pn = "com.nhn.android.search";
                if (IntentUtil.getInstance().checkLaunched(pn)) {
                    IntentUtil.getInstance().moveToNaverPay();
                } else {
                    IntentUtil.getInstance().moveToStore(pn);
                }
                break;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        mBinding = FragmentPersonBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewPager();
        mBinding.ivMoveNaver.setOnClickListener(mOnClickListener);
//        mBinding.tl.setupWithViewPager(mBinding.vp);
        new TabLayoutMediator(mBinding.tl, mBinding.vp,
                (tab, position) ->tab.setText("")).attach();
        return mBinding.getRoot();
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