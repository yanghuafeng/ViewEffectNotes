package com.example.vieweffectnotes.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Camera;
import android.graphics.Canvas;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.view.View;

import com.example.vieweffectnotes.R;

/**
 * Created by YHF at 9:17 on 2019-09-27.
 */

public class MyTextView extends View {

    private Paint paint;
    private String text = "萝卜不好吃";
    private Bitmap bitmap;
    private Path path;
    private Context mContext;
    private Matrix matrix = new Matrix();
    private Camera camera = new Camera();

    int left = 800;
    int top = 200;
    int right = 900;
    int bottom = 300;
    float[] pointsSrc = {left, top, right, top, left, bottom, right, bottom};
    float[] pointsDst = {left - 10, top + 50, right + 120, top - 90, left + 20, bottom + 30, right + 20, bottom + 60};

    public MyTextView(Context context){
        super(context);
        mContext = context;
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon1);
        bitmap = Bitmap.createScaledBitmap(bitmap,100,100,false);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawPath(path, paint); // 把 Path 也绘制出来，理解起来更方便
       // canvas.drawTextOnPath("Hello HeCoder", path, 0, 0, paint);//沿path绘制文字，拐角记得用圆角

        //伪粗体
        paint.setFakeBoldText(true);
        //删除线
        paint.setStrikeThruText(true);
        //下划线
        paint.setUnderlineText(true);
        //倾斜
        paint.setTextSkewX(-0.5f);
        //缩放
        paint.setTextScaleX(0.8f);
        //字符间隔
        paint.setLetterSpacing(0.2f);
        paint.setTextSize(50);
        //设置字体
        paint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(text, 700, 150, paint);
       // paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Satisfy-Regular.ttf"));
       // canvas.drawText(text, 100, 450, paint);


        //裁剪
        canvas.save();
        path.addCircle(800,400,50, Path.Direction.CW);
        canvas.clipPath(path);
        //canvas.clipRect(700, 300, 900, 500);
        canvas.drawBitmap(bitmap, 750, 350, paint);
        canvas.restore();

        //旋转
        paint.reset();
        canvas.save();
        canvas.rotate(45, 1000, 100);
        canvas.drawBitmap(bitmap, 1000, 100, paint);
        canvas.restore();

        //放缩
//        canvas.save();
//        canvas.scale(1.3f, 1.3f, x + bitmapWidth / 2, y + bitmapHeight / 2);
//        canvas.drawBitmap(bitmap, x, y, paint);
//        canvas.restore();

        //错切
        paint.reset();
        canvas.save();
        canvas.skew(0, 0.5f);
        canvas.drawBitmap(bitmap, 800, -300, paint);
        canvas.restore();

        //使用 Matrix 来做自定义变换
        paint.reset();
        matrix.reset();
        matrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 800, 200, paint);
        canvas.restore();

        //使用 Camera 来做三维变换
        //Camera.setLocation(x, y, z) 设置虚拟相机的位置。x 和 y 参数一般不会改变，直接填 0 就好。
        canvas.save();
        camera.save(); // 保存 Camera 的状态
        camera.rotateX(50); // 旋转 Camera 的三维空间
        canvas.translate(950, 400); //
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        canvas.translate(-950, -400); //
        camera.restore(); // 恢复 Camera 的状态
        canvas.drawBitmap(bitmap, 900, 350, paint);
        canvas.restore();
    }

}
