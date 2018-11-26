package com.example.huzhikuan.list;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzhikuan on 2018/3/28.
 */

public class RaderMap extends View {
    private String TAG = "RaderMap";
    private float width,height;
    private float r;
    private float center_x;
    private float center_y;
    private int count = 5;
    private float angle = (float) (2 * Math.PI / count);//弧度
    private int ceng=3;
    private float[] fromArray = {0,0,100,100,100};//变化后的值
    private float[] toArray = {100,100,100,100,100};//变化后的值
    private float[][] defaultRadar = {{100,100,100,100,100},
                                    {100,100,100,100,100},
                                    {100,100,100,100,100}};//每个多边形的各个点的半径，默认初始值
    private Paint[] paintText = new Paint[5];
    private String[] title = {"WLi","PTi","DELi","ANXi","Pi"};
    private List<RaderMapBean> list = new ArrayList<>();
    public RaderMap(Context context) {
        super(context);
        initView();
    }

    public RaderMap(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RaderMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.e(TAG,"widthon1=="+widthMeasureSpec);
//        Log.e(TAG,"heighton2=="+heightMeasureSpec);
//        setMeasuredDimension(400,400);
//    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
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

    //初始化基本元素
    private void initView() {

        for (int i=0;i<paintText.length;i++){
            Paint paint = new Paint();
            paint.setTextSize(40);
            paint.setColor(Color.GRAY);
            paintText[i] = paint;
        }



    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        refreshLayout(canvas);
        drawText(canvas);

    }
    //绘制雷达图型
    private void refreshLayout(Canvas canvas){
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

        drawRegion(canvas);
    }
    //绘制阴影区域
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
    //计算雷达图上各个点的坐标
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
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    start((float[]) msg.obj);
                    break;
            }
        }
    };
    /**
     * 刷新
     * @param toArray
     */
    public void refreshData(float[] toArray){
        this.toArray = toArray;

        Message message = new Message();
        message.what = 0;
        message.obj = toArray;
        handler.sendMessage(message);

    }
    private int margin = 5;
    private void drawText(Canvas canvas) {
        for (int i=0;i<toArray.length;i++){
            Paint paint = new Paint();
            paint.setTextSize(40);
            paint.setColor(Color.GRAY);
            Rect rectTitle  = new Rect();
            paint.getTextBounds(title[i],0,title[i].length(),rectTitle);
            paint.measureText(title[i]);
            paint.getFontMetrics().ascent=100;
            Rect rectValue  = new Rect();
            String value = String.valueOf((int)toArray[i]);
            paintText[i].getTextBounds(value,0,value.length(),rectValue);
            int xV=0;
            int yV=0;
            int xT=0;
            int yT=0;
            if (i==0){
                paintText[i].setTextAlign(Paint.Align.CENTER);
                xV = (int) list.get(0).getX1();
                yV = (int) list.get(0).getY1() - margin;
                paint.setTextAlign(Paint.Align.CENTER);
                xT = (int) list.get(0).getX1();
                yT = (int) list.get(0).getY1() - margin - rectValue.height() - margin;

            }else if (i==1){
                paintText[i].setTextAlign(Paint.Align.RIGHT);
                xV = (int) list.get(0).getX2();
                yV = (int) list.get(0).getY2()+rectValue.height() + margin;
                paint.setTextAlign(Paint.Align.RIGHT);
                xT = (int) list.get(0).getX2();
                yT = (int) list.get(0).getY2();
            }else if (i==2){
                paintText[i].setTextAlign(Paint.Align.CENTER);
                xV = (int) list.get(0).getX3();
                yV = (int) list.get(0).getY3()+rectValue.height() + margin;
            }else if (i==3){
                paintText[i].setTextAlign(Paint.Align.CENTER);
                xV = (int) list.get(0).getX4();
                yV = (int) list.get(0).getY4() + rectValue.height() + margin;
            }else if (i==4){
                paintText[i].setTextAlign(Paint.Align.LEFT);
                xV = (int) list.get(0).getX5();
                yV = (int) list.get(0).getY5() + rectValue.height() + margin;

            }
            canvas.drawText(value,xV,yV,paintText[i]);
            canvas.drawText(title[i],xT,yT,paint);
        }

    }

    class PointEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            float[] from = (float[]) startValue;
            float[] to = (float[]) endValue;
            float[] current = new float[5];
            for (int i=0;i<from.length;i++){
                current[i] = (from[i]+ fraction * (to[i] - from[i]));
            }
            return current;
        }
    }
    private ValueAnimator animator;
    public void start(float[] toArray) {
        animator = ValueAnimator.ofObject(new PointEvaluator(),
                fromArray, toArray);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                fromArray = (float[]) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }
}
