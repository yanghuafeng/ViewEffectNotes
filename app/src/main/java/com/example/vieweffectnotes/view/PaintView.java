package com.example.vieweffectnotes.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposeShader;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.graphics.SweepGradient;
import android.view.View;

import com.example.vieweffectnotes.R;

/**
 * Created by YHF at 16:34 on 2019-09-26.
 */

public class PaintView extends View {
    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private Bitmap bitmap;
    private Path path = new Path();
    private Context mContext;

    public PaintView(Context context){
        super(context);
        mContext = context;
        init();
    }

    private void init(){
        paint = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint3.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.FILL); //设置绘制模式：STROKE 线、FILL 填充、FILL_AND_STROKE、同时使用
        paint.setColor(Color.parseColor("#ffffffff")); //设置颜色
        paint.setStrokeWidth(2); //设置线条宽度
        paint.setTextSize(10); //设置文字大小
        paint.setAntiAlias(false); //设置抗锯齿开关
        paint.setStrokeCap(Paint.Cap.ROUND);//设置线头形状 BUTT 平头、ROUND 圆头、SQUARE 方头。默认为 BUTT。
        paint.setStrokeJoin(Paint.Join.ROUND);//设置拐角形状 MITER 尖角、 BEVEL 平角和 ROUND 圆角。默认为 MITER。
        paint.setStrokeMiter(29);//拐角大于多少会保留尖角，否则变成平角
        paint.setDither(true);//抖动
        paint.setFilterBitmap(true);//是否使用双线性过滤来绘制 Bitmap(减少马赛克)

        //paint.reset() 重置  paint.set(Paint src) 复制

        /*线性渐变
        参数：
        x0 y0 x1 y1：渐变的两个端点的位置
        color0 color1 是端点的颜色
        tile：端点范围之外的着色规则，类型是 TileMode 一共有 3 个值可选： CLAMP, MIRROR 和  REPEAT。
        CLAMP会在端点之外延续端点处的颜色；MIRROR 是镜像模式；REPEAT 是重复模式。具体的看一下例子就明白。
        */
        Shader linearGradient = new LinearGradient(0, 0, 100, 100, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);

       /* 辐射渐变
        参数：
        centerX centerY：辐射中心的坐标
        radius：辐射半径
        centerColor：辐射中心的颜色
        edgeColor：辐射边缘的颜色
        tileMode：辐射范围之外的着色模式。
        */
        Shader radialGradient = new RadialGradient(300, 300, 100, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);

        //扫描渐变
        Shader sweepGradient = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"));


        //Bitmap渐变可以用来绘制圆形的图片
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon1);
        bitmap = Bitmap.createScaledBitmap(bitmap,200,200,false);
        Shader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon2);
        bitmap1 = Bitmap.createScaledBitmap(bitmap1,200,200,false);
        Shader bitmapShader1 = new BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        // ComposeShader：结合两个 Shader
        //mode: 混合模式很多
        Shader composeShader = new ComposeShader(bitmapShader, bitmapShader1, PorterDuff.Mode.SRC_OVER);
        paint.setShader(bitmapShader);

        //设置颜色过滤 3种
        ColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0xff0000);
        //PorterDuffColorFilter(int color, PorterDuff.Mode mode)
        float[] a = new float[20];
        ColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(a);

        paint.setColorFilter(lightingColorFilter);

        /*
        paint.setXfermode(Xfermode xfermode)
        "Xfermode" 其实就是 "Transfer mode"，用 "X" 来代替 "Trans" 是一些美国人喜欢用的简写方式。严谨地讲，
        Xfermode 指的是你要绘制的内容和 Canvas 的目标位置的内容应该怎样结合计算出最终的颜色。
        */




        //设置轮廓

        //拐角变圆
        PathEffect pathEffect = new CornerPathEffect(20);
        PathEffect pathEffect2 = new DiscretePathEffect(20, 5);

        //DiscretePathEffect 随机偏离 DashPathEffect虚线 PathDashPathEffect使用一个path来绘制（用三角形绘制一条线）

        //分别按照两个绘制
        pathEffect = new SumPathEffect(pathEffect, pathEffect2);
        // ComposePathEffect
        //这也是一个组合效果类的 PathEffect 。不过它是先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
        paint2.setPathEffect(pathEffect);


        //在之后的绘制内容下面加一层阴影。方法的参数里， radius 是阴影的模糊范围； dx dy 是阴影的偏移量； shadowColor 是阴影的颜色。
        //如果要清除阴影层，使用 clearShadowLayer() 。
        paint2.setShadowLayer(10, 0, 0, Color.GREEN);
        paint2.setTextSize(50);

        //设置模糊效果
        paint3.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.NORMAL));
        //设置浮雕
        //paint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));




        //
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300, 300, 200, paint);
        //path.addXxx() ——添加子图形addCircle(float x, float y, float radius, Direction dir) 添加圆x, y, radius 这三个参数是圆的基本信息，最后一个参数 dir 是画圆的路径的方向。
        //path.lineTo(float x, float y) / rLineTo(float x, float y) 画直线  r相对坐标
        //path.moveTo(float x, float y) / rMoveTo(float x, float y) 移动到目标位置
        path.arcTo(100, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）false有痕迹
        path.close();//封闭子图形
        canvas.drawPath(path,paint2);
        canvas.drawText("萝卜不好吃", 300, 300, paint2);
        canvas.drawBitmap(bitmap, 100, 300, paint3);
    }

}
