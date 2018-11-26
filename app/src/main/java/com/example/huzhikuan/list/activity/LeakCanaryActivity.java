package com.example.huzhikuan.list.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huzhikuan.list.R;

import java.io.InputStream;
import java.lang.ref.WeakReference;

/**
 * handler 内存泄漏原因及解决方案
 * 原因 MessageQueue->Message->Handler->Activity
 */
public class LeakCanaryActivity extends AppCompatActivity {
    //方案二弱引用
    private MyHamdler myHamdler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Message message = Message.obtain();
        message.what = 0;
        message.obj = "test";
        myHamdler = new MyHamdler(this);
        myHamdler.sendMessageDelayed(message,1000*300);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //方法一：
//        myHamdler.removeCallbacksAndMessages(null);
    }
    class MyHamdler extends Handler{
        private WeakReference<LeakCanaryActivity> weakReference;
        public MyHamdler(LeakCanaryActivity leakCanaryActivity) {
            weakReference = new WeakReference<>(leakCanaryActivity);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (weakReference.get() != null){
                //update UI
            }
        }
    }
}
