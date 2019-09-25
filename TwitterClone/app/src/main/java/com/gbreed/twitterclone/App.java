package com.gbreed.twitterclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("90d7f25c7d4d06623848eba1f671a9c4baadc3dd")
                .server("http://192.168.1.182:80/parse/")
                .build());
    }
}
