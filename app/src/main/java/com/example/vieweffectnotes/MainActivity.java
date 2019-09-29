package com.example.vieweffectnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.vieweffectnotes.view.AnimationView;
import com.example.vieweffectnotes.view.AnimationViewPlus;
import com.example.vieweffectnotes.view.MyTextView;
import com.example.vieweffectnotes.view.NormalView;
import com.example.vieweffectnotes.view.PaintView;

import static android.view.View.LAYER_TYPE_SOFTWARE;


public class MainActivity extends AppCompatActivity {
    private RelativeLayout layout;
    private RelativeLayout.LayoutParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout)findViewById(R.id.layout);
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        NormalView normalView = new NormalView(this);
        layout.addView(normalView);


        params.setMargins(500,0,0,0);
        PaintView paintView = new PaintView(this);
        layout.addView(paintView,params);

        MyTextView myTextView = new MyTextView(this);
        layout.addView(myTextView,params);




        //translation,rotation,scale
        paintView.animate().translationY(300).setDuration(2000);

        final AnimationView view = new AnimationView(this);
        layout.addView(view);

        //速度设置器
        Interpolator interpolator = new AnticipateOvershootInterpolator();//开始前回拉，最后超过一些然后回弹
        interpolator = new AccelerateDecelerateInterpolator();//先加速再减速
        interpolator = new LinearInterpolator();//匀速
        interpolator = new AccelerateInterpolator();//一直在加速，直到动画结束的一瞬间，直接停止
        interpolator = new DecelerateInterpolator();//始的时候是最高速度，然后在动画过程中逐渐减速，直到动画结束的时候恰好减速到 0
        interpolator = new AnticipateInterpolator();//先回拉一下再进行正常动画轨迹。效果看起来有点像投掷物体或跳跃等动作前的蓄力。
        interpolator = new OvershootInterpolator();//动画会超过目标值一些，然后再弹回来
        interpolator = new BounceInterpolator();//在目标值处弹跳。有点像玻璃球掉在地板上的效果
        interpolator = new CycleInterpolator(2f);//动画可以不到终点就结束，也可以到达终点后回弹，回弹的次数由曲线的周期决定，曲线的周期由 CycleInterpolator() 构造方法的参数决定。
        //用 PathInterpolator 可以定制出任何速度模型。定制的方式是使用一个 Path 对象来绘制出你要的动画完成度 /时间完成度曲线。

        /*
         监听器
         还有更新监听和暂停监听
        */
       final Animator.AnimatorListener listener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.animate().translationY(300).setDuration(2000).setInterpolator(new CycleInterpolator(2f));
                //btn.clearAnimation();
                //                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btn.getLayoutParams();
                //                params.leftMargin = params.leftMargin - 30;
                //                btn.setLayoutParams(params);
                //                btn.bringToFront();
                //当我们给view做动画的时候，平移到某一个位置，却发现响应不了点击事件，因为view的真实位置还在原来的地方
            }

            @Override
            public void onAnimationCancel(Animator animation) {

                //end也会调用
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        //自定义变量要手动添加getter和setter方法
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view, "angle", 0, 200);
        animator.setDuration(3000);
        animator.setInterpolator(new AnticipateInterpolator());
        animator.addListener(listener);
        animator.start();
       // view.animate().translationY(300).setDuration(2000).setInterpolator(interpolator).setListener(listener);

        AnimationViewPlus viewPlus = new AnimationViewPlus(this);
        layout.addView(viewPlus);
        ObjectAnimator objectAnimator = ObjectAnimator.ofArgb(viewPlus, "color", 0xffff0000, 0xff00ff00);
        objectAnimator.setDuration(5000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.start();

        viewPlus.animate().scaleX(0.5f).scaleY(0.5f).alpha(0.5f).setDuration(5000);//同时动画
        //同时动画
//        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 1);
//        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 1);
//        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 1);
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3)
//        animator.start();

        //依次执行动画
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(...);
//        animator1.setInterpolator(new LinearInterpolator());
//        ObjectAnimator animator2 = ObjectAnimator.ofInt(...);
//        animator2.setInterpolator(new DecelerateInterpolator());
//
//        AnimatorSet animatorSet = new AnimatorSet();
//// 两个动画依次执行
//        animatorSet.playSequentially(animator1, animator2);
//        animatorSet.start();

        // 两个动画同时执行
//        animatorSet.playTogether(animator1, animator2);
//        animatorSet.start();

        // 使用 AnimatorSet.play(animatorA).with/before/after(animatorB)
// 的方式来精确配置各个 Animator 之间的关系
//        animatorSet.play(animator1).with(animator2);
//        animatorSet.play(animator1).before(animator2);
//        animatorSet.play(animator1).after(animator2);
//        animatorSet.start();

        //让进度增加到 100% 后再「反弹」回来。
//        // 在 0% 处开始
//        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
//// 时间经过 50% 的时候，动画完成度 100%
//        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);
//// 时间见过 100% 的时候，动画完成度倒退到 80%，即反弹 20%
//        Keyframe keyframe3 = Keyframe.ofFloat(1, 80);
//        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);
//
//        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder);
//        animator.start();

        //关闭硬件加速
        view.setLayerType(LAYER_TYPE_SOFTWARE, null);


    }
}
