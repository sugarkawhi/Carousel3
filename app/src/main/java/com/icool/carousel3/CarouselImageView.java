package com.icool.carousel3;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author zhzy
 * created on 2018/9/18.
 */
public class CarouselImageView extends ImageView {
    public static final float BOOK_COVER_RATIO = 1.36627907f;

    public CarouselImageView(Context context) {
        this(context, null);
    }

    public CarouselImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize;
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                heightSize = 471 / 2;
                break;
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                heightSize = MeasureSpec.getSize(heightMeasureSpec);
                break;
            default:
                heightSize = 471;
                break;
        }
        int widthSize = (int) (heightSize / BOOK_COVER_RATIO);
        setMeasuredDimension(widthSize, heightSize);
    }
}
