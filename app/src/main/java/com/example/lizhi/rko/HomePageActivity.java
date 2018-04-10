package com.example.lizhi.rko;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lizhi.rko.model.DataBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.xml.transform.Result;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomePageActivity extends AppCompatActivity {

    private RadioButton mRB1,mRB2,mRB3;
    private RadioGroup mRG;
    private FragmentTransaction mFT;
    private FragmentManager mFM;
    private HpFragment mHpF;
    private FaFragment mFaF;
    private UserFragment mUF;
    private String Authorization;
    private String DATA_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mRB1 = (RadioButton) findViewById(R.id.hp);
        mRB2 = (RadioButton) findViewById(R.id.fa);
        mRB3 = (RadioButton) findViewById(R.id.user);
        SharedPreferences preferences = getSharedPreferences("token",MODE_PRIVATE);
        Authorization = preferences.getString("Authorization","");

        initView();
        setClick(0);
    }

    private void initView() {
        mRG = (RadioGroup) findViewById(R.id.RG);
        mFM = getFragmentManager();
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.hp:
                        setClick(0);
                        break;
                    case R.id.fa:
                        click();
                        setClick(1);
                        break;
                    case R.id.user:
                        setClick(2);
                        break;
                }
            }
        });
    }

    private void click(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://51wanshan.com:8003/api/sensors")
                .header("Authorization",Authorization)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                DATA_1= response.body().string();
//                jiexi(DATA_1);
                Log.e("sssssss", DATA_1);
                SharedPreferences sharedPreferences = HomePageActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("DATA_1",DATA_1);
                editor.commit();
            }

//            private void jiexi(String data_1) {
//                try {
//                    JSONObject jsonObject = new JSONObject(data_1);
//                    DataBean dataBean = new DataBean();
//
//                    for (int i = 0; i <jsonObject.length() ; i++) {
//                        dataBean.setId(jsonObject.optInt("id"));
//                        dataBean.setArea_id(jsonObject.optInt("area_id"));
//                        dataBean.setName(jsonObject.optString("name"));
//
//                    }
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
        });
    }

    private void setClick(int type) {
        mFT = mFM.beginTransaction();
        hideFragment(mFT);
        switch (type){
            case 0:
                if (mHpF ==null){
                    mHpF = new HpFragment();
                    mFT.add(R.id.fragment,mHpF);
                }else {
                    mFT.show(mHpF);
                }break;
            case 1:
                if (mFaF ==null){
                    mFaF = new FaFragment();
                    mFT.add(R.id.fragment,mFaF);
                }else {
                    mFT.show(mFaF);
                }break;
            case 2:
                if (mUF ==null){
                    mUF = new UserFragment();
                    mFT.add(R.id.fragment,mUF);
                }else {
                    mFT.show(mUF);
                }break;
        }mFT.commitAllowingStateLoss();

    }

    /**
     * 隐藏fragment
     * @param mFT
     */
    private void hideFragment(FragmentTransaction mFT) {
        //如果此Fragment不为空就隐藏起来
        if (mHpF != null){
            mFT.hide(mHpF);
        }
        if (mFaF != null){
            mFT.hide(mFaF);
        }
        if (mUF != null){
            mFT.hide(mUF);
        }
    }


}
