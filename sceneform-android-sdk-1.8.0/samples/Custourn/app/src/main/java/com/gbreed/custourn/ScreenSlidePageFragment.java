package com.gbreed.custourn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScreenSlidePageFragment extends Fragment
{
    public static ScreenSlidePageFragment newInstance(int spot)
    {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("theSpot", spot);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        int theSpot = (int) getArguments().getSerializable("theSpot");

        ViewGroup rootView;

        if(theSpot == 1)
        {
            rootView = (ViewGroup) inflater.inflate(R.layout.user_ideas, container, false);
        }else
        {
            rootView = (ViewGroup) inflater.inflate(R.layout.show_friends, container, false);
        }

        return rootView;
    }
}
