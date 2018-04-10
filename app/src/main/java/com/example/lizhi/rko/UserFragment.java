package com.example.lizhi.rko;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

/**
 * Created by LIzhi on 2018/3/21.
 */

public class UserFragment extends Fragment {
    private TextView textView1,textView2,textView3,textView4;
    private Button mbtn;
    private EasyPopup mEasyPopup;
    private RelativeLayout mRLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment,container,false);
        textView1 = (TextView) view.findViewById(R.id.textView1);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        textView3 = (TextView) view.findViewById(R.id.textView3);
        textView4 = (TextView) view.findViewById(R.id.textView4);
        mbtn = (Button) view.findViewById(R.id.mbtn_exit);
        mRLayout = (RelativeLayout) view.findViewById(R.id.user_1);
        mEasyPopup = new EasyPopup(this.getActivity());
        mEasyPopup.setContentView(R.layout.exitorno);
        mEasyPopup.setAnimationStyle(R.style.popwindow_style);
        mEasyPopup.setFocusAndOutsideEnable(true);
        mEasyPopup.setBackgroundDimEnable(true);
        mEasyPopup.setDimValue(0.6f);
        mEasyPopup.createPopup();
        click();
        return view;
    }


    private void click() {
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HelpActivity.class);
                startActivity(intent);
                // TODO: 2018/3/22 使用帮助
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),UserChangeActivity.class);
                startActivity(intent);
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SetActivity.class);
                startActivity(intent);
                // TODO: 2018/3/22   设置

            }
        });
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEasyPopup.showAtAnchorView(mRLayout, VerticalGravity.CENTER, HorizontalGravity.CENTER,0,0);
            }
        });
    }
}
