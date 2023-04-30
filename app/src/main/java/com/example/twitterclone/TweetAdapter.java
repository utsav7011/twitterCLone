package com.example.twitterclone;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class TweetAdapter extends ArrayAdapter<Tweet> {
    private Context context;

    public TweetAdapter(Context context, List<Tweet> tweets){
        super(context, R.layout.item_tweet, tweets);
        this.context = context;
    }

}
