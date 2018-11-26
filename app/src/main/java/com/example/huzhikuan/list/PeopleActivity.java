package com.example.huzhikuan.list;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.huzhikuan.list.view.WaterWallView;

import java.util.HashMap;
import java.util.Map;

public class PeopleActivity extends AppCompatActivity {
    private String[] index = {"发育相对早熟","发育正常","发育相对晚熟","周五","吃大餐"};
    private WaterWallView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        view = (WaterWallView) findViewById(R.id.webView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    view.setData(index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
