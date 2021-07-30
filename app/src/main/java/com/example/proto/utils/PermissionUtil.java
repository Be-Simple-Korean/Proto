package com.example.proto.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

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
     * @param activity
     */
    public void requestAllPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, permissions, Constants.REQUEST_ALL_PERMISSIONS);
    }

    /**
     * 권한 요청 결과
     * @param context
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
}

