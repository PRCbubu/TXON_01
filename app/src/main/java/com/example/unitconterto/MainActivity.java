package com.example.unitconterto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation obj = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        TextView heading =  (TextView) findViewById(R.id.appName);
        heading.startAnimation(obj);
    }
}