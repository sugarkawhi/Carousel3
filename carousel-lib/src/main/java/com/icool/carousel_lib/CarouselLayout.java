package com.icool.carousel_lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * @author zhzy
 * created on 2018/9/17.
 */
public class CarouselLayout extends ViewGroup {

    //边距
    private int mGapSpacing = 30;
    //高度
    private int mHeight;
    //宽度
    private int mWidth;

    private Scroller mScroller;

    private RecyclerView mContainer1;
    private RecyclerView mContainer2;
    private RecyclerView mContainer3;


    public CarouselLayout(Context context) {
        this(context, null);
    }

    public CarouselLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(getContext());
        mContainer1 = new RecyclerView(context);
        mContainer1.setBackgroundColor(Color.RED);
        TextView t1 = new TextView(getContext());
        t1.setText("TEXT - 1");
        mContainer1.addView(t1);
        mContainer2 = new RecyclerView(context);
        mContainer2.setBackgroundColor(Color.GREEN);
        TextView t2 = new TextView(getContext());
        t2.setText("TEXT -2");
        mContainer2.addView(t2);
        mContainer3 = new RecyclerView(context);
        mContainer3.setBackgroundColor(Color.BLUE);
        TextView t3 = new TextView(getContext());
        t3.setText("TEXT -2");
        mContainer3.addView(t3);
        addView(mContainer1);
        addView(mContainer2);
        addView(mContainer3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int sHeight = (mHeight - mGapSpacing * 2) / 3;

        mContainer1.layout(0, 0, mWidth, sHeight);
        mContainer2.layout(0, sHeight + mGapSpacing, mWidth, sHeight * 2 + mGapSpacing);
        mContainer3.layout(0, (sHeight + mGapSpacing) * 2, mWidth, mHeight);

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        mContainer1.scrollBy(3, 0);
        mContainer2.scrollBy(-3, 0);
        mContainer3.scrollBy(3, 0);
    }

    public void setAdapter(RecyclerView.Adapter adapter1, RecyclerView.Adapter adapter2, RecyclerView.Adapter adapter3) {
        mContainer1.setAdapter(adapter1);
        mContainer1.setAdapter(adapter2);
        mContainer1.setAdapter(adapter3);
    }

    public void startTransfer() {
        mScroller.startScroll(0, 0, 1000, 1000, 30000);
    }

}
