package com.example.lizhi.rko;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lizhi.rko.centerFragment.GeneralAdapter;
import com.example.lizhi.rko.centerFragment.ItemAdapter;
import com.example.lizhi.rko.centerFragment.MyListview;
import com.example.lizhi.rko.model.Data2Bean;
import com.example.lizhi.rko.model.DataBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FaFragment extends Fragment {

    private View view_fragment;
    private List<DataBean> list = new ArrayList<>();
    private MyListview lv_my_list;
    private Button btn;
    private ImageButton imageButton;
    private GeneralAdapter generalAdapter;
    private Button center_btn_set_false;
    private boolean is_show = false;
    private String data;
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view_fragment = inflater.inflate(R.layout.fragment_fa_layout, null);
        btn = (Button) view_fragment.findViewById(R.id.center_btn_set);
        imageButton = (ImageButton) view_fragment.findViewById(R.id.ImageBtn_1);
        center_btn_set_false = (Button) view_fragment.findViewById(R.id.center_btn_set_false);
        SharedPreferences preferences =getActivity().getSharedPreferences("data",MODE_PRIVATE) ;
        data = preferences.getString("DATA_1","");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setVisibility(View.VISIBLE);
                center_btn_set_false.setVisibility(View.VISIBLE);
                btn.setVisibility(View.GONE);

                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setB_l(!is_show);
                }
                generalAdapter.notifyDataSetChanged();
            }
        });
        center_btn_set_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setVisibility(View.GONE);
                center_btn_set_false.setVisibility(View.GONE);
                btn.setVisibility(View.VISIBLE);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setB_l(is_show);
                }
                generalAdapter.notifyDataSetChanged();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),GroupAddActivity.class);
                startActivity(intent);
            }
        });

        initLayout();
        jSONObject_jiexie();

       // initData();
        return view_fragment;
    }

    private void jSONObject_jiexie( ) {
        try {
//            ;
                JSONArray jsonArray =new JSONArray(data);
            for (int i = 0; i <jsonArray.length() ; i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                DataBean dataBean = new DataBean();
                dataBean.setArea_id(jsonObject.optInt("area_id"));
                dataBean.setId(jsonObject.optInt("id"));
                dataBean.setName(jsonObject.optString("name"));
                JSONArray jsonArray2 = new JSONArray(jsonObject.optString("subdevices"));
//                dataBean.setSubdevices(jsonObject.optString("subdevices"));
                for (int j = 0; j <jsonArray2.length() ; j++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray2.get(j);
                    Data2Bean data2Bean = new Data2Bean();
                    data2Bean.setId(jsonObject1.optInt("id"));
                    data2Bean.setMac(jsonObject1.optString("mac"));
                    data2Bean.setName(jsonObject1.optString("name"));
                    data2Bean.setSensor_id(jsonObject1.optInt("sensor_id"));
                    data2Bean.setUd(jsonObject1.optString("ud"));
                    dataBean.subdevices.add(data2Bean);
                }
//                ItemAdapter itemAdapter = new ItemAdapter(getActivity(),list2);

                //dataBean.setSubdevices((List<DataBean.SubdevicesBean>) jsonObject.optJSONArray("subdevices"));
                //dataBean.setB_l(false);
                list.add(dataBean);
            }
            generalAdapter = new GeneralAdapter(getActivity(),list);
            lv_my_list.setAdapter(generalAdapter);
//            Log.e("ttttttttttttt",data);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initLayout() {
        lv_my_list = (MyListview) view_fragment.findViewById(R.id.lv_my_list);

    }

    private void initData() {

        for (int i = 0; i < 3; i++) {
            DataBean dataBean = new DataBean();
            dataBean.setName("---"+i);
            dataBean.setB_l(false);
            list.add(dataBean);
        }


    }

}
