package com.example.lizhi.rko.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lizhi.rko.Fragment.Fragment_clz;
import com.example.lizhi.rko.Fragment.Fragment_wcl;
import com.example.lizhi.rko.Fragment.Fragment_ycl;

/**
 * Created by wsdf.s on 2018/3/22.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 3;
    private Fragment_wcl f1 = null;
    private Fragment_clz f2 = null;
    private Fragment_ycl f3 = null;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        f1 = new Fragment_wcl();
        f2 = new Fragment_clz();
        f3 = new Fragment_ycl();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            // TODO: 2018/3/22
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;

    }
}
