package com.example.proto.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.example.proto.BuildConfig;
import com.example.proto.Constants;

import org.jetbrains.annotations.NotNull;

public class PermissionUtil {

    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

    private PermissionUtil() {
    }

    private static class PermissionInstance {
        private static final PermissionUtil instance = new PermissionUtil();
    }

    public static PermissionUtil getInstance() {
        return PermissionInstance.instance;
    }

    /**
     * 전체 권한 요청
     *
     * @param activity
     */
    public void requestAllPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, permissions, Constants.REQUEST_ALL_PERMISSIONS);
    }

    /**
     * 권한 요청 결과
     *
     * @param requestCode
     * @param permissions
     */
    public void checkPermissionsResult(Context context, int requestCode, @NotNull String[] permissions) {
        if (requestCode == Constants.REQUEST_ALL_PERMISSIONS) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                    Log.e("권한 거부됨", permission);
                }
            }
        }
    }

    /**
     * 저장소 권한 허용 확인
     */
    public boolean checkStoragePermission(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 저장소 권한 요청
     *
     * @param activity
     */
    public void requestStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.REQUEST_STORAGE_PERMISSIONS);
    }

    /**
     * 앱 설정화면으로 이동동
     */
    public void manualSetPermission(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("저장소 접근 권한")
                .setMessage("파일 및 미디어 권한을 허용해주세요")
                .setPositiveButton("확인", (dialog, which) -> {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package",
                            BuildConfig.APPLICATION_ID, null);
                    intent.setData(uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                });
        builder.create();
        builder.show();
    }

}

