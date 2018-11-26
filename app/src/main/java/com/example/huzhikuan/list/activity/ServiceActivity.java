package com.example.huzhikuan.list.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huzhikuan.list.R;
import com.example.huzhikuan.list.Utils.MyService;

public class ServiceActivity extends AppCompatActivity {
    private ServiceConnection serviceConnection;
    private MyService.MyBinder myBinder;
    private MyService myService;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MyService.MyBinder) service;
                myService = myBinder.getService();
                myService.setCallBack(new MyService.ICallBack() {
                    @Override
                    public void OnDataChange(String data) {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("name","come from service");
                        message.setData(bundle);
                        //回到主线程刷新数据
                        handler.sendMessage(message);

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this,MyService.class);
        intent.putExtra("name","hu");
        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
        startService(intent);
//        myService.stopService(intent);
//        myService.unbindService(serviceConnection);
    }
}
