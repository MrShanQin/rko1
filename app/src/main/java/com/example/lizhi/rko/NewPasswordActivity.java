package com.example.lizhi.rko;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

public class NewPasswordActivity extends AppCompatActivity {

    private EasyPopup mEasyPopup,mEasyPopup_save;
    private RelativeLayout mRLayout;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        mRLayout = (RelativeLayout) findViewById(R.id.RelativeLayout_changpassword);
        mEasyPopup = new EasyPopup(this);
        mEasyPopup.setContentView(R.layout.changepassword);
        mEasyPopup.setAnimationStyle(R.style.popwindow_style);
        mEasyPopup.setFocusAndOutsideEnable(true);
        mEasyPopup.setBackgroundDimEnable(true);
        mEasyPopup.setDimValue(0.6f);
        mEasyPopup.createPopup();

        button = mEasyPopup.getView(R.id.change_succeed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/23
               finish();
               mEasyPopup.dismiss();

            }
        });
    }
    public void newPassword(View view){
        // TODO: 2018/3/19
        mEasyPopup.showAtAnchorView(mRLayout, VerticalGravity.CENTER, HorizontalGravity.CENTER,0,0);
    }
}
