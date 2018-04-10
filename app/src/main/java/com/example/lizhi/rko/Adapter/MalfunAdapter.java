package com.example.lizhi.rko.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lizhi.rko.Class.MalfunClass;
import com.example.lizhi.rko.R;

import java.util.List;

/**
 * Created by wsdf.s on 2018/3/28.
 */

public class MalfunAdapter extends BaseAdapter {
    private List<MalfunClass> malfunClassList = null ;
    private Context context = null;
    private LayoutInflater inflater = null;

    public MalfunAdapter( List<MalfunClass> malfunClassList,Context context){
        this.malfunClassList = malfunClassList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return malfunClassList.size();
    }

    @Override
    public Object getItem(int position) {
        return malfunClassList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MalfunClass malfunClass = malfunClassList.get(position);
        View view =inflater.inflate(R.layout.item_malfun,null);
        TextView mTextView = (TextView) view.findViewById(R.id.mTextView_facilityName);
        TextView mTextView2 = (TextView) view.findViewById(R.id.mTextView_facilityAddress);
        TextView mTextView3 = (TextView) view.findViewById(R.id.mTextView_questionForm);
        TextView mTextView4 = (TextView) view.findViewById(R.id.mTextView_distributePeople);
        TextView mTextView5 = (TextView) view.findViewById(R.id.mTextView_phoneNumber);
        TextView mTextView6 = (TextView) view.findViewById(R.id.mTextView_suggest);

        return view;
    }
}
