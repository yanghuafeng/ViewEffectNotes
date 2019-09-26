package com.example.vieweffectnotes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by YHF at 15:29 on 2019-09-26.
 */

public class NormalView extends View {
    private Paint paint;
    public NormalView(Context context){
        super(context);
        init();
    }
    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //绘制颜色。
        canvas.drawColor(Color.parseColor("#88550000"));
        canvas.drawRGB(100, 200, 100);
        canvas.drawARGB(100, 100, 200, 100);
        // 绘制一个圆circle、矩形rect、椭圆oval、线line(s)、点point(s) ,paint.setStrokeCap(Paint.Cap.ROUND)可以设置点的形状
        // 有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 其实这个是用来设置线头的！
        canvas.drawCircle(300, 300, 200, paint);
        // left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
        canvas.drawRoundRect(100, 100, 500, 300, 50, 50, paint);


        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
                8 /* 一共绘制 8 个数（4 个点）*/, paint);
        /*
          drawArc() 是使用一个椭圆来描述弧形的。left, top, right, bottom 描述的是这个弧形所在的椭圆；startAngle 是弧形的起始角度
         （x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
         sweepAngle 是弧形划过的角度；useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形
         */
        canvas.drawArc(200, 100, 800, 500, -110, 100, true, paint); // 绘制扇形
        //画自定义形状
        Path path = new Path();
        //path.addXxx() ——添加子图形addCircle(float x, float y, float radius, Direction dir) 添加圆x, y, radius 这三个参数是圆的基本信息，最后一个参数 dir 是画圆的路径的方向。
        //path.lineTo(float x, float y) / rLineTo(float x, float y) 画直线  r相对坐标
        //path.moveTo(float x, float y) / rMoveTo(float x, float y) 移动到目标位置
        path.arcTo(100, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）false有痕迹
        path.close();//封闭子图形
        path.setFillType(Path.FillType.EVEN_ODD);// 设置填充方式 WINDING 是「全填充」，而 EVEN_ODD 是「交叉填充」
        canvas.drawPath(path,paint);
        //canvas.drawBitmap(bitmap, 200, 100, paint);canvas.drawText(text, 200, 100, paint);

    }
}
