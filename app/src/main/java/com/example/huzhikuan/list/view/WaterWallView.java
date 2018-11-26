package com.example.huzhikuan.list.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huzhikuan on 2018/5/24.
 */

public class WaterWallView extends View {
    private int width,height;
    private int padding = 20;
    private int startx,starty,endx,endy;
    private int cell = 10;
    private int heightBottom = 60;//底部文字所占高度
    private Paint paint;
    private String[] index = {"发育相对早熟","发育正常","发育相对晚熟"};
    private int count = 3;
    private int widthRec,heightRec;//长方形宽高
    private int cellRec ;//长方形距离左边分界线的X距离
    private int currentX;//当前矩形所在区域的x坐标
    public WaterWallView(Context context) {
        super(context);
    }

    public WaterWallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterWallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(){
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);

        startx = 0;
        starty = height - heightBottom;
        endx = width;
        endy = height - heightBottom;
        count = index.length;
        widthRec = width/index.length/3*2;//三分之二作为长方形的宽
        heightRec = (height- heightBottom)/(index.length+1);//获取长方形高度
        cellRec = widthRec/4;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w-2*padding;
        height = h-2*padding;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(padding,padding);
//        Rect rect = new Rect(0,0,width,height);
//        canvas.drawRect(rect,paint);
        canvas.drawLine(startx,starty,endx,endy,paint);
        for (int i=0;i<=count;i++){
            canvas.drawLine(width/count*i,starty,width/count*i,endy+cell,paint);

        }
        for (int i=0;i<count;i++){
            Rect rect = new Rect();
            paint.getTextBounds(index[i],0,index[i].length(),rect);
            Path path = new Path();
            path.moveTo(width/count*i,height-heightBottom/2 + rect.height()/2);
            path.lineTo(width/count*(i+1),height-heightBottom/2 + rect.height()/2);
            canvas.drawTextOnPath(index[i],path,0,index[i].length(),paint);
        }

        for (int i=0;i<count;i++){
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL);
            currentX = width/count*i;
            int x1 = cellRec + currentX;
            int y1 = starty-heightRec*(i+1);
            int x2 = x1+widthRec;
            int y2 = y1+heightRec;
            canvas.drawRect(x1,y1,x2,y2,p);
            if (i !=0){
                canvas.drawLine(x1,y1+heightRec,x1-2*cellRec,y1+heightRec,p);
            }
        }
        paint.setTextSize(50);
        canvas.drawText("↓",width-cellRec-widthRec/2,heightRec/2,paint);

    }

    public void setData(String[] index){
        this.index = index;
        init();
        postInvalidate();
    }
}
