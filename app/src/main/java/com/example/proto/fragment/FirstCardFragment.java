package com.example.proto.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
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
import com.example.proto.utils.PermissionUtil;

public class FirstCardFragment extends Fragment {

    private FragmentFirstCardBinding mBinding = null;

    private final ActivityResultLauncher<Intent> mStartActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if (IntentUtil.getInstance().resultFromGallery(result)) {
                        setImageFromGallery(result);
                    }
                }
            }
    );

    private ActivityResultLauncher<String> mPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    IntentUtil.getInstance().moveToFolder(mStartActivityResult, Constants.MIME_TYPE_IMAGE);
                } else {
                    PermissionUtil.getInstance().manualSetPermission(getContext());
                }
            });

    private View.OnClickListener mOnClickListener = v -> {
        mPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
//        if (PermissionUtil.getInstance().checkStoragePermission(getContext())) {
//            IntentUtil.getInstance().moveToFolder(mStartActivityResult, Constants.MIME_TYPE_IMAGE);
//        } else {
//            Log.e("수행","!");
//            PermissionUtil.getInstance().requestStoragePermission(getActivity());
//        }
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

    /**
     * 갤러리에서 가져온 이미지 세팅
     *
     * @param result
     */
    private void setImageFromGallery(ActivityResult result) {
        Glide.with(this)
                .load(result.getData().getData())
                .circleCrop()
                .into(mBinding.ivProfile);
    }

}