package com.example.com.demoapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/*Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = msg.arg1;
            mDownloadBar.setCurProgress(progress);
        }
    };

    private void func() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress <= 100) {
                    mHandler.removeMessages(0);
                    Message message = new Message();
                    message.arg1 = progress;
                    message.what = 0;
                    mHandler.sendMessage(message);
                    progress++;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
        <com.example.com.myapplication.DownloadProgressBar
        android:id="@+id/downloadbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    */

/**
 * Created by hongwei on 2017/4/2.
 */

public class DownloadProgressBar extends View implements View.OnClickListener {
    private static final int DEFAULT_MAX_PROGRESS = 100;
    public static final int STATUE_DOWNLOAD = 1;
    public static final int STATUE_DOWNLOADING = 2;
    public static final int STATUE_PAUSE = 3;
    public static final int STATUE_COMPLETE = 4;
    public static final int STATUE_RETRY = 5;
    public static final int STATUE_FAIL = 6;
    private int mStatus;

    private int mMaxProgress;
    private int mCurProgress;
    private int mProgressBgColor;
    private int mProgressFgColor;
    private int mTextBgColor;
    private int mTextFgColor;
    private String mTextString;

    private Paint mProgressPaint;
    private Paint mTextPaint;
    private int mProgressHeight;
    private Rect mTextRect;
    private OnStatusListener mOnStatusListener;

    private int mDefaultProgressWidth = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX, 100, getResources().getDisplayMetrics());
    private int mDefaultProgressHeight = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX, 120, getResources().getDisplayMetrics());

    private int mDefaultTextSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());

    public DownloadProgressBar(Context context) {
        super(context);
        init();
    }

    public DownloadProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DownloadProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mMaxProgress = DEFAULT_MAX_PROGRESS;
        mProgressBgColor = Color.RED;
        mProgressFgColor = Color.BLUE;
        mTextBgColor = Color.RED;
        mTextFgColor = Color.BLUE;

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mDefaultTextSize);
        mTextPaint.setFakeBoldText(true);
        mTextRect = new Rect();
        mStatus = STATUE_DOWNLOAD;
        mTextString = "Download";
        this.setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = mDefaultProgressWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = mDefaultProgressHeight;
        }
        mProgressHeight = height;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        onProgressDraw(canvas, 0, getWidth(), mProgressBgColor);
        onProgressDraw(canvas, 0, getWidth() * mCurProgress / mMaxProgress, mProgressFgColor);

        mTextPaint.getTextBounds(mTextString, 0, mTextString.length(), mTextRect);
        onTextDraw(canvas, (getWidth() - mTextRect.width()) / 2, getWidth() * mCurProgress / mMaxProgress,
                mTextBgColor);
        onTextDraw(canvas, getWidth() * mCurProgress / mMaxProgress, getWidth() / 2 + mTextRect.width() / 2,
                mTextFgColor);
        Log.d("tag", "" + getWidth() + "  " + mTextRect);
    }

    private void onProgressDraw(Canvas canvas, int start, int end, int color) {
        mProgressPaint.setColor(color);
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        //canvas.clipRect(start, 0, end, mProgressHeight);
        canvas.drawRect(start, mProgressHeight, end, 0, mProgressPaint);
        canvas.restore();
    }

    private void onTextDraw(Canvas canvas, int start, int end, int color) {
        mTextPaint.setColor(color);
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(start, 0, end, getMeasuredHeight());
        canvas.drawText(mTextString, (getWidth() - mTextRect.width()) / 2, (getHeight() + mTextRect.height()) / 2, mTextPaint);
        canvas.restore();
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setCurProgress(int curProgress) {
        this.mCurProgress = curProgress;
        if (mCurProgress > 0 && mCurProgress < mMaxProgress) {
            mStatus = STATUE_DOWNLOADING;
            mTextString = new StringBuffer("Downloading:").append(100 * mCurProgress / mMaxProgress).append("%").toString();
        } else if (mCurProgress >= mMaxProgress) {
            mStatus = STATUE_COMPLETE;
            mTextString = "Download complete";
        } else {
            mStatus = STATUE_DOWNLOAD;
            mTextString = "Download";
        }
        invalidate();
    }

    /**
     * @see #STATUE_DOWNLOAD
     * @see #STATUE_DOWNLOADING
     * @see #STATUE_PAUSE
     * @see #STATUE_COMPLETE
     * @see #STATUE_RETRY
     * @see #STATUE_FAIL
     * @param status
     */
    public void setStatue(int status) {
        mStatus = status;
    }

    @Override
    public void onClick(View v) {
        Log.d("tag", "tag");
        if (mOnStatusListener != null) {
            mOnStatusListener.statusChange(0);
        }
    }

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        mOnStatusListener = onStatusListener;
    }

    public interface OnStatusListener{
        void statusChange(int statu);
    }
}
