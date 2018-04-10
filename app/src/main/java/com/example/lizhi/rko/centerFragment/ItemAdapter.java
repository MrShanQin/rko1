package com.example.lizhi.rko.centerFragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lizhi.rko.R;
import com.example.lizhi.rko.model.Data2Bean;
import com.example.lizhi.rko.model.DataBean;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<Data2Bean> mDataList;


    public ItemAdapter(Context context, List<Data2Bean> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
//        return 1;
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView != null && convertView.getTag() != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_item_layout, null);
            holder.imageButton = (ImageButton) convertView.findViewById(R.id.ImageBtn_2);
            holder.mTextView1 = (TextView) convertView.findViewById(R.id.mTextView_data);
            holder.mTextView2 = (TextView) convertView.findViewById(R.id.mTextView_name);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.llllll);
            convertView.setTag(holder);
        }
       // holder.mTextView1.setText(mDataList.get(position).getUd());
        holder.mTextView2.setText(mDataList.get(position).getName());


        return convertView;
    }

    public class ViewHolder {
        TextView mTextView1,mTextView2;
        ImageButton imageButton;
        LinearLayout layout;
    }


}
