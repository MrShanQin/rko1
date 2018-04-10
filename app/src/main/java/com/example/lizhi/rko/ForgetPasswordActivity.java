package com.example.lizhi.rko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
    private Button button_1,button_2;
    private EditText editText1,editText2,editText3,editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        init();
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/4/4   点击获取验证码

                
            }
        });


        button_2.setOnClickListener(new View.OnClickListener() {// 点击确定
            @Override
            public void onClick(View v) {

                if (editText3.getText().toString().equals(editText4.getText().toString())){
                    OkHttpClient okHttpClient = new OkHttpClient();
                    FormBody body = new  FormBody.Builder()
                            // TODO: 2018/4/4                         ↓ 
                            .add("verification_key","这里差个key")
                            .add("verification_code",editText2.getText().toString())
                            .add("password",editText3.getText().toString())
                            .add("phone",editText1.getText().toString())
                            .add("_method","PUT")
                            .build();
                    Request request = new Request.Builder()
                            .url("http://47.104.72.161:8003/api/users/reset_password")
                            .post(body)
                            .build();

                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.code() ==200){
                                Intent intent = new Intent(ForgetPasswordActivity.this,NewPasswordActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }else {
                    Toast.makeText(ForgetPasswordActivity.this,"两次输入密码不一致",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /**
     * 初始化控件
     */
    private void init() {
        button_1 = (Button) findViewById(R.id.btn_code_forget);
        button_2 = (Button) findViewById(R.id.change_password);
        editText1 = (EditText) findViewById(R.id.phone_forget);
        editText2 = (EditText) findViewById(R.id.username_code1);
        editText3 = (EditText) findViewById(R.id.username_password_new_1);
        editText4 = (EditText) findViewById(R.id.username_password_new_2);

    }



}
