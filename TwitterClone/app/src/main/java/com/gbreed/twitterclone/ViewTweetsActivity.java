package com.gbreed.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewTweetsActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> myFollowers;
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tweets);

        setTitle("Tweets");

        loadTweets();
    }

    public void loadTweets()
    {
        myListView = findViewById(R.id.tweetList);
        myFollowers = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myFollowers);
        myListView.setAdapter(arrayAdapter);

        ParseQuery<ParseObject> getTweets = ParseQuery.getQuery("Tweets");
        getTweets.whereContainedIn("username", ParseUser.getCurrentUser().getList("follows"));
        getTweets.orderByDescending("createdAt");
        getTweets.setLimit(20);

        getTweets.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e)
            {
                if(e == null)
                {
                    for(ParseObject aTweet : list)
                    {
                        myFollowers.add(aTweet.get("username") + " : " + aTweet.get("tweet"));
                        Log.i("A Tweet : ", aTweet.get("username") + " : " + aTweet.get("tweet"));
                    }

                    arrayAdapter.notifyDataSetChanged();
                }else
                {
                    e.printStackTrace();
                }
            }
        });

        /*
        final ParseQuery<ParseObject> queryGetTweets = ParseQuery.getQuery("Tweets");


        ParseQuery<ParseUser> queryGetFollowers = ParseUser.getQuery();
        queryGetFollowers.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());

        queryGetFollowers.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e)
            {
                if(e == null)
                {
                    JSONArray followers = object.getJSONArray("follows");

                    if(followers != null)
                    {
                        for(int i = 0; i < followers.length(); i++)
                        {
                            try
                            {
                                JSONObject tmpUser = followers.getJSONObject(i);
                                queryGetTweets.whereEqualTo("username", tmpUser.getString("name"));
                                Log.i("username", tmpUser.getString("name"));
                            }catch (Exception t)
                            {
                                t.printStackTrace();

                                return;
                            }
                        }
                    }

                    queryGetTweets.setLimit(10);
                    queryGetTweets.orderByDescending("createdAt");
                    queryGetTweets.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e)
                        {
                            if(e == null)
                            {
                                for(ParseObject aTweet: objects)
                                {
                                    myFollowers.add(aTweet.get("username") + " : " + aTweet.get("tweet"));
                                    Log.i("A Tweet : ", aTweet.get("username") + " : " + aTweet.get("tweet"));
                                }
                                arrayAdapter.notifyDataSetChanged();
                            }else
                            {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        */
    }
}
