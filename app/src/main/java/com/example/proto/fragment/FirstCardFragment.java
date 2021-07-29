package com.example.proto.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.proto.Constants;
import com.example.proto.R;
import com.example.proto.databinding.FragmentFirstCardBinding;
import com.example.proto.utils.IntentUtil;

import java.io.IOException;
import java.io.InputStream;

public class FirstCardFragment extends Fragment {

    private FragmentFirstCardBinding mBinding = null;

    private final ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if (IntentUtil.getInstance().resultFromGallery(result)) {
                        setImageFromGallery(result);
                    }
                }
            }
    );

    private View.OnClickListener mOnClickListener = v -> {
        IntentUtil.getInstance().moveToFolder(startActivityResult, Constants.MIME_TYPE_IMAGE);
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = FragmentFirstCardBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Glide.with(this)
                .load(R.drawable.dog)
                .circleCrop()
                .into(mBinding.ivProfile);

        mBinding.ivMoveGallery.setOnClickListener(mOnClickListener);

        return mBinding.getRoot();
    }

    private void setImageFromGallery(ActivityResult result) {
        Glide.with(this)
                .load(result.getData().getData())
                .circleCrop()
                .into(mBinding.ivProfile);
    }

}