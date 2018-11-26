package com.example.huzhikuan.list;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzhikuan on 2018/5/8.
 */

public class Radar extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private SurfaceHolder holder;
    // 用于绘图的Canvas
    private Canvas canvas;

    private String TAG = "RaderMap";
    private float width,height;
    private float r;
    private float center_x;
    private float center_y;
    private int count = 5;
    private float angle = (float) (2 * Math.PI / count);//弧度
    private int ceng=3;
    private float[] fromArray = {100,100,100,100,100};//变化后的值
    private float[][] defaultRadar = {{100,100,100,100,100},
            {100,100,100,100,100},
            {100,100,100,100,100}};//每个多边形的各个点的半径，默认初始值
    private List<RaderMapBean> list = new ArrayList<>();
    private boolean isStart = true;
    private Handler handler  = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    start((float[]) msg.obj);
                    break;
                case 1:
//                    invalidate();
                    postInvalidate();
                    break;
            }
        }


    };
    public Radar(Context context) {
        super(context);
        init();
    }

    public Radar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Radar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.e(TAG,"widthon3=="+w);
        Log.e(TAG,"heighton4=="+h);
        Log.e(TAG,"widthon5=="+getMeasuredWidth());
        Log.e(TAG,"heighton6=="+getMeasuredHeight());

        width = w;
        height = h;
        r = Math.min(width,height)/4;
        center_x = width/2;
        center_y = height/2;
        for ( int i = 0; i<defaultRadar.length;i++){
            for (int j=0;j<defaultRadar[i].length;j++){
                defaultRadar[i][j] = (int) (r - r*i/ceng);
            }
        }
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);



    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.e(TAG,"=========================");
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        Log.e(TAG,"=========================");

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void run() {
        while (isStart){
            long start = System.currentTimeMillis();
            try {
//                Thread.sleep(5);
//                int left = (int) list.get(0).getX2();
//                int top = (int) list.get(0).getX2();
//                int right = (int) list.get(0).getX2();
//                int bottom = (int) list.get(0).getX2();
//                Rect rect = new Rect(left,top,right,bottom);
//                canvas = holder.lockCanvas(rect);
                canvas = holder.lockCanvas();
                canvas.drawColor(Color.BLACK);
                drawLayout();
                drawRegion(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (canvas != null){
                    holder.unlockCanvasAndPost(canvas);
                }
                long end = System.currentTimeMillis();
                Log.e(TAG,"time=========="+(end-start));
            }
        }


    }


    private void drawLayout() {
//        canvas = holder.lockCanvas();
        list.clear();
        for (int j=0;j<defaultRadar.length;j++){
            RaderMapBean bean = drawRadars(defaultRadar[j]);
            Paint paint = new Paint();
            if (j == 0){
                paint.setStrokeWidth(2);
                paint.setTextSize(30);
                paint.setColor(Color.GRAY);
                paint.setStyle(Paint.Style.STROKE);
            }else if (j == 1 ){
                paint.setStrokeWidth(2);
                paint.setTextSize(30);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.GRAY);
            }else if (j == 2 ){
                paint.setStrokeWidth(2);
                paint.setTextSize(30);
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.BLACK);
            }
            bean.setPaint(paint);
            list.add(bean);
        }
        for (int i=0;i<list.size();i++){
            Path path = new Path();
            path.moveTo(list.get(i).getX1(),list.get(i).getY1());
            path.lineTo(list.get(i).getX2(),list.get(i).getY2());
            path.lineTo(list.get(i).getX3(),list.get(i).getY3());
            path.lineTo(list.get(i).getX4(),list.get(i).getY4());
            path.lineTo(list.get(i).getX5(),list.get(i).getY5());
            path.close();
            canvas.drawPath(path,list.get(i).getPaint());
        }


//        holder.unlockCanvasAndPost(canvas);
    }
    private void drawRegion(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.leida));
        paint.setStyle(Paint.Style.FILL);
        float[] array = new float[5];
        for (int i=0;i<fromArray.length;i++){
            array[i] = (int) (r/100*fromArray[i]);
        }
        RaderMapBean bean = drawRadars(array);
        Path path = new Path();
        path.moveTo(bean.getX1(),bean.getY1());
        path.lineTo(bean.getX2(),bean.getY2());
        path.lineTo(bean.getX3(),bean.getY3());
        path.lineTo(bean.getX4(),bean.getY4());
        path.lineTo(bean.getX5(),bean.getY5());
        path.close();
        canvas.drawPath(path,paint);
    }

    private RaderMapBean drawRadars(float[] radius) {
        RaderMapBean bean  = new RaderMapBean();
        for (int i=0;i<count;i++){
            float x = 0;
            float y = 0;
            if (i==0){
                x = center_x;
                y = (center_y-radius[i]);
            }else {
                if(angle * i>0 && angle * i <=(Math.PI/2)){
                    x = (float) (center_x - radius[i] * Math.sin(angle * i));
                    y = (float) (center_y - radius[i] * Math.cos(angle * i));
                }else if (angle * i>(Math.PI/2) && angle * i <= Math.PI){
                    x = (float) (center_x - radius[i] * Math.cos(angle * i-Math.PI/2));
                    y = (float) (center_y + radius[i] * Math.sin(angle * i-Math.PI/2));
                }else if (angle * i>Math.PI && angle * i <=(Math.PI)*3/2){
                    x = (float) (center_x + radius[i] * Math.sin(angle * i-Math.PI));
                    y = (float) (center_y + radius[i] * Math.cos(angle * i-Math.PI));
                }else if (angle * i>(Math.PI)*3/2  && angle * i <=(Math.PI*2)){
                    x = (float) (center_x + radius[i] * Math.cos(angle * i-Math.PI*3/2));
                    y = (float) (center_y - radius[i] * Math.sin(angle * i-Math.PI*3/2));
                }
            }
            if (i==0){
                bean.setX1(x);
                bean.setY1(y);
            }else if (i == 1){
                bean.setX2(x);
                bean.setY2(y);
            }else if (i == 2){
                bean.setX3(x);
                bean.setY3(y);
            }else if (i == 3){
                bean.setX4(x);
                bean.setY4(y);
            }else if (i == 4){
                bean.setX5(x);
                bean.setY5(y);
            }
        }
        return bean;
    }

    /**
     * 刷新
     * @param toArray
     */
    private ValueAnimator animator;
    public void refreshData(float[] toArray){

        Message message = new Message();
        message.what = 0;
        message.obj = toArray;
        handler.sendMessage(message);


    }
    private int countaa=0;
    class PointEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            float[] from = (float[]) startValue;
            float[] to = (float[]) endValue;
            float[] current = new float[5];
            for (int i=0;i<from.length;i++){
                current[i] = (from[i]+ fraction * (to[i] - from[i]));
            }
            countaa++;
            if (fraction == 1.0){
                Log.e("radermap","aaaa111111110==="+countaa);
                countaa=0;
            }
            return current;
        }
    }
    public void start(float[] toArray) {
        animator = ValueAnimator.ofObject(new PointEvaluator(),
                fromArray, toArray);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                fromArray = (float[]) animation.getAnimatedValue();
            }
        });
        animator.start();
    }
    public void  destroy(){
        isStart = false;
    }
}
