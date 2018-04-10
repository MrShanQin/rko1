package com.example.lizhi.rko;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.lizhi.rko.Class.UserRegister;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EasyPopup mEasyPopup;
    private RelativeLayout mRLayout;
    private Button button,button_code,button_register;
    private EditText editText_Name,editText_Phone,editText_Code,editText_Password,editText_Password2;
    private String PATH = "http://47.104.72.161:8003/api/verification_code?phone=13738359552";
    private String key;
    private String DATA;
    private String phone;
    private long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


        mRLayout = (RelativeLayout) findViewById(R.id.RelativeLayout_zhuce);
        mEasyPopup = new EasyPopup(this);
        mEasyPopup.setContentView(R.layout.succeed);
        mEasyPopup.setAnimationStyle(R.style.popwindow_style);
        mEasyPopup.setFocusAndOutsideEnable(true);
        mEasyPopup.setBackgroundDimEnable(true);
        mEasyPopup.setDimValue(0.6f);
        mEasyPopup.createPopup();



        button = mEasyPopup.getView(R.id.register_succeed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/23
                mEasyPopup.dismiss();
                finish();
                
            }
        });
        button_code.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/27
                getDatasync();
                //创建OKHTTP对象
                /**OkHttpClient okHttpClient = new OkHttpClient();
                FormBody body = new FormBody.Builder()
                        .add("key","x5CKomN3H0kI9F8")
                        .add("expired_at","2018-03-24 12:31:43").build();

                    Request request = new Request.Builder().url(PATH).post(body);
                    okHttpClient.newCall();
               */}
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register2();
            }
        });

    }

    /**
     * 初始化数据
     */
    private void init() {
        editText_Name=(EditText) findViewById(R.id.user_name);
        editText_Phone = (EditText) findViewById(R.id.user_number);
        editText_Code = (EditText) findViewById(R.id.user_code);
        editText_Password = (EditText) findViewById(R.id.user_password_1);
        editText_Password2 = (EditText) findViewById(R.id.user_password_2);
        button_code = (Button) findViewById(R.id.btn_code);
        button_register = (Button) findViewById(R.id.btn_register);
    }




    /**
     * 点击获取短信验证码
     */
    public void getDatasync(){
        time = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        final int mHour = calendar.get(Calendar.HOUR);
        final int mMinute = calendar.get(Calendar.MINUTE);
        final int mYear = calendar.get(Calendar.YEAR);
        final int mMonth =calendar.get(Calendar.MONTH);
        final int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        final int mS = calendar.get(Calendar.SECOND);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                    FormBody body = new FormBody.Builder()
                            .add("phone",editText_Phone.getText().toString())
//                            .add("key","S1HOEdrT7ISH4o4")
//                            .add("expired_at",mYear+"-"+mMonth+"-"+mDay+" "+mHour+":"+mMinute+":"+mS)
                            .build();
                    Request request = new Request.Builder()//创建Request 对象
                            .url(PATH)//请求接口。如果需要传参拼接到接口后面。
                            .post(body)
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            DATA = response.body().string();// TODO: 这里的问题， response.body().string()调用一次就会关闭流，所以要用DATA保存服务器返回的数据
                            Log.e("---------------1",DATA);
                            Log.e("---------------2",response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(DATA);
                                key = jsonObject.optString("key");
                                phone =jsonObject.optString("phone");
                                Log.e("---------------",key);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (response.code()==400){

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this,phone,Toast.LENGTH_LONG).show();

                                    }
                                });
                                //Toast.makeText(RegisterActivity.this,"获取成功",Toast.LENGTH_LONG).show();
                            }else if (response.code()==201){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this,"获取成功",Toast.LENGTH_LONG).show();

                                    }
                                });
                            }else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this,"获取失败",Toast.LENGTH_LONG).show();

                                    }
                                });
                            }
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void register2(){
        if (editText_Password2.getText().toString().equals(editText_Password.getText().toString())){
            {
                OkHttpClient client = new OkHttpClient();
                FormBody body = new FormBody.Builder()
                        .add("verification_key",key)
                        .add("verification_code",editText_Code.getText().toString())
                        .add("password",editText_Password.getText().toString())
                        .add("phone",editText_Phone.getText().toString())
                        .add("name",editText_Name.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url("http://47.104.72.161:8003/api/users")
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    DATA = response.body().string();
                                    Log.e("------------",DATA);
                                    final JSONObject jsonObject = new JSONObject(DATA);

                                    if (response.code() == 200) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                mEasyPopup.showAtAnchorView(mRLayout, VerticalGravity.CENTER, HorizontalGravity.CENTER, 0, 0);
                                            }
                                        });

                                        //Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();

                                    } else if (response.code() == 422){
                                        runOnUiThread(new Runnable() {
                                            String error = jsonObject.optString("error");
                                            @Override
                                            public void run() {
                                                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    }else {
                                        runOnUiThread(new Runnable() {
                                            String phone = jsonObject.optString("phone");
                                            @Override
                                            public void run() {
                                                Toast.makeText(RegisterActivity.this, phone, Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                        //DATA = response.body().string();
                        //Log.e("*************",response.body().string());

//                               mEasyPopup.showAtAnchorView(mLayout, VerticalGravity.CENTER, HorizontalGravity.CENTER,0,0);


                    }
                });
            }

        }else {
            Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_LONG).show();
        }
        // TODO: 2018/3/19



    }
}
