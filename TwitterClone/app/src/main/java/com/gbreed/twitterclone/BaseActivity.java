package com.gbreed.twitterclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity
{
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case R.id.tweet:
                Log.i("Tweet","tweet");
                sendATweet();
                return true;
            case R.id.yourFeed:
                Log.i("Feed","Feed");
                startActivity(new Intent(getApplicationContext(), ViewTweetsActivity.class));
                return true;
            case R.id.logout:
                ParseUser.logOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;

            default:
                return false;
        }
    }

    public void sendATweet()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Post A Tweet");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE);
        builder.setView(input);

        builder.setPositiveButton("Post", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(!input.getText().toString().equals(""))
                {
                    ParseObject tweet = new ParseObject("Tweets");
                    tweet.put("username", ParseUser.getCurrentUser().getUsername());
                    tweet.put("tweet", input.getText().toString());

                    tweet.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e)
                        {
                            if(e == null) {
                                Toast.makeText(BaseActivity.this, "Tweet Posted!", Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Log.i("Error", e.toString());
                            }
                        }
                    });
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }
}
