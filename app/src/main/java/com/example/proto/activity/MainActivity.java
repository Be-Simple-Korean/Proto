package com.example.proto.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.proto.Constants;
import com.example.proto.R;
import com.example.proto.databinding.ActivityMainBinding;
import com.example.proto.fragment.FirstCardFragment;
import com.example.proto.utils.PermissionUtil;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        PermissionUtil.getInstance().requestAllPermissions(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==Constants.REQUEST_ALL_PERMISSIONS){
            PermissionUtil.getInstance().checkPermissionsResult(this, requestCode, permissions);
            init();
        }


    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(mBinding.bnv, navController);
    }

}