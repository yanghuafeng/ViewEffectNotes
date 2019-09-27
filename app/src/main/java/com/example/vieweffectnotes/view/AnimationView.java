package com.example.vieweffectnotes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by YHF at 10:46 on 2019-09-27.
 */

public class AnimationView extends View {
    private Paint paint;
    private Path path = new Path();



    private float angle;
    public AnimationView(Context context){
        super(context);
        init();
    }


    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#aa0000"));
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
        invalidate();
    }






    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);


        path.arcTo(50, 50, 300, 300, -90, angle, true); // 强制移动到弧形起点（无痕迹）false有痕迹
        canvas.drawPath(path,paint);

    }
}
