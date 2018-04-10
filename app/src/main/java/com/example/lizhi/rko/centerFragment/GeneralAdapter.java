package com.example.lizhi.rko.centerFragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lizhi.rko.FacilityAddActivity;
import com.example.lizhi.rko.R;
import com.example.lizhi.rko.XQActivity;
import com.example.lizhi.rko.XuanzeActivity;

import com.example.lizhi.rko.model.DataBean;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GeneralAdapter extends BaseAdapter {

    private Context mContext;
    private List<DataBean> mDataList;
    private static final int REQUEST_CODE = 001;
    private String daaa;


    public GeneralAdapter(Context context, List<DataBean> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
//        this.mbean = mbean;
    }

    @Override
    public int getCount() {
//        return 2;
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
    public View getView(final int i, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView != null && convertView.getTag() != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_layout, null);
            holder.horizontalListView = (HorizontalListView)convertView.findViewById(R.id.horizontal_listview);
            holder.ImageBtn_3 = (ImageButton)convertView.findViewById(R.id.ImageBtn_3);
            holder.textView = (TextView) convertView.findViewById(R.id.mTextView_cgq);
            convertView.setTag(holder);
        }
            holder.textView.setText(mDataList.get(i).getName());
            ItemAdapter itemAdapter = new ItemAdapter(mContext,mDataList.get(i).getSubdevices());
            holder.horizontalListView.setAdapter(itemAdapter);
            holder.horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    pusher();
                    Log.e("22222222222", i+"");

                    Intent intent = new Intent(mContext, XQActivity.class);
                    intent.putExtra("shuju",pusher());
//                    intent.putExtra("item",i);
                    mContext.startActivity(intent);
                }
            });

        holder.ImageBtn_3.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Intent intent =new Intent(mContext, XuanzeActivity.class);
                intent.putExtra("int1",i);
                mContext.startActivity(intent);
            }
        });


        if (mDataList.get(i).isB_l()){
            holder.ImageBtn_3.setVisibility(View.VISIBLE);
        }else {
            holder.ImageBtn_3.setVisibility(View.GONE);
        }



        return convertView;
    }

    private String pusher() {
        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        Pusher pusher = new Pusher("5a6cf08ca094b6e02d65", options);

        Channel channel = pusher.subscribe("xiaofang_area.{01}");

        channel.bind("UpdateDeviceStatus", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                Log.e("---------s ",data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    daaa = jsonObject.optString("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        pusher.connect();
        return daaa;
    }


    public class ViewHolder {
        HorizontalListView horizontalListView;
        ImageButton ImageBtn_3;
        TextView textView;

    }


}
