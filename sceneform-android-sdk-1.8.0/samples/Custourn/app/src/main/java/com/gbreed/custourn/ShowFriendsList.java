package com.gbreed.custourn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowFriendsList extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ListView friendsListView = findViewById(R.id.friendslistview);
        ArrayList<String> friends = new ArrayList<>();
        friends.add("Greg");
        friends.add("Yasmina");
        friends.add("Tom");
        friends.add("George");
        friends.add("Yup");
        friends.add("Tim");
        friends.add("Sean");
        friends.add("Rob");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friends);
        friendsListView.setAdapter(arrayAdapter);
    }
}
