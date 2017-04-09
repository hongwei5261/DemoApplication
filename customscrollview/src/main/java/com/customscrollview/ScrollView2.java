package com.customscrollview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class ScrollView2 extends FrameLayout {

    private float mLastMotionY;
    private int mActivePointerId;
    // 滚动阀值,x的move距离超出该值时才视为一个touch的move行为
    private int mTouchSlop;
    private boolean mIsBeingDragged;

    ////////////////////////	Scroller-Fling	///////////////////////////////
    private static final int INVALID_POINTER = -1;
    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    private int mMinimumVelocity;
    private int mMaximumVelocity;

    public ScrollView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initScrollView();
    }

    public ScrollView2(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ScrollView2(Context context) {
        this(context, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        initVelocityTrackerIfNotExists();
        mVelocityTracker.addMovement(ev);

        final int action = ev.getAction();

        try {
            switch (action & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    mIsBeingDragged = getChildCount() != 0;
                    if (!mIsBeingDragged) {
                        return false;
                    }

            /*
             * If being flinged and user touches, stop the fling. isFinished
             * will be false if being flinged.
             */
                    if (!mScroller.isFinished()) {
                        mScroller.abortAnimation();
                    }

                    // Remember where the motion event started
                    mLastMotionY = (int) ev.getY();
                    mActivePointerId = ev.getPointerId(0);
                    break;
                }
                case MotionEvent.ACTION_MOVE:
                    final int activePointerIndex = ev
                            .findPointerIndex(mActivePointerId);
                    final float y = ev.getY(activePointerIndex);
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

                        float oldScrollY = getScrollY();
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

                        // 替换了ScrollView的overScrollBy方法,
                        // 即不考虑overScroll情况直接scrollTo滚动了
                        scrollTo(getScrollX(), (int) (scrollY));
                    }
                    break;
                ////////////////////////	Scroller-Fling	///////////////////////////////
                case MotionEvent.ACTION_UP:
                    if (mIsBeingDragged) {
                        final VelocityTracker velocityTracker = mVelocityTracker;
                        velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                        int initialVelocity = (int) velocityTracker
                                .getYVelocity(mActivePointerId);

                        if (getChildCount() > 0) {
                            // 速度超过某个阀值时才视为fling
                            if ((Math.abs(initialVelocity) > mMinimumVelocity)) {
                                fling(-initialVelocity);
                            }
                        }

                        mActivePointerId = INVALID_POINTER;
                        endDrag();
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
            View child = getChildAt(0);
            scrollRange = Math.max(0, child.getHeight() - (getHeight() - getPaddingTop() - getPaddingBottom()));
        }
        return scrollRange;
    }


    ////////////////////////	Scroller-Fling	///////////////////////////////

    private void endDrag() {
        mIsBeingDragged = false;

        recycleVelocityTracker();
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * Fling the scroll view
     *
     * @param velocityY The initial velocitX in the X direction. Positive numbers mean
     *                  that the finger/cursor is moving down the screen, which means
     *                  we want to scroll towards the top.
     */
    public void fling(int velocityY) {
        if (getChildCount() > 0) {
            mScroller.fling(getScrollX(), getScrollY(),
                    0, velocityY,
                    0, 0,
                    0, getScrollRangeY());

            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = mScroller.getCurrX();
            int y = mScroller.getCurrY();

            if (oldX != x || oldY != y) {
                scrollTo(x, y);
            }

            // Keep on drawing until the animation has finished.
            invalidate();
            return;
        }
    }

    private void initScrollView() {
        mScroller = new Scroller(getContext());
        final ViewConfiguration configuration = ViewConfiguration
                .get(getContext());
        mTouchSlop = configuration.getScaledPagingTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }
}
