package com.example.vieweffectnotes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by YHF at 16:08 on 2019-09-27.
 */

public class AnimationViewPlus extends View {
    private Paint paint;
    private Path path = new Path();
    private int color = 0xffff0000;

    public AnimationViewPlus(Context context){
        super(context);
        init();
    }


    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }






    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint.setColor(color);
        canvas.drawCircle(100,500,100,paint);

    }
}