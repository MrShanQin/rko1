package com.example.lizhi.rko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lizhi.rko.model.Token_code;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GroupAddActivity extends AppCompatActivity {

    private EditText editText ;
    private Button button;
    private int i;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
//        Intent intent = getIntent();
//        token = intent.getStringExtra("token");
        SharedPreferences preferences = getSharedPreferences("token",MODE_PRIVATE);
        final String Authorization = preferences.getString("Authorization","");
        Log.e("*********",Authorization);
        editText = (EditText) findViewById(R.id.mEditText);
        button = (Button) findViewById(R.id.mButton);
/**
 * 点击确定添加分组
 */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody body = new FormBody.Builder()
                        .add("name",editText.getText().toString())
                        .add("area_id","1")
                        .build();

                Request request =new Request.Builder()
                        .url("http://47.104.72.161:8003/api/sensors")
                        .header("Authorization",Authorization)
                        .post(body)
                        .build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String date = response.body().string();
                        Log.e("--------1",response.toString());
                        Log.e("--------2",date);
                        if (response.code() == 200){
                            finish();//销毁页面
                            // TODO: 2018/4/2 刷新listview



                        }else if (response.code() == 401){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(GroupAddActivity.this,"添加失败", Toast.LENGTH_LONG).show();
                                }
                            });
                        }else if (response.code()==400){
                            try {
                                JSONObject jsonObject = new JSONObject(date);
                                final String  name = jsonObject.optString("name");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(GroupAddActivity.this,name, Toast.LENGTH_LONG).show();
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else if (response.code() == 403){
                            try {
                                JSONObject jsonObject = new JSONObject(date);
                                final String  errors = jsonObject.optString("errors");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(GroupAddActivity.this,errors, Toast.LENGTH_LONG).show();
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
            }
        });
    }
}
