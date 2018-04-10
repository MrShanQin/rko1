package com.example.lizhi.rko.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lizhi.rko.Homepageinfo;
import com.example.lizhi.rko.R;

import java.util.List;


/**
 * Created by LIzhi on 2018/3/26.
 */

public class HomepageAdapter extends BaseAdapter {
    private List<Homepageinfo>  homepageinfoList;
    private LayoutInflater inflater;
    public HomepageAdapter(){}
    public HomepageAdapter(List<Homepageinfo>  homepageinfoList, Context context){
        this.homepageinfoList = homepageinfoList;
        this.inflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return homepageinfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view =inflater.inflate(R.layout.item_homepage,null);
        Homepageinfo homepageinfo = (Homepageinfo) getItem(position);
        TextView textView_info = (TextView) view.findViewById(R.id.textView_info);
        ImageView imageView_state = (ImageView) view.findViewById(R.id.imageview_state);
        TextView textView_time = (TextView) view.findViewById(R.id.textview_time);
        ImageView imageView_point = (ImageView) view.findViewById(R.id.imageView_point);

        // TODO: 2018/3/27   这里获取服务器数据然后加载进listView 
       /* textView_info.setText(homepageinfo.getInfo());
        imageView_state.setImageResource(homepageinfo.getColor());
        textView_time.setText(homepageinfo.getTime());
        imageView_point.setImageResource(homepageinfo.getColor_point());*/
        return view;
    }
}
