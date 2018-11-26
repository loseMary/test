package com.example.huzhikuan.list.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.huzhikuan.list.Adapter.ListAdapter;
import com.example.huzhikuan.list.R;
import com.example.huzhikuan.list.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {
    private ListAdapter adapter;
    private MyListView listView;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        for (int i=0;i<50;i++){
            list.add("这是第"+i+"个item");
        }
        listView = (MyListView) findViewById(R.id.listView);
        adapter = new ListAdapter(this,list);
        listView.setAdapter(adapter);
    }
}
