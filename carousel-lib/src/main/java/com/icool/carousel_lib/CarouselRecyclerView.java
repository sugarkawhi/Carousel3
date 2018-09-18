package com.icool.carousel_lib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author zhzy
 * created on 2018/9/18.
 */
public class CarouselRecyclerView extends RecyclerView {
    public CarouselRecyclerView(Context context) {
        super(context);
    }

    public CarouselRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CarouselRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
}
