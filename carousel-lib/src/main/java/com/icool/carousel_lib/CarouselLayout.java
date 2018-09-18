package com.icool.carousel_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author zhzy
 * created on 2018/9/17.
 */
public class CarouselLayout extends ViewGroup {
    private static final String TAG = "CarouselLayout";

    //边距
    private int mGapSpacing;
    //角度e
    private float mAngle;
    //速度
    private int mSpeed;
    //滚动辅助类
    private Scroller mScroller;

    private LinearLayout mContainer;
    private RecyclerView mRv1;
    private RecyclerView mRv2;
    private RecyclerView mRv3;
    private int mDiagonalLine;


    public CarouselLayout(Context context) {
        this(context, null);
    }

    public CarouselLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CarouselLayout);
        mGapSpacing = array.getDimensionPixelSize(R.styleable.CarouselLayout_carousel_spacing, 20);
        mAngle = array.getFloat(R.styleable.CarouselLayout_carousel_angle, -30);
        mSpeed = array.getInt(R.styleable.CarouselLayout_carousel_speed, 1);
        array.recycle();
        mScroller = new Scroller(getContext());
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
        mDiagonalLine = (int) Math.sqrt(getMeasuredWidth() * getMeasuredWidth() + getMeasuredHeight() * getMeasuredHeight());
        ViewGroup.LayoutParams params = mContainer.getLayoutParams();
        params.width = mDiagonalLine;
        params.height = mDiagonalLine;
        mContainer.setLayoutParams(params);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = -(mDiagonalLine - getMeasuredWidth()) / 2;
        int top = -(mDiagonalLine - getMeasuredHeight()) / 2;
        int right = left + mDiagonalLine;
        int bottom = top + mDiagonalLine;
        mContainer.layout(left, top, right, bottom);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        mRv1.scrollBy(mSpeed, 0);
        mRv2.scrollBy(-mSpeed, 0);
        mRv3.scrollBy(mSpeed, 0);
        if (mScroller.isFinished()) {
            Log.i(TAG, "Scroller finished");
            start();
        }
        invalidate();
    }

    public void init() {
        mContainer = new LinearLayout(getContext());
        mContainer.setOrientation(LinearLayout.VERTICAL);
        addView(mContainer, generateDefaultLayoutParams());
        mRv1 = new CarouselRecyclerView(getContext());
        mRv2 = new CarouselRecyclerView(getContext());
        mRv3 = new CarouselRecyclerView(getContext());
        mContainer.addView(mRv1);
        mContainer.addView(mRv2);
        mContainer.addView(mRv3);
        setSpacing();


        mRv1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRv2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        mRv3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        mContainer.setRotation(mAngle);
    }

    private void setSpacing() {
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        lp1.topMargin = mGapSpacing;
        mRv1.setLayoutParams(lp1);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        lp2.topMargin = mGapSpacing;
        mRv2.setLayoutParams(lp2);

        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
        lp3.topMargin = mGapSpacing;
        lp3.bottomMargin = mGapSpacing;
        mRv3.setLayoutParams(lp3);
    }


    public void setAdapter(RecyclerView.Adapter adapter1, RecyclerView.Adapter adapter2, RecyclerView.Adapter adapter3) {
        mRv1.setAdapter(adapter1);
        mRv2.setAdapter(adapter2);
        mRv3.setAdapter(adapter3);
    }

    /**
     * start
     */
    public void start() {
        mScroller.startScroll(0, 0, 100, 100, 1000);
    }

    /**
     * pause move
     */
    public void pause() {
        mScroller.forceFinished(true);
    }

    /**
     * move speed
     * recommend 1~3
     * the bigger the fast
     *
     * @param speed speed
     */
    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    /**
     * set gap spacing
     * recommend this value equals recyclerView's item space
     *
     * @param spacing space
     */
    public void setGapSpacing(int spacing) {
        mGapSpacing = spacing;
        setSpacing();
        requestLayout();
    }

    /**
     * set angel
     * recommend -20~-50
     *
     * @param angle angel
     */
    public void setAngle(int angle) {
        mAngle = angle;
        mContainer.setRotation(mAngle);
    }

}
