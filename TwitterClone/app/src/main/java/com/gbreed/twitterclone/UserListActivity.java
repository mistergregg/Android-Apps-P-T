package com.gbreed.twitterclone;

import android.os.Bundle;

import android.util.SparseBooleanArray;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.CheckedTextView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        final ListView myList = findViewById(R.id.userListView);
        final ArrayList<String> users = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, users);
        myList.setAdapter(arrayAdapter);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e)
            {
                if(e == null && objects.size() > 0)
                {
                    for(ParseUser user :  objects)
                    {
                        users.add(user.getUsername());
                    }
                    arrayAdapter.notifyDataSetChanged();

                    for(String username : users)
                    {
                        if(ParseUser.getCurrentUser().getList("follows").contains(username))
                        {
                            myList.setItemChecked(users.indexOf(username), true);
                        }
                    }
                }
            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                CheckedTextView checkedTextView = (CheckedTextView) view;

                if(checkedTextView.isChecked())
                {
                    ParseUser.getCurrentUser().add("follows", users.get(position));
                }else
                {
                    ParseUser.getCurrentUser().getList("follows").remove(users.get(position));
                    List tmpUsers = ParseUser.getCurrentUser().getList("follows");
                    ParseUser.getCurrentUser().remove("follows");
                    ParseUser.getCurrentUser().put("follows", tmpUsers);
                }
                ParseUser.getCurrentUser().saveInBackground();
            }
        });
    }
}
