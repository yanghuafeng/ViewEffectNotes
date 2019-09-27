package com.example.vieweffectnotes;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.widget.TextView;


import com.example.vieweffectnotes.view.MyTextView;

/**
 * Created by YHF at 17:04 on 2019-09-27.
 */

public class MytextView extends TextView {
    private Paint paint = new Paint();
    PathEffect pathEffect;
    private int color;
    public MytextView(Context context){
        super(context);
        init();
    }

    public MytextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        //将attrs.xml 文件中的 imageview属性 与 类中的属性str关联
        color = typedArray.getInt(R.styleable.MyTextView_color,Color.GREEN);
        //回收typeArray
        typedArray.recycle();
        init();
    }

    private void init(){
        pathEffect = new CornerPathEffect(20);
        PathEffect pathEffect2 = new DiscretePathEffect(20, 5);

        //DiscretePathEffect 随机偏离 DashPathEffect虚线 PathDashPathEffect使用一个path来绘制（用三角形绘制一条线）

        //分别按照两个绘制
        pathEffect = new SumPathEffect(pathEffect, pathEffect2);
    }

    @Override
    public void onDraw(Canvas canvas){
        paint = getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(pathEffect);
        paint.setShadowLayer(10, 0, 0, color);
        super.onDraw(canvas);
    }

}
