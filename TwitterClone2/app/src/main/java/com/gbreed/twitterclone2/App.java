package com.gbreed.twitterclone2;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("90d7f25c7d4d06623848eba1f671a9c4baadc3dd")
                .server("http://192.168.1.182:80/parse/")
                .build());

        ParseObject score = new ParseObject("TestObject");
        score.put("myString", "dfgd");
        score.put("myNum", "4334");
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                {
                    Log.i("Saved", "Success!");
                }else
                {
                    Log.i("Saved","Failed");
                    Log.i("E", e.toString());
                }
            }
        });
    }
}
