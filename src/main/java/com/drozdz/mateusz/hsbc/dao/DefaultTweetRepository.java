package com.drozdz.mateusz.hsbc.dao;

import com.drozdz.mateusz.hsbc.domain.Tweet;
import com.drozdz.mateusz.hsbc.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Drożdż on 30.04.17.
 */
@Repository
public class DefaultTweetRepository implements TweetRepository {

    List<Tweet> tweets = new ArrayList<>();

    @Override
    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    @Override
    public List<Tweet> getUserTweets(User user) {
        return tweets.stream().filter(tweet -> tweet.getUser().equals(user)).collect(Collectors.toList());
    }
}
