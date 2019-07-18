package com.example.test;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.itsrts.pptviewer.PPTViewer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();

                PPTViewer pptViewer = (PPTViewer) findViewById(R.id.pptviewer);
                pptViewer.setNext_img(R.drawable.next)
                        .setPrev_img(R.drawable.prev)
                        .setSettings_img(R.drawable.settings)
                        .setZoomin_img(R.drawable.zoomin)
                        .setZoomout_img(R.drawable.zoomout);
                pptViewer.loadPPT(MainActivity.this, "/storage/emulated/0/KakaoTalkDownload/avc1.ppt");


            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();




    }
}
