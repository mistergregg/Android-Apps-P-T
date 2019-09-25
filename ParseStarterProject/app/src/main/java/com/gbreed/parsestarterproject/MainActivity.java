package com.gbreed.parsestarterproject;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener
{
    boolean signUp;
    Button signUpButton;
    TextView sign;
    TextView userText;
    TextView passText;

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            signUpButton(sign.getRootView());
        }
        return false;
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.imageViewLogo || v.getId() == R.id.backgroundLayout)
        {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Instagram");

        signUp = true;

        signUpButton = findViewById(R.id.buttonSign);
        sign = findViewById(R.id.textViewSign);
        userText = findViewById(R.id.editTextUser);
        passText = findViewById(R.id.editTextPass);
        ImageView logoImageView = findViewById(R.id.imageViewLogo);
        ConstraintLayout backgroundCon = findViewById(R.id.backgroundLayout);

        logoImageView.setOnClickListener(this);
        backgroundCon.setOnClickListener(this);
        passText.setOnKeyListener(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("d5f424686d10beb43387e2951086cced0c6c8042")
                .clientKey("371cfb4d853bd4f5f0ebc9e64d1b101419407d08")
                .server("http://18.224.25.46:80/parse/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        if(ParseUser.getCurrentUser() != null)
        {
            showUserList();
        }
    }

    public void signUpButton(View view)
    {
        if(signUp)
        {
            if(userText.getText().toString().equals("") || passText.getText().toString().equals(""))
            {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ParseUser user = new ParseUser();
                user.setUsername(userText.getText().toString());
                user.setPassword(passText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            showUserList();
                        }else
                        {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Username already Exists", Toast.LENGTH_SHORT).show();
                            ParseUser.logOut();
                        }
                    }
                });
            }
        }else
        {
            ParseUser.logInInBackground(userText.getText().toString(), passText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(user != null)
                    {
                        showUserList();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Invalid Username of Pass", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void change(View view)
    {
        if(signUp)
        {
            signUpButton.setText("LOGIN");
            sign.setText("or, Sign Up");
            signUp = false;
        }else
        {
            signUpButton.setText("SIGN UP");
            sign.setText("or, Login");
            signUp = true;
        }
    }

    public void showUserList()
    {
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }
}
