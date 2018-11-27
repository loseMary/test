package com.example.huzhikuan.list.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huzhikuan.list.MyTextView;
import com.example.huzhikuan.list.R;

import java.util.Timer;
import java.util.TimerTask;

public class TouchActivity extends AppCompatActivity {
    private LinearLayout Alayout,Blayout,Clayout;
    private MyTextView DTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        Alayout = (LinearLayout) findViewById(R.id.Alayout);
        Blayout = (LinearLayout) findViewById(R.id.Blayout);
        Clayout = (LinearLayout) findViewById(R.id.Clayout);
        DTextview = (MyTextView) findViewById(R.id.DTextview);
        //CESHIfenzhi

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("00000000000000","--onInterceptTouchEvent--ddd");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("00000000000000","--onTouchEvent--ddd");
                break;
        }

        return true;
    }

}
