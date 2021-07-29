package com.example.proto.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proto.R;
import com.example.proto.databinding.FragmentSecondCardBinding;

public class SecondCardFragment extends Fragment {

    private FragmentSecondCardBinding mBinding = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= FragmentSecondCardBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }
}