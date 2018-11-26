package com.example.huzhikuan.list.activity;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.huzhikuan.list.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HandlerThreadActivity extends AppCompatActivity implements Handler.Callback{
    private Button button,button2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            try {
                Thread.sleep(10000);
                button2.setText("hhhh");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    //未完成
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();

//        handlerThread.getThreadHandler();
//        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(20);
//        ExecutorService newF = Executors.newCachedThreadPool();
//        ExecutorService ss = Executors.newScheduledThreadPool(23);
//        ExecutorService sss = Executors.newSingleThreadExecutor();
//        Thread thread = new Thread();


        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HandlerThreadActivity.this,"ssssss",Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.e("HandlerThreadActivity",msg.obj.toString());
        return false;
    }
}
