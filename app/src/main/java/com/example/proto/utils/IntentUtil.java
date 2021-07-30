package com.example.proto.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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
        String uri = Constants.BASE_PLAYSTORE_URL + packageName;
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
     * 네이미앱으로 이동
     */
    public void moveToNamee() {
        Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(Constants.NAMEE_PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
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

    /**
     * 사진파일을 가져온것인지 확인
     *
     * @param result
     * @return
     */
    public boolean resultFromGallery(ActivityResult result) {
        return result.getData().getDataString().contains("image");

    }

    /**
     * 웹브라우저 띄우기
     * @param url
     */
    public void moveToBrowser(String url) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        if (checkLaunched(Constants.BROWSER_PACKAGE_NAME)) {
            i.setPackage(Constants.BROWSER_PACKAGE_NAME);
        } else if (checkLaunched(Constants.CHROME_PACKAGE_NAME)) {
            i.setPackage(Constants.CHROME_PACKAGE_NAME);
        }
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }

}
