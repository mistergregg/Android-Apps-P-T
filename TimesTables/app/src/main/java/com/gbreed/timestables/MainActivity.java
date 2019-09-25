package com.gbreed.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timesSeek = findViewById(R.id.seekBar);

        timesSeek.setMax(19);

        final ListView table = findViewById(R.id.listViewTable);

        final ArrayList<Integer> times = new ArrayList<Integer>();

        for(int i = 1; i <21; i++)
            times.add((timesSeek.getProgress() + 1) * i);

        final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, times);

        table.setAdapter(arrayAdapter);

        timesSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                for(int i = 1; i <21; i++)
                    times.set(i-1, ((progress + 1) * i));

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
