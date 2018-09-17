package com.icool.carousel3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.icool.carousel_lib.CarouselLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarouselLayout carouselLayout = findViewById(R.id.carouse);
        carouselLayout.setRotation(-30);
        carouselLayout.startTransfer();
    }
}
