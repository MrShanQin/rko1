package com.example.lizhi.rko.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lizhi.rko.R;

/**
 * Created by wsdf.s on 2018/3/22.
 */

public class Fragment_ycl extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View V =inflater.inflate(R.layout.firefragment,container,false);
        //TextView textView = (TextView) V.findViewById(R.id.tv1);
        //textView.setText("这里是已处理");
        return V;
    }
}
