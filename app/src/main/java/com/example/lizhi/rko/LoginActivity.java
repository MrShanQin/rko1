package com.example.lizhi.rko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class LoginActivity extends AppCompatActivity {
    private EditText editText_phone,editText_password;

    private String TOKEN;
    private Token_code token_code;
    private  int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_phone = (EditText) findViewById(R.id.login_number);
        editText_password = (EditText) findViewById(R.id.login_password_1);
    }
    public void login2(View view){
//        Intent intent = new Intent(LoginActivity.this,AreasActivity.class);
//        startActivity(intent);
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("phone",editText_phone.getText().toString())
                .add("password",editText_password.getText().toString())
                .build();
        final Request request = new Request.Builder()
                .url("http://47.104.72.161:8003/api/authorizations")
                //.addHeader("Authorization","bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8veGlhb2ZhbmcubG9jYWwvYXBpL2F1dGhvcml6YXRpb25zIiwiaWF0IjoxNTIyMTc3Nzc4LCJleHAiOjE1MjIxODg1NzgsIm5iZiI6MTUyMjE3Nzc3OCwianRpIjoiajNZd3hBbk5jaE1vN3FpTSIsInN1YiI6MjEsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.r_bekVdaT7cM24i6MPpdskFlI5SopPPJTLT6n5c6F94\"}")
                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {



                String DATA = response.body().string();
                Log.e("**************",DATA);
                try {
                    JSONObject jsonObject = new JSONObject(DATA);
                    TOKEN = jsonObject.optString("token");
                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("token",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Authorization",TOKEN);
                    editor.commit();

                    OkHttpClient okHttpClient2 = new OkHttpClient();
                    Request request2 = new Request.Builder()
                            .url("http://47.104.72.161:8003/api/areas")
                            .header("Authorization",TOKEN)
                            .build();
                    okHttpClient2.newCall(request2).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                JSONObject jsonObject1 = new JSONObject(response.body().string());
                                Log.e("-------------7",response.body().string());
                                id = jsonObject1.optInt("id");
                                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("token2",MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                                editor1.putInt("id",id);
                                editor1.commit();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (response.code() == 201 ){
                    Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(intent);
                }else if (response.code() == 400){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


    }
    public void Fpassword(View view){
        Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
