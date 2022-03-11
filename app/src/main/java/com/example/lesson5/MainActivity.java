package com.example.lesson5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity { //Update - Android 21

    private static final int REQUEST_CODE_TO_ACTIVITY2 = 101;
    public static final String KEY_DATA1 = "KEY_DATA1";
    public static final String KEY_DATA2 = "KEY_DATA2";
    public static final String KEY_DATA3 = "KEY_DATA3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button activityButton = (Button) findViewById(R.id.activity_button);

        // perform setOnClickListener event on Button
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // display a message by using a Toast
                Toast.makeText(getApplicationContext(), "Activity's Button", Toast.LENGTH_LONG).show();

                Intent in = new Intent(getApplicationContext(), Activity2.class);
                in.putExtra(KEY_DATA1, "String value tu Main Activity");
                in.putExtra(KEY_DATA2, 100);
                in.putExtra(KEY_DATA3, new Student("Tran Tuan Long", 25));

                startActivityForResult(in, REQUEST_CODE_TO_ACTIVITY2);
            }
        });

        FragmentManager fmr = getFragmentManager();

        SimpleFragment simpleFm = (SimpleFragment) fmr.findFragmentById(R.id.fragments);
        simpleFm.setupInfo();

        checkAndRequestPermissionIfNeeded();

    }

    private void checkAndRequestPermissionIfNeeded() {
        String[] params = null;
        String writeExternalStorage = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String readExternalStorage = Manifest.permission.READ_EXTERNAL_STORAGE;

        String cameraPermission = Manifest.permission.CAMERA;

        int hasWriteExternalStoragePermission;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            hasWriteExternalStoragePermission = PackageManager.PERMISSION_GRANTED;
        } else {
            hasWriteExternalStoragePermission = ActivityCompat.checkSelfPermission(this, writeExternalStorage);
        }

        int hasReadExternalStoragePermission = ActivityCompat.checkSelfPermission(this, readExternalStorage);

        int hasCameraPermission = ActivityCompat.checkSelfPermission(this, cameraPermission);

        List<String> permissions = new ArrayList<>();

        if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permissions.add(writeExternalStorage);
        if (hasReadExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permissions.add(readExternalStorage);
        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED)
            permissions.add(cameraPermission);

        if (!permissions.isEmpty()) {
            params = permissions.toArray(new String[permissions.size()]);
        }
        if (params != null && params.length > 0) {
            ActivityCompat.requestPermissions(MainActivity.this, params, 123);
        } else {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TO_ACTIVITY2) {

            String sData = data.getStringExtra(Activity2.AC2_KEY_DATA1);

            Log.d(MainActivity.class.getName(), sData);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123) {
            //checkAndRequestPermissionIfNeeded();
        }
    }

    public void doSomeThing() {
        Log.d("AAA", "do something");
    }

    @Override
    protected void onPause() {
        super.onPause();

        //viet code o day
    }

    @Override
    protected void onResume() {
        super.onResume();

        //viet code o day
    }

    @Override
    protected void onStop() {
        super.onStop();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}