# Carousel3
微信读书 本周推送的类似传送带View的具体实现
### 实现效果

![](https://ws1.sinaimg.cn/large/92d4722bly1fvemr1qgvgg20as0j6b2c.jpg)

### 使用

```
  <com.icool.carousel_lib.CarouselLayout
            android:id="@+id/carousel"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:carousel_angle="-30"
            app:carousel_spacing="20dp"
            app:carousel_speed="1" />
           
```

只需要通过设置Adapter就OK了
```
 public void setAdapter(RecyclerView.Adapter adapter1, RecyclerView.Adapter adapter2, RecyclerView.Adapter adapter3) {
        mRv1.setAdapter(adapter1);
        mRv2.setAdapter(adapter2);
        mRv3.setAdapter(adapter3);
    }
```



#### 属性说明

|      属性值      |   说明   | 值|
|:---------------:|:-------:|:--:|
|  carousel_angle | 倾斜角度 | 默认-30°|
|  carousel_spacing |  列表之间的间隙，通常设置为recyclerView的item间距大小一致 |dp|
|  carousel_speed | 速度，值越大传送越快，不小于0 | 默认1|

1. 可在代码中设置间隙 `setGapSpacing`
2. 代码中设置角度 `setAngle`
3. 代码中设置速度 `setSpeed`

### 需求分析

+ 直观有 三条传送带式列表
+ 一个正向移动 两个反向移动
+ 有一个倾斜角度
+ 可以循环展示

### 具体分析

+ 根据样式 可以确定的是需要自定义ViewGroup来实现
+ 结合列表的正向反向移动 可以确定：`RecyclerView` + `LinearLayoutManager` 可以做到
+ 不停滚动借助 `Scroller` 来实现
+ 倾斜角度 可以通过 `setRatation()` 方法来旋转一个角度
+ 循环展示 通过设置 `RecyclerView.Adapter`的 `itemCount` 为 `Inter.MAX_VALUE`

### 具体实现

#### 自定义`CarouselLayout`继承自`ViewGroup`
添加一个子View `LinearLayout`, `setOrientation(LinearLayout.VERTICAL);`
依次添加三个 `RecyclerView`,设置其 `marginTop`为 `gapSpacing`的值

```
    mContainer = new LinearLayout(getContext());
        mContainer.setOrientation(LinearLayout.VERTICAL);
        addView(mContainer, generateDefaultLayoutParams());
        mRv1 = new CarouselRecyclerView(getContext());
        mRv2 = new CarouselRecyclerView(getContext());
        mRv3 = new CarouselRecyclerView(getContext());
        mContainer.addView(mRv1);
        mContainer.addView(mRv2);
        mContainer.addView(mRv3);
        setSpacing();//此方法设置margin,详见代码

        mRv1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRv2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        mRv3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
 
```

#### 旋转一个角度

设置 `LinearLayout` 的rotation 

```
 mContainer.setRotation(mAngle);//旋转角度

```

#### 设置`LinearLayout`的大小来保证切斜后仍可以占满全屏
由于在ViewGroup中最大的距离就是对角线，所以 设置 子View的宽高都为对角线的长度

```
//对角线长度
  mDiagonalLine = (int) Math.sqrt(getMeasuredWidth() * getMeasuredWidth() + getMeasuredHeight() * getMeasuredHeight());
        ViewGroup.LayoutParams params = mContainer.getLayoutParams();
        params.width = mDiagonalLine;
        params.height = mDiagonalLine;
        mContainer.setLayoutParams(params);
        
```

#### 移动起来
借助Scroller类来不断 调用 `computeScroll`方法实现滚动
```
   @Override
    public void computeScroll() {
        super.computeScroll();
        mRv1.scrollBy(mSpeed, 0);//speed 对应移动像素值
        mRv2.scrollBy(-mSpeed, 0);
        mRv3.scrollBy(mSpeed, 0);
        if (mScroller.isFinished()) {
            start();
        }
    }
    
```

#### 其他

1. 因为列表使用`RecyclerView`实现，所以我们手动还可以滑动它。
如果不想手动滑动的话，重写`RecyclerView`的`onTouchEvent`方法, ` return false;`
2. 无限循环
设置Adapter的时候 
```
   @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
  
```
然后在 `onBindViewHolder` 方法取item的时候 进行取余操作
```
 String url = mSources[position % mSources.length];
```

### 后记

这个效果在微信阅读上是WebView实现的，我们的UI直接抄了过来。所以只能用Android代码实现一下。
如果有更好的实现方式，或者需要改进的地方，希望可以一起探讨。





