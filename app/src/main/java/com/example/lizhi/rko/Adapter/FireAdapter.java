package com.example.lizhi.rko.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lizhi.rko.Fragment.FireWcl;
import com.example.lizhi.rko.R;

import java.util.List;

/**
 * Created by wsdf.s on 2018/3/28.
 */

public class FireAdapter extends BaseAdapter {
    private List<FireWcl> fireWclsList = null ;
    private Context context = null;
    private LayoutInflater inflater = null;

    public FireAdapter(List<FireWcl> fireWclsList,Context context){
        this.fireWclsList = fireWclsList;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return fireWclsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View view =inflater.inflate(R.layout.item_fire,null);
        View view = inflater.inflate(R.layout.item_fire,null);
        FireWcl fireWcl = (FireWcl) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.textView_time2);
        TextView textView2 = (TextView) view.findViewById(R.id.textView_info2);
        return view;
    }
}
