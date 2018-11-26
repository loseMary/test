package com.example.huzhikuan.list.Utils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by huzhikuan on 2018/6/13.
 */

public class MyService extends Service {
    private ICallBack iCallBack;
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public class MyBinder extends Binder{
        public int getBinder(){
            return 2;
        }
        public MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setCallBack(ICallBack iCallBack){
        this.iCallBack = iCallBack;
    }
    public interface ICallBack{
        void OnDataChange(String data);
    }
}
