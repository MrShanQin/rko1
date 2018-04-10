package com.example.lizhi.rko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lizhi.rko.Class.DataClass;
import com.example.lizhi.rko.view.MyDashBoardView;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class XQActivity extends AppCompatActivity {

    private MyDashBoardView dashView;
    int count=0;
    private String DATA;
    private String data1,data2,data3,data4;
    private int id ,a;

    private DataClass dataClass;
    private String[] data_noname;
    private RadioGroup rg_1,rg_2;
    private RadioButton rb_1,rb_2,rb_3,rb_4,rb_5,rb_6,rb_7,rb_8;
    private RadioGroup.OnCheckedChangeListener listener1,listener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);

        init();
        onClick();

        SharedPreferences preferences = getSharedPreferences("token2",MODE_PRIVATE);
        id = preferences.getInt("id",5);
        dashView = (MyDashBoardView) this.findViewById(R.id.dashview);

    }

    private void onClick() {
        rg_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_1:
                        if (pusher()==null){
                            //Toast.makeText(XQActivity.this,"目前没有数据",Toast.LENGTH_LONG).show();
                        }else{
                            Handler handler = new Handler() {//刷新页面
                                @Override
                                public void handleMessage(Message msg) {
                                    super.handleMessage(msg);
                                    if (msg.what == 1) {
                                        dataClass=data(noname()[0]);
                                        a=Integer.parseInt(dataClass.getShuju1()+dataClass.getShuju2(),16);
                                        count = a;
                                        Log.e("-------7777",+count+"");
                                        dashView.setCurrentValue(count);//  这里放数据进表盘
                                        if (count==100) count =0;
                                        sendEmptyMessageDelayed(1, 1000);
                                    }
                                }
                            };
                            handler.sendEmptyMessage(1);
                        }



                        break;
                }
            }
        });
    }

    /**
     * 将通道1的数据传进来
     * @param s
     */
    private DataClass data(String s) {
        String[] a = s.split("");
        DataClass dataClass = new DataClass();
                dataClass.setTd(a[0]+a[1]);
                dataClass.setSbh(a[2]+a[3]);
                dataClass.setShuju1(a[4]+a[5]);
                dataClass.setShuju2(a[6]+a[7]);
                dataClass.setFireinfo1(a[8]+a[9]);
                dataClass.setFireinfol2(a[10]+a[11]);

                return dataClass;
    }

//    private void shan(int i) {
//        String data_ = dataClassList.get(i).getShuju1()+dataClassList.get(i).getShuju2();
//        a= Integer.parseInt(data_,16);
//    }

    /**
     * 初始化控件
     */
    private void init() {
        rg_1 = (RadioGroup) findViewById(R.id.mrg_top);
        rg_2 = (RadioGroup) findViewById(R.id.mrg_botm);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);
        rb_5 = (RadioButton) findViewById(R.id.rb_5);
        rb_6 = (RadioButton) findViewById(R.id.rb_6);
        rb_7 = (RadioButton) findViewById(R.id.rb_7);
        rb_8 = (RadioButton) findViewById(R.id.rb_8);
    }

    private String pusher() {
        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        Pusher pusher = new Pusher("5a6cf08ca094b6e02d65", options);
        Channel channel = pusher.subscribe("xiaofang_area."+id);
        Log.e("---------22 ","执行"+id );
        channel.bind("UpdateDeviceStatus", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                //Log.e("---------s ",data);
                try {
                    JSONObject jsonObject1 = new JSONObject(data);
                    // TODO: 2018/4/4   这里接受pusher反给我的数据 
                    data1 = jsonObject1.optString("data");
                    //Log.e("---------s ",data1);
                    JSONObject jsonObject2 = new JSONObject(data1);
                    data2 = jsonObject2.optString("sensors");
                    //Log.e("---------s ",data2);
                    JSONObject jsonObject3 = new JSONObject(data2);
                    data3 = jsonObject3.getString("1");
                    //Log.e("xxxxxxxxx",data3);
                    JSONArray jsonArray = new JSONArray(data3);

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject4 = (JSONObject) jsonArray.get(i);
                        data4 = jsonObject4.optString("data"); //  8个通道的数据
                        Log.e("xxxxxxxxx",data4);
                    }



                    Log.e("3333333",noname()[0]);
//                    JSONObject jsonObject4 = new JSONObject(data3);
//                    data4 = jsonObject4.optString("data");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        pusher.connect();
        return data4;
    }

    /**
     * 这里将8个通道的数据整好
     *
     */

    private String[] noname() {
        if (pusher()==null){
            Toast.makeText(this,"没有收到数据",Toast.LENGTH_LONG);
            return null;
        }else {
            data_noname = pusher().split("_");
            Log.e("----------",data_noname[0]);//00 00 00 00 00 00
            return data_noname;
        }

    }

    /**
     * 数据放入实体类
     */
//    private void data( String[] data_noname) {
//
//        if (data_noname!=null){
//            for (int i = 0; i < 8; i++) {
//                String[] a = data_noname[i].split("");
//                DataClass dataClass = new DataClass();
//                dataClass.setTd(a[0]+a[1]);
//                dataClass.setSbh(a[2]+a[3]);
//                dataClass.setShuju1(a[4]+a[5]);
//                dataClass.setShuju2(a[6]+a[7]);
//                dataClass.setFireinfo1(a[8]+a[9]);
//                dataClass.setFireinfol2(a[10]+a[11]);
//                dataClassList.add(dataClass);
//
//            }
//        }else {
//            Log.e("----------null",data_noname[0]);//00 00 00 00 00 00
//            Toast.makeText(this,"没有收到数据",Toast.LENGTH_LONG);
//
//
//        }

//        if (dataClassList.get().equals("00")){
//            dashView.setmHeadText("可燃气体");
//        }else if (dataClassList.get(1).equals("01")){
//            dashView.setmHeadText("电器火灾");
//        }else if (dataClassList.get(1).equals("02")){
//            dashView.setmHeadText("火焰探测");
//        }else if (dataClassList.get(1).equals("03")){
//            dashView.setmHeadText("烟雾报警");
//    }

}

