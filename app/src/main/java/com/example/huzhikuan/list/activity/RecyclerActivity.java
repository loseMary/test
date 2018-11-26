package com.example.huzhikuan.list.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.huzhikuan.list.Adapter.MyRecyclerViewAdapter;
import com.example.huzhikuan.list.R;
import com.example.huzhikuan.list.Utils.DividerGridViewItemDecoration;
import com.example.huzhikuan.list.Utils.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class RecyclerActivity extends FragmentActivity {
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        for (int i=0; i<6;i++){
            list.add(i+"A");
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置RecyclerView管理器
        //垂直
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //水平
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        //网格
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //瀑布
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,MyDividerItemDecoration.VERTICAL));
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,MyDividerItemDecoration.HORIZONTAL));

//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        recyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));
        adapter = new MyRecyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon, String data) {
                Toast.makeText(RecyclerActivity.this,"点击的是："+data,Toast.LENGTH_SHORT).show();

            }
        });
        adapter.setOnLongItemClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int positon, String data) {
                Toast.makeText(RecyclerActivity.this,"长按的是："+data,Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void delete(View view){
        adapter.deleteItem();
    }
    public void add(View view){
        adapter.addItem();
    }
}
