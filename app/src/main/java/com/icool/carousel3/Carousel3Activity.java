package com.icool.carousel3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.icool.carousel_lib.CarouselLayout;

/**
 * @author zhzy
 * created on 2018/9/18.
 */
public class Carousel3Activity extends AppCompatActivity {

    private CarouselLayout carouselLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);
        carouselLayout = findViewById(R.id.carousel);
        CarouselAdapter adapter1 = new CarouselAdapter(MainActivity.RES_1);
        CarouselAdapter adapter2 = new CarouselAdapter(MainActivity.RES_2);
        CarouselAdapter adapter3 = new CarouselAdapter(MainActivity.RES_3);
        carouselLayout.setAdapter(adapter1, adapter2, adapter3);

        init();
    }

    private void init() {
        RadioGroup rg_speed = findViewById(R.id.rg_speed);
        rg_speed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.speed_1:
                        carouselLayout.setSpeed(1);
                        break;
                    case R.id.speed_2:
                        carouselLayout.setSpeed(2);
                        break;
                    case R.id.speed_3:
                        carouselLayout.setSpeed(3);
                        break;
                    case R.id.speed_4:
                        carouselLayout.setSpeed(4);
                        break;
                }
            }
        });
        RadioGroup rg_angle = findViewById(R.id.rg_angle);
        rg_angle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.angle_30:
                        carouselLayout.setAngle(-30);
                        break;
                    case R.id.angle_45:
                        carouselLayout.setAngle(-45);
                        break;
                    case R.id.angle_60:
                        carouselLayout.setAngle(-60);
                        break;
                }
            }
        });

        RadioGroup rg_spacing = findViewById(R.id.rg_spacing);
        rg_spacing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.spacing_20:
                        carouselLayout.setGapSpacing(20);
                        break;
                    case R.id.spacing_40:
                        carouselLayout.setGapSpacing(40);
                        break;
                    case R.id.spacing_60:
                        carouselLayout.setGapSpacing(60);
                        break;
                }
            }
        });

    }
}
