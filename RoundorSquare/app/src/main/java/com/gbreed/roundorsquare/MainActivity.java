package com.gbreed.roundorsquare;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();
    }

    public void whichOne(View view)
    {
        if(getResources().getConfiguration().isScreenRound())
        {
            Toast.makeText(this, "Screen is Round!", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "Screen is Square!", Toast.LENGTH_SHORT).show();
        }
    }
}
