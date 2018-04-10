package com.example.lizhi.rko;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.lizhi.rko.Adapter.HomepageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIzhi on 2018/3/21.
 */

public class HpFragment extends Fragment {
    private Button button1,button2,button3,button4,button5,button6;
    private ListView mListView;
    private HomepageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.hp_fragment,container,false);
        button_sm(view);
        List<Homepageinfo> homepageinfoList = new ArrayList<>();
        for (int i=0;i<4;i++){
            Homepageinfo homepageinfo = new Homepageinfo();
            homepageinfoList.add(homepageinfo);
        }
        adapter = new HomepageAdapter(homepageinfoList,getActivity());
        mListView.setAdapter(adapter);
        Click();
        return view;
    }

    public void Click(){
        //火灾信息
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FireInformationActivity.class);
                startActivity(intent);
            }
        });
        //故障信息
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MalfunNewsActivity.class);
                startActivity(intent);
            }
        });
        //消息中心
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MassageCenterActivity.class);
                startActivity(intent);
            }
        });
        //值班信息
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WorkInforActivity.class);
                startActivity(intent);
            }
        });
        //状态监测
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2018/3/22  
            }
        });
        //拍照上传
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PhotoActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 6个按钮的初始化
     * @param view
     */
    private void button_sm(View view) {
        button1 = (Button) view.findViewById(R.id.button_1);
        button2 = (Button) view.findViewById(R.id.button_2);
        button3 = (Button) view.findViewById(R.id.button_3);
        button4 = (Button) view.findViewById(R.id.button_4);
        button5 = (Button) view.findViewById(R.id.button_5);
        button6 = (Button) view.findViewById(R.id.button_6);
        mListView = (ListView) view.findViewById(R.id.mListView_hp);
    }


}
