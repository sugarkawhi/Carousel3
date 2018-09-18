package com.icool.carousel3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.icool.carousel_lib.CarouselLayout;

public class MainActivity extends AppCompatActivity {

    public static final String[] RES_1 = new String[]{
            "https://img.xhhread.cn/images/covers/120/20171212160717948317.jpg",
            "https://img.xhhread.cn/images/covers/120/20171114170340861.jpg",
            "https://img.xhhread.cn/images/covers/120/20170908114858367.jpg",
            "https://img.xhhread.cn/images/covers/120/20171129151704679316.jpg",
            "https://img.xhhread.cn/images/covers/20170524190259477.png"
    };
    public static final String[] RES_2 = new String[]{
            "https://img.xhhread.cn/images/covers/120/20180719162856280497.jpg",
            "https://img.xhhread.cn/images/covers/120/20180211151726743903.jpg",
            "https://img.xhhread.cn/images/covers/20161218174211850.png",
            "https://img.xhhread.cn/images/covers/120/20171103182048278.jpg",
            "https://img.xhhread.cn/images/covers/120/20171121104914447.jpg"
    };
    public static final String[] RES_3 = new String[]{
            "https://img.xhhread.cn/images/covers/120/20171102153743559.jpg",
            "https://img.xhhread.cn/images/covers/120/20161116184433248.jpg",
            "https://img.xhhread.cn/images/covers/20170402001402658.png",
            "https://img.xhhread.cn/images/covers/120/20170330215335077.jpg",
            "https://img.xhhread.cn/images/covers/20170321195717153.png"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .fitsSystemWindows(false)
                .transparentStatusBar()
                .init();
        findViewById(R.id.tv_search)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Carousel3Activity.class);
                        startActivity(intent);
                    }
                });

        CarouselLayout carouselLayout = findViewById(R.id.carousel);

        CarouselAdapter adapter1 = new CarouselAdapter(RES_1);
        CarouselAdapter adapter2 = new CarouselAdapter(RES_2);
        CarouselAdapter adapter3 = new CarouselAdapter(RES_3);

        carouselLayout.setAdapter(adapter1, adapter2, adapter3);

        carouselLayout.start();

    }
}
