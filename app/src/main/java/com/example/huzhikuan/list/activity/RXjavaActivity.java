package com.example.huzhikuan.list.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.huzhikuan.list.R;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class RXjavaActivity extends AppCompatActivity {
    public String TAG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
//        Observable from = Observable.from(new ArrayList());
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e(TAG,"call="+Thread.currentThread().getName());
                subscriber.onNext("first1");
                subscriber.onNext("second2");
                subscriber.onCompleted();

            }
        });
        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.e(TAG,"onStart"+Thread.currentThread().getName());

            }

            @Override
            public void onCompleted() {
                Log.e(TAG,"end"+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,s+Thread.currentThread().getName());
            }
        };
        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"Observerend");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"Observer:"+e.toString());
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,"Observer:"+s);
            }
        };
        observable.observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.newThread()).
                subscribe(subscriber);
//        observable.subscribe(
//                new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.e(TAG,"Observer:"+s);
//                    }
//                },
//                new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        Log.e(TAG,"Observer:"+throwable.toString());
//                    }
//                },
//                new Action0() {
//
//                    @Override
//                    public void call() {
//                        Log.e(TAG,"completed:");
//                    }
//                });


    }


}
