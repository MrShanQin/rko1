package com.example.lizhi.rko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lizhi.rko.Adapter.MalfunAdapter;
import com.example.lizhi.rko.Class.MalfunClass;

import java.util.ArrayList;
import java.util.List;

public class MalfunNewsActivity extends AppCompatActivity {

    private ListView mListView;
    private MalfunAdapter malfunAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malfun_news);
        List<MalfunClass> malfunClassList = new ArrayList<>();


        for (int i = 0;i<3 ;i++){
            MalfunClass malfunClass =new MalfunClass();
            malfunClassList.add(malfunClass);
        }
        malfunAdapter = new MalfunAdapter(malfunClassList,this);
        mListView = (ListView) findViewById(R.id.mListView_Malfun);
        mListView.setAdapter(malfunAdapter);
    }
}
