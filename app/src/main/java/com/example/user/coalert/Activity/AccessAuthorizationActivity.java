package com.example.user.coalert.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.coalert.R;
import com.example.user.coalert.Singleton.Singleton;

public class AccessAuthorizationActivity extends AppCompatActivity {
    Button accessBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.access_authorization);
        accessBtn = findViewById(R.id.authorization_confirm_button);
        accessBtn.setOnClickListener(mainClickListener);
        getUUID(getBaseContext());
    }
    void getUUID(Context mContext) {
        TelephonyManager mgr = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint({"HardwareIds", "MissingPermission"}) String idByTelephonyManager = mgr.getDeviceId();
            Log.e("UUID: ", idByTelephonyManager);
            Singleton.getInstance().setIndependenceNum(idByTelephonyManager);
    }
    Button.OnClickListener mainClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent mainActivity = new Intent(AccessAuthorizationActivity.this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
    };
}
