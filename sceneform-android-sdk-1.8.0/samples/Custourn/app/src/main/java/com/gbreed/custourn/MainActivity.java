package com.gbreed.custourn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(), FriendsList.class);
        startActivity(intent);
        finish();
    }

    public void signupClick(View view)
    {

    }
}
