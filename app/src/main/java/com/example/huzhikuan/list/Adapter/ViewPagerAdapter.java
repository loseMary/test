package com.example.huzhikuan.list.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzhikuan on 2018/9/4.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment = new ArrayList<>();
    private Context context;

    public ViewPagerAdapter(FragmentManager fm,Context context, List<Fragment> listFragment) {
        super(fm);
        this.context = context;
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

}
