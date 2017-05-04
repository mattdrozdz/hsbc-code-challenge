package com.drozdz.mateusz.hsbc.services;

import com.drozdz.mateusz.hsbc.domain.Tweet;
import com.drozdz.mateusz.hsbc.util.InvalidMessageException;

import java.util.List;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
public interface TweetService {

    void postTweet(String nick, String message) throws InvalidMessageException;

    List<Tweet> getTweets(String nick);

    List<Tweet> getFolloweesTweets(String nick);

}
