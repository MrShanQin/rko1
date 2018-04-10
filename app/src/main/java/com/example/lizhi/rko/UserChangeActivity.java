package com.example.lizhi.rko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import static com.example.lizhi.rko.R.id.main;

public class UserChangeActivity extends AppCompatActivity{

    private ImageView imageView ,imageView2;
    private Button btn, btn1,btn2,btn3,btn4;
    private EasyPopup mEsayPopup,mEasyPopup_save;
    private RelativeLayout mRL,mRLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change);
        imageView = (ImageView) findViewById(R.id.userchange_back);
        imageView2 = (ImageView) findViewById(R.id.ImageView_head);
        btn = (Button) findViewById(R.id.save);
        mRL = (RelativeLayout) findViewById(R.id.main);

        mEsayPopup = new EasyPopup(this);
        mEsayPopup.setContentView(R.layout.window_pupop);
        mEsayPopup.setAnimationStyle(R.style.popwindow_style);
        mEsayPopup.setFocusAndOutsideEnable(true);
        mEsayPopup.setBackgroundDimEnable(true);
        mEsayPopup.setDimValue(0.6f);
        mEsayPopup.createPopup();


        mRLayout = (RelativeLayout) findViewById(R.id.main);
        mEasyPopup_save = new EasyPopup(this);
        mEasyPopup_save.setContentView(R.layout.save);
        mEasyPopup_save.setAnimationStyle(R.style.popwindow_style);
        mEasyPopup_save.setFocusAndOutsideEnable(true);
        mEasyPopup_save.setBackgroundDimEnable(true);
        mEasyPopup_save.setDimValue(0.6f);
        mEasyPopup_save.createPopup();


        btn1 = mEsayPopup.getView(R.id.paizhao);
        btn2 = mEsayPopup.getView(R.id.xiangce);
        btn3 = mEsayPopup.getView(R.id.quxiao);
        btn4 = mEasyPopup_save.getView(R.id.save_succeed);

        click();


    }

    /**
     * 点击事件
     */
    private void click() {

        //back按钮
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击保存按钮
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/22保存功能
                mEasyPopup_save.showAtAnchorView(mRLayout,VerticalGravity.CENTER, HorizontalGravity.CENTER,0,0);
            }
        });
        // 点击头像
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/23
                mEsayPopup.showAtAnchorView(mRL,VerticalGravity.ALIGN_BOTTOM, HorizontalGravity.CENTER,0,0);
            }
        });



        //拍照
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/23
                Toast.makeText(getBaseContext(),"你点了拍照，但是我还没有给你写相机",Toast.LENGTH_LONG).show();
                mEsayPopup.dismiss();
            }
        });
        //从相册选照片的点击事件
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/23、
                Toast.makeText(getBaseContext(),"你点了相册选相片，但是我还没有找到你的相册",Toast.LENGTH_LONG).show();
                mEsayPopup.dismiss();
            }
        });
        //取消按钮
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEsayPopup.dismiss();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
