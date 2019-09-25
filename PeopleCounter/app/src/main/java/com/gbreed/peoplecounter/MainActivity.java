package com.gbreed.peoplecounter;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends WearableActivity {

    private TextView countText;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countText = findViewById(R.id.textCount);

        // Enables Always-on
        setAmbientEnabled();

        count = 0;
        countText.setText(String.format(Locale.getDefault(),"%d", count));
    }

    public void resetCount(View view)
    {
        count = 0;
        countText.setText(String.format(Locale.getDefault(),"%d", count));
    }

    public void addCount(View view)
    {
        count++;
        countText.setText(String.format(Locale.getDefault(),"%d", count));
    }
}
