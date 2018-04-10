package com.example.lizhi.rko;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lizhi.rko.Fragment.Fragment_clz;
import com.example.lizhi.rko.Fragment.Fragment_wcl;
import com.example.lizhi.rko.Fragment.Fragment_ycl;

public class FireInformationActivity extends AppCompatActivity {

    ImageView imageView ;
    private RadioGroup mRG1;
    private RadioButton mRB_1,mRB_2,mRB_3;
    private FragmentTransaction mFT;
    private FragmentManager mFM;
    private Fragment_wcl fragment_wcl;
    private Fragment_clz fragment_clz;
    private Fragment_ycl fragment_ycl;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_information);
        mRB_1 = (RadioButton) findViewById(R.id.wcl);
        mRB_2 = (RadioButton) findViewById(R.id.clz);
        mRB_3 = (RadioButton) findViewById(R.id.ycl);
        imageView = (ImageView) findViewById(R.id.back_fire_informationg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        setClick(0);
    }

    private void initView() {
        mRG1 = (RadioGroup) findViewById(R.id.RadioGroup);
        mFM = getFragmentManager();
        mRG1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.wcl:
                        setClick(0);
                        break;
                    case R.id.clz:
                        setClick(1);
                        break;
                    case R.id.ycl:
                        setClick(2);
                        break;
                }
            }
        });
    }

    private void setClick(int type) {
        mFT = mFM.beginTransaction();
        hideFragment(mFT);
        switch (type){
            case 0:
                if (fragment_wcl ==null){
                    fragment_wcl = new Fragment_wcl();
                    mFT.add(R.id.fragment1,fragment_wcl);
                }else {
                    mFT.show(fragment_wcl);
                }break;
            case 1:
                if (fragment_clz ==null){
                    fragment_clz = new Fragment_clz();
                    mFT.add(R.id.fragment1,fragment_clz);
                }else {
                    mFT.show(fragment_clz);
                }break;
            case 2:
                if (fragment_ycl ==null){
                    fragment_ycl = new Fragment_ycl();
                    mFT.add(R.id.fragment1,fragment_ycl);
                }else {
                    mFT.show(fragment_ycl);
                }break;
        }mFT.commitAllowingStateLoss();

    }

    /**
     * 隐藏fragment
     * @param mFT
     */
    private void hideFragment(FragmentTransaction mFT) {
        //如果此Fragment不为空就隐藏起来
        if (fragment_wcl != null){
            mFT.hide(fragment_wcl);
        }
        if (fragment_clz != null){
            mFT.hide(fragment_clz);
        }
        if (fragment_ycl != null){
            mFT.hide(fragment_ycl);
        }
    }
}
