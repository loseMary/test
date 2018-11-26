package com.example.huzhikuan.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener{
    private RaderMap raderMap;
    private Timer timer;
    private TimerTask timerTask;
    private Button people,scrollView,newActivity,touch,animation,portrait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MainActivity","=================onCreate");
        people = (Button) findViewById(R.id.people);
        scrollView = (Button) findViewById(R.id.scrollView);
        newActivity = (Button) findViewById(R.id.newActivity);
        touch = (Button) findViewById(R.id.touch);
        animation = (Button) findViewById(R.id.animation);
        portrait = (Button) findViewById(R.id.portrait);
        people.setOnClickListener(this);
        scrollView.setOnClickListener(this);
        newActivity.setOnClickListener(this);
        touch.setOnClickListener(this);
        animation.setOnClickListener(this);
        portrait.setOnClickListener(this);
        raderMap = (RaderMap) findViewById(R.id.raderMap);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                float[] array = new float[5];
                for (int i = 0 ;i<5;i++){
                    array[i] = (float) (Math.random()*90+10);
                }
                raderMap.refreshData(array);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask,2000,4000);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity","=================onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity","=================onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity","=================onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity","=================onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity","=================onStop");
    }

    @Override
    protected void onDestroy() {
        if (timerTask != null){
            timerTask.cancel();
        }
        if (timer != null){
            timer.cancel();
        }
//        raderMap.destroy();
        super.onDestroy();
        Log.e("MainActivity","=================onDestroy");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.people:
                startActivity(new Intent(MainActivity.this,PeopleActivity.class));
                break;
            case R.id.scrollView:
                startActivity(new Intent(MainActivity.this,ScrollActivity.class));
                break;
            case R.id.newActivity:
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                overridePendingTransition(R.anim.in, R.anim.out);
                break;
            case R.id.touch:
                startActivity(new Intent(MainActivity.this,TouchActivity.class));
                overridePendingTransition(R.anim.in, R.anim.out);
                break;
            case R.id.animation:
                startActivity(new Intent(MainActivity.this,AnimationActivity.class));
                overridePendingTransition(R.anim.in, R.anim.out);
            case R.id.portrait:
                startActivity(new Intent(MainActivity.this,AnimationActivity.class));
                overridePendingTransition(R.anim.in, R.anim.out);
                break;
        }
    }
}
