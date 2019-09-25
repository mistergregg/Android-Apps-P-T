package com.gbreed.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.gbreed.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>();

        friends.add("Greg");
        friends.add("John");
        friends.add("Tim");

        sharedPreferences.edit().putString("friends", gson.toJson(friends)).apply();

        Log.i("friends", friends.toString());

        Type type = new TypeToken<ArrayList<String>>(){}.getType();

        ArrayList<String> newFriends = gson.fromJson(sharedPreferences.getString("friends", gson.toJson(new ArrayList<String>())), type);

        Log.i("New Friends", newFriends.toString());
    }
        //sharedPreferences.edit().putString("username","greg").apply();

        //String username = sharedPreferences.getString("username","");

        //Log.i("This is the username", username);
}

