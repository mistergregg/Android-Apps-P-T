package com.gbreed.parseserver;

import com.parse.Parse;
import android.app.Application;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class StarterApplication extends Application
{
    @Override public void onCreate()
    {
        super.onCreate();
        Parse.initialize(
                new Parse.Configuration.Builder(this)
                .applicationId("d5f424686d10beb43387e2951086cced0c6c8042")
                .clientKey("371cfb4d853bd4f5f0ebc9e64d1b101419407d08")
                .server("http://18.224.25.46:80/parse/")
                .build()
        );

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}