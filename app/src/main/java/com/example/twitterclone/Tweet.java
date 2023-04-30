package com.example.twitterclone;

public class Tweet {

    private String username;
    private String displayName;
    private String tweet;
    private String publihTime;

    public Tweet(String username, String displayName, String tweet, String publishTime){
        setDisplayName(displayName);
        setPublihTime(publishTime);
        setTweet(tweet);
        setUsername(username);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getPublihTime() {
        return publihTime;
    }

    public void setPublihTime(String publihTime) {
        this.publihTime = publihTime;
    }
}
