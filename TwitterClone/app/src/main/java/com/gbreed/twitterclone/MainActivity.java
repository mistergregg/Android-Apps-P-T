package com.gbreed.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null)
        {
            useLoggedIn();
            finish();
        }
    }

    public void loginClick(View view)
    {
        EditText userText = findViewById(R.id.editTextUser);
        EditText passText = findViewById(R.id.editTextPass);

        final String user = userText.getText().toString();
        final String pass = passText.getText().toString();

        if(!user.equals("") && !pass.equals(""))
        {
            ParseUser.logInInBackground(user, pass, new LogInCallback() {
                @Override
                public void done(ParseUser tmpUser, ParseException e) {
                    if(tmpUser != null)
                    {
                        useLoggedIn();
                    }else
                    {
                        ParseUser username = new ParseUser();
                        username.setUsername(user);
                        username.setPassword(pass);

                        username.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e)
                            {
                                if(e == null)
                                {
                                    ArrayList<String> tmpArray = new ArrayList<>();
                                    ParseUser.getCurrentUser().put("follows", tmpArray);

                                    ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e)
                                        {
                                            if(e == null)
                                            {
                                                useLoggedIn();
                                            }else
                                            {
                                                Toast.makeText(MainActivity.this, "Failed to Log In", Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }else
                                {
                                    Toast.makeText(MainActivity.this, "Incorrect User/Pass", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public void useLoggedIn()
    {

        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);

        startActivity(intent);

        finish();
    }
}
