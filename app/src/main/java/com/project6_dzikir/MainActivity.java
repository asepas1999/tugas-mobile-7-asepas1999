package com.project6_dzikir;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private DzikirPagerAdapter dzikirPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        dzikirPagerAdapter = new DzikirPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(dzikirPagerAdapter);
    }
}
