package com.example.huzhikuan.list.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huzhikuan.list.Adapter.ViewPagerAdapter;
import com.example.huzhikuan.list.Fragment.FirstFragment;
import com.example.huzhikuan.list.Fragment.SecondFragment;
import com.example.huzhikuan.list.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private ViewPagerAdapter adapter ;
    private List<Fragment> listFragment = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        listFragment.add(FirstFragment.newInstance("first_1","first_2"));
        listFragment.add(SecondFragment.newInstance("second_1","second_2"));
        viewpager = findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),this,listFragment);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(2);
    }
}
