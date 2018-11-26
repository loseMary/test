package com.example.huzhikuan.list.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import com.example.huzhikuan.list.R;

public class AnimationActivity extends AppCompatActivity {
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        start = (Button) findViewById(R.id.start);
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,300);
//        valueAnimator.setDuration(3000);
//        valueAnimator.setRepeatCount(0);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                int x = (int) valueAnimator.getAnimatedValue();
//                start.getLayoutParams().width = x;
//                start.requestLayout();
//            }
//        });
//        valueAnimator.start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(start,"rotation",180,0,180);
        objectAnimator.setDuration(3000);
        objectAnimator.setRepeatCount(1);
        objectAnimator.start();
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Log.e("tttt","start=========onAnimationStart");
            }
            public void onAnimationEnd(Animator animator) {
                Log.e("tttt","start=========onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                Log.e("tttt","start=========onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                Log.e("tttt","start=========onAnimationRepeat");
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();

    }
}
