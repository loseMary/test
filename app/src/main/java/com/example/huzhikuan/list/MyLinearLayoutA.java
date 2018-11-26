package com.example.huzhikuan.list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by huzhikuan on 2018/5/10.
 */

public class MyLinearLayoutA extends LinearLayout {
    private String tag = "MyLinearLayoutA";
    public MyLinearLayoutA(Context context) {
        super(context);
    }

    public MyLinearLayoutA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayoutA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                Log.e(tag,"--onInterceptTouchEvent--A");
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                Log.e(tag,"--onTouchEvent--A");
                break;
        }
        return false;
    }
}
