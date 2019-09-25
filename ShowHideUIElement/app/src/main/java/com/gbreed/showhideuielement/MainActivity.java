package com.gbreed.showhideuielement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view)
    {
        TextView textView = findViewById(R.id.helloText);

        textView.setVisibility(View.VISIBLE);
    }

    public void hide(View view)
    {
        TextView textView = findViewById(R.id.helloText);

        textView.setVisibility(View.INVISIBLE);
    }
}
