package com.example.lizhi.rko.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lizhi.rko.Adapter.FireAdapter;
import com.example.lizhi.rko.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsdf.s on 2018/3/22.
 */

public class Fragment_wcl extends Fragment {
    private ListView mListView;
    private FireAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.firefragment, container, false);

        mListView = (ListView) v.findViewById(R.id.mListView_wcl);
        List<FireWcl> fireWclList = new ArrayList<>();
        for (int i=0;i<3;i++){
            FireWcl fireWcl = new FireWcl();
            fireWclList.add(fireWcl);
        }
        adapter = new FireAdapter(fireWclList,getActivity());
        mListView.setAdapter(adapter);
        return v;
    }
}
