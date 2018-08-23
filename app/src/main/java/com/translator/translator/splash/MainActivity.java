package com.translator.translator.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.translator.translator.R;
import com.translator.translator.TextTranslation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.viewpager);
        SlideAdapter myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);
    }

    public void gotoTextTranslation(View v){
        Intent intent = new Intent(this, TextTranslation.class);
        startActivity(intent);
    }
}
