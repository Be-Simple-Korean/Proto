package com.example.proto.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proto.R;
import com.example.proto.activity.NoticeBoardActivity;
import com.example.proto.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {
    private FragmentMoreBinding mBinding = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=FragmentMoreBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        init();
        return mBinding.getRoot();
    }

    private void init(){
        mBinding.tvNotice.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), NoticeBoardActivity.class));
        });
    }
}