package com.example.huzhikuan.list;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huzhikuan on 2018/4/27.
 */

public class MyView extends View {
    private int width;
    private int height;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
////        MeasureSpec.EXACTLY;
//        setMeasuredDimension(600,600);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, 500, 500, 500, 500);
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
//        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,width,height,paint);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(width/2,height/2,(height-10)/2,paint);
        Rect rect  = new Rect();
        String string = "f";
        paint.getTextBounds(string,0,string.length(),rect);
        float xxx = paint.measureText(string);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        canvas.drawText(string,width/5,height/2+fontMetrics.ascent,paint);
        canvas.drawText(string,width/4,height/2+fontMetrics.descent,paint);
        canvas.drawText(string,width/3,height/2+(Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2,paint);
        canvas.drawText(string,width/2,height/2+rect.height()/2,paint);
        canvas.drawLine(0,height/2,width,height/2,paint);

        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(200,200);
        path.lineTo(300,250);
        path.lineTo(350,400);
//        path.close();
        canvas.drawPath(path,paint);
        canvas.drawTextOnPath("aaaasssssssssssssssssssssssssssssssssssssssssssssaaa",path,0,200,paint);

    }

}
