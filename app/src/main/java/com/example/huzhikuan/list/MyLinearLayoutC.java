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

public class MyLinearLayoutC extends LinearLayout {
    private String tag = "MyLinearLayoutC";
    public MyLinearLayoutC(Context context) {
        super(context);
    }

    public MyLinearLayoutC(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayoutC(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
//                getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }
//        return super.dispatchTouchEvent(event);
//    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:

                Log.e(tag,"--onInterceptTouchEvent--C");
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                Log.e(tag,"--onTouchEvent--C");
                break;
        }
        return false;
    }
}
