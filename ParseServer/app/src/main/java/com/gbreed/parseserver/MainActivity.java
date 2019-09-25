package com.gbreed.parseserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        ParseUser user = new ParseUser();

        user.setUsername("Greg");
        user.setPassword("MyPass");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                {
                    Log.i("Signed Up", "Successful!");
                }
            }
        });
        */

        /*
        ParseUser.logInInBackground("Greg", "MyPass", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null)
                {
                    Log.i("Log In Status", "Successful!");
                }
                else
                {
                    e.printStackTrace();
                }
            }
        });
        */

        if(ParseUser.getCurrentUser() != null)
        {
            Log.i("Signed In", ParseUser.getCurrentUser().getUsername());
        }



        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
