package com.customscrollview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * 上下滚动scrollview
 */
public class ScrollView1 extends FrameLayout {

    // 最近y轴坐标
    private float mLastMotionY;
    private int mActivePointerId;
    // 滚动阀值,y的move距离超出该值时才视为一个touch的move行为
    private int mTouchSlop;
    // 是否正在滚动
    private boolean mIsBeingDragged;

    public ScrollView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    public ScrollView1(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ScrollView1(Context context) {
        this(context, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        try {
            switch (action & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    // 如果没有子view，则不滚动
                    mIsBeingDragged = getChildCount() != 0;
                    if (!mIsBeingDragged) {
                        return false;
                    }

                    // Remember where the motion event started
                    mLastMotionY = (int) ev.getY();
                    mActivePointerId = ev.getPointerId(0);
                    break;
                }
                case MotionEvent.ACTION_MOVE:
                    final int activePointerIndex = ev
                            .findPointerIndex(mActivePointerId);
                    // 获取滑动过程中y的坐标
                    final float y = ev.getY(activePointerIndex);
                    // y的增量
                    final int deltaY = (int) (mLastMotionY - y);

                    if (!mIsBeingDragged) {
                        // 判断是否为一个move行为
                        if (Math.abs(deltaY) > mTouchSlop) {
                            mIsBeingDragged = true;
                        }
                    }

                    if (mIsBeingDragged) {
                        // Scroll to follow the motion event
                        mLastMotionY = y;

                        // y方向已经滚动过的距离，默认为0，是一个相对距离
                        float oldScrollY = getScrollY();
                        // 需要滚动到的位置，原有的距离（oldScrollY）加上增量（deltaY）
                        float scrollY = oldScrollY + deltaY;

                        // Clamp values if at the limits and record
                        final int top = 0;
                        final int bottom = getScrollRangeY();
                        // 防止滚动超出边界
                        if (scrollY > bottom) {
                            scrollY = bottom;
                        } else if (scrollY < top) {
                            scrollY = top;
                        }

                        // 替换了系统ScrollView的overScrollBy方法,
                        // 即不考虑overScroll情况直接scrollTo滚动了
//                    scrollTo((int) (scrollY), getScrollY());
                        scrollTo(getScrollX(), (int) (scrollY));
                    }
                    break;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取y轴向最大滚动范围
     *
     * @return
     */
    private int getScrollRangeY() {
        int scrollRange = 0;
        if (getChildCount() > 0) {
            // scrollview直属的子view只有一个
            View child = getChildAt(0);
            // 子view的高度减掉scrollview的高度
            scrollRange = Math.max(0, child.getHeight() - (getHeight() - getPaddingTop() - getPaddingBottom()));
        }
        return scrollRange;
    }
}
