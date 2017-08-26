package com.dangyao.parallaxlistviewlib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by dangyao on 2017/8/25.
 */

public class PallaraxListView extends ListView {
    private ImageView headerImage;
    private int maxHeight;
    private int originalHeight;

    public PallaraxListView(Context context) {
        super(context);
    }

    public PallaraxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PallaraxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /***
     * 1. 在顶部 向下滑动
     * 2. 在底部, 向上滑动
     *
     * @param deltaX
     * @param deltaY         在底部向上滑动  +  在顶部向下滑动 -
     * @param scrollX
     * @param scrollY        滑动的超过的距离
     * @param scrollRangeX
     * @param scrollRangeY   滑动的超过的范围
     * @param maxOverScrollX
     * @param maxOverScrollY 滑动超过的最大值
     * @param isTouchEvent   true 表示是手指拖动向下滑动  false 表示 是惯性滑动
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        System.out.println("overScrollBy deltaY = " + deltaY + "::scrollY = " + scrollY
                + "::scrollRangeY = " + scrollRangeY + "::maxOverScrollY = " + maxOverScrollY +
                ":;isTouchEvent " + isTouchEvent
        );
        //手指 &向下滑动
        if (isTouchEvent && deltaY < 0) {
            ViewGroup.LayoutParams layoutParams = headerImage.getLayoutParams();
            int oldHeight = layoutParams.height;
            int height = oldHeight + Math.abs(deltaY/2);

            layoutParams.height = layoutParams.height >= maxHeight ? maxHeight : height;
            // 重新测量 重新绘制
            headerImage.requestLayout();
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    //处理触摸事件,当手指抬起的时候 ,恢复
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:// 手指抬起恢复为原来的高度
//                headerImage.setMaxHeight();
//                ObjectAnimator
                //值动画
                // 开始高度 --->目标高度
                ViewGroup.LayoutParams layoutParams = headerImage.getLayoutParams();
                int startHeight = layoutParams.height;
                int targetHeight = originalHeight;//目标高度
                ValueAnimator animator = ValueAnimator.ofFloat(startHeight, targetHeight);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float height = (float) animation.getAnimatedValue();
                        ViewGroup.LayoutParams lp = headerImage.getLayoutParams();
                        lp.height = (int) height;

                        //重新测量 重新绘制
                        headerImage.requestLayout();

                    }
                });
                //插值器
//                animator.setInterpolator(new BounceInterpolator());//
                animator.setInterpolator(new OvershootInterpolator(5));
                animator.setDuration(300);
                animator.start();
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setHeaderImage(ImageView headerImage) {
        this.headerImage = headerImage;

        //获取图片的真实高度
        maxHeight = headerImage.getDrawable().getIntrinsicHeight();
        //图片原来的高度
        originalHeight = headerImage.getMeasuredHeight();
    }

}
