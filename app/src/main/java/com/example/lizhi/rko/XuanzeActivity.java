package com.example.lizhi.rko;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uuzuche.lib_zxing.activity.CaptureActivity;

public class XuanzeActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 001;

    private Button mbutton1,mbutton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanze);
        mbutton1 = (Button) findViewById(R.id.mButton_saomiao);
        mbutton2 = (Button) findViewById(R.id.mButton_shoudong);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {//申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(XuanzeActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                intent1.getIntExtra("int1",99);

                Intent intent = new Intent(XuanzeActivity.this,FacilityAddActivity.class);
                intent.putExtra("intent",intent1.getIntExtra("int1",99));
                startActivity(intent);
                finish();
            }
        });
    }
}
