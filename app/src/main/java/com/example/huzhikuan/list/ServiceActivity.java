package com.example.huzhikuan.list;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huzhikuan.list.Utils.MyService;

public class ServiceActivity extends AppCompatActivity {
    private ServiceConnection serviceConnection;
    private MyService.MyBinder myBinder;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
//        send
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MyService.MyBinder) service;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,serviceConnection, Service.BIND_AUTO_CREATE);
    }
}
