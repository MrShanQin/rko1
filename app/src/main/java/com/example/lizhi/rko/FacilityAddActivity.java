package com.example.lizhi.rko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FacilityAddActivity extends AppCompatActivity {
    private EditText editText1,editText2;
    private Button button;
    private RadioButton radioButton1,radioButton2;
    int TYPE_ID=1;
    private String Authorization;
    private int i;   //设备组编号-1
    private int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_add);
        SharedPreferences preferences = getSharedPreferences("token",MODE_PRIVATE);
        Authorization = preferences.getString("Authorization","");
        init();
        SharedPreferences preferences2 = getSharedPreferences("token2",MODE_PRIVATE);
        ID = preferences2.getInt("id",99);
        Intent intent = getIntent();
        i = intent.getIntExtra("intent",0100);
        Log.e( "-------------3",i+"" );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody body = new FormBody.Builder()
                        .add("name",editText1.getText().toString())
                        .add("mac",editText2.getText().toString())
                        .add("sensor_id", String.valueOf(i+1))
                        .add("type", String.valueOf(TYPE_ID))
                        .add("area_id", String.valueOf(ID))
                        .build();
                Request request = new Request.Builder()
                        .url("http://47.104.72.161:8003/api/sub_devices")
                        .header("Authorization",Authorization)
                        .post(body)
                        .build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // TODO: 2018/4/4    创建成功后返回fragment并刷新一次
                        Log.e("---------1 ",response.body().string() );
                        Log.e("---------2 ",response.toString() );
                        if (response.code() == 200){
                            finish();
                        }


                    }
                });
            }
        });

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TYPE_ID=1;
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TYPE_ID=0;
            }
        });
    }

    /**
     * 初始化控件
     */
    private void init() {
        button = (Button) findViewById(R.id.mbtn_facilityAdd);
        editText1 = (EditText) findViewById(R.id.mEditText_facility_name);
        editText2 = (EditText) findViewById(R.id.mEditText_facility_mac);
        radioButton1 = (RadioButton) findViewById(R.id.mRB_gprs);
        radioButton2 = (RadioButton) findViewById(R.id.mRB_wifi);
    }
}
