package com.example.proto.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proto.Constants;
import com.example.proto.R;
import com.example.proto.databinding.ActivitySaveBcardBinding;
import com.example.proto.databinding.DialogSelectCameraGalleryBinding;
import com.example.proto.utils.IntentUtil;
import com.example.proto.utils.PermissionUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SaveBCardActivity extends AppCompatActivity {

    private final String LG_CAMERA_PACKAGE_NAME = "com.lge.camera";
    private final String SAMSUNG_CAMERA_PACKAGE_NAME = "com.google.android.documentsui";
    private final String FILE_PACKAGE_NAME = "com.sec.android.app.camera";

    private ActivitySaveBcardBinding mBinding = null;
    private DialogSelectCameraGalleryBinding mSubBinding = null;
    private BottomSheetDialog mBottomSheetDialog = null;
    private PackageInfo mSelectCamera = null;
    private PackageInfo mSelectFile = null;


    private final ActivityResultLauncher<Intent> mStartActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    setImageFromGallery(result);
                }
            }
    );

    private final ActivityResultLauncher<Intent> mCameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    setImageFromCamera(result);
                }
            }
    );

    private ActivityResultLauncher<String> mPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    IntentUtil.getInstance().moveToFolder(mStartActivityResult, Constants.MIME_TYPE_IMAGE);
                } else {
                    PermissionUtil.getInstance().manualSetPermission(this);
                }
            });

    private View.OnClickListener mOnClickListener = v -> {
        switch (v.getId()) {
            case R.id.iv_select_profile:
                mBottomSheetDialog.show();
                break;
            case R.id.tv_file:
                mPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                mBottomSheetDialog.cancel();
                break;
            case R.id.tv_camera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mCameraResult.launch(intent);
                mBottomSheetDialog.cancel();
                break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySaveBcardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        try {
            initData();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        init();

    }

    private void initData() throws PackageManager.NameNotFoundException {

        try {
            mSelectCamera = getPackageManager().getPackageInfo(LG_CAMERA_PACKAGE_NAME, 0);
            mSelectFile = getPackageManager().getPackageInfo(FILE_PACKAGE_NAME, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            mSelectCamera = getPackageManager().getPackageInfo(SAMSUNG_CAMERA_PACKAGE_NAME, 0);
        }
    }

    private void init() {
        initBottomSheetDialog();
        mBinding.ivSelectProfile.setOnClickListener(mOnClickListener);

    }

    private void initBottomSheetDialog() {
        mSubBinding = DialogSelectCameraGalleryBinding.inflate(getLayoutInflater());
        mBottomSheetDialog = new BottomSheetDialog(SaveBCardActivity.this, R.style.BottomSheetDialogTheme);
        mBottomSheetDialog.setContentView(mSubBinding.getRoot());

        Drawable cameraIcon = mSelectCamera.applicationInfo.loadIcon(getPackageManager());
        cameraIcon.setBounds(0, 0, cameraIcon.getMinimumWidth(), cameraIcon.getMinimumHeight());
        mSubBinding.tvCamera.setCompoundDrawables(null, cameraIcon, null, null);
        Drawable fileIcon = mSelectFile.applicationInfo.loadIcon(getPackageManager());
        fileIcon.setBounds(0, 0, fileIcon.getMinimumWidth(), fileIcon.getMinimumHeight());
        mSubBinding.tvFile.setCompoundDrawables(null, fileIcon, null, null);

        mSubBinding.tvCamera.setOnClickListener(mOnClickListener);
        mSubBinding.tvFile.setOnClickListener(mOnClickListener);
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
                .into(mBinding.ivSelectProfile);
    }

    private void setImageFromCamera(ActivityResult result) {
        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
        if (bitmap != null) {
            Glide.with(this)
                    .load(bitmap)
                    .circleCrop()
                    .into(mBinding.ivSelectProfile);
        } else {
            Toast.makeText(this, "is Null", Toast.LENGTH_SHORT).show();
        }
    }
}