package com.example.proto.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;

import com.example.proto.Constants;

public class IntentUtil {

    private Context mContext;

    private IntentUtil() {
    }

    private static class IntentInstance {
        private static final IntentUtil instance = new IntentUtil();
    }

    public static IntentUtil getInstance() {
        return IntentInstance.instance;
    }

    public static void create(Context context) {
        getInstance().mContext = context;
    }

    /**
     * 앱 설치 여부 확인
     *
     * @param packageName
     * @return
     */
    public boolean checkLaunched(String packageName) {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(packageName);
        return intent != null;
    }

    /**
     * 플레이스토어로 이동
     *
     * @param packageName
     */
    public void moveToStore(String packageName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        String uri = Constants.BASE_PLAY_URL_ + packageName;
        intent.setData(Uri.parse(uri));
        mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 네이버페이로 이동
     */
    public void moveToNaverPay() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(Constants.CONNECT_NAVER_PAY_URL));
        mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 갤러리로 이동
     *
     * @param startActivityResult
     */
    public void moveToFolder(ActivityResultLauncher<Intent> startActivityResult, String mimeType) {
        Intent intent = new Intent();
        intent.setType(mimeType);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityResult.launch(intent);
    }

    public boolean resultFromGallery(ActivityResult result) {
        return result.getData().getDataString().contains("image");

    }

}
