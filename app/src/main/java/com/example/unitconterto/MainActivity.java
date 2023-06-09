package com.example.unitconterto;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    GridView menuItems;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation obj = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        TextView heading =  (TextView) findViewById(R.id.appName);
        heading.startAnimation(obj);

        menuItems = findViewById(R.id.options);
        ArrayList<MenuItems> menuItemsArrayList = new ArrayList<MenuItems>();

        menuItemsArrayList.add(new MenuItems("Temperature", R.drawable.thermometer));
        menuItemsArrayList.add(new MenuItems("Mass", R.drawable.dumble));
        menuItemsArrayList.add(new MenuItems("Distance", R.drawable.road));
        menuItemsArrayList.add(new MenuItems("Time", R.drawable.clock));
        menuItemsArrayList.add(new MenuItems("Currency", R.drawable.money));
        menuItemsArrayList.add(new MenuItems("Force", R.drawable.energy));

        AdapterClass adapter = new AdapterClass(this, menuItemsArrayList);
        menuItems.setAdapter(adapter);

        menuItems.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(menuItemsArrayList.get(i).getOptionName() == "Temperature")
                {
                    Intent j = new Intent(getApplicationContext(), temperature_converter.class);
                    startActivity(j);
                }
                else if(menuItemsArrayList.get(i).getOptionName() == "Mass")
                {
                    Intent j = new Intent(getApplicationContext(), mass_converter.class);
                    startActivity(j);
                }
                else if(menuItemsArrayList.get(i).getOptionName() == "Distance")
                {
                    Intent j = new Intent(getApplicationContext(), distance_converter.class);
                    startActivity(j);
                }
                else if(menuItemsArrayList.get(i).getOptionName() == "Time")
                {
                    Intent j = new Intent(getApplicationContext(), time_converter.class);
                    startActivity(j);
                }
                else if(menuItemsArrayList.get(i).getOptionName() == "Currency")
                {
                    Intent j = new Intent(getApplicationContext(), currency_conversion.class);
                    startActivity(j);
                }
                else if(menuItemsArrayList.get(i).getOptionName() == "Force")
                {
                    Intent j = new Intent(getApplicationContext(), force_converter.class);
                    startActivity(j);
                }
            }
        });

    }
}