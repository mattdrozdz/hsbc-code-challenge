package com.drozdz.mateusz.hsbc.dao;

import com.drozdz.mateusz.hsbc.domain.Tweet;
import com.drozdz.mateusz.hsbc.domain.User;

import java.util.List;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
public interface TweetRepository {

    void addTweet(Tweet tweet);

    List<Tweet> getUserTweets(User user);


}
