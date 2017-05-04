package com.drozdz.mateusz.hsbc.services;

import com.drozdz.mateusz.hsbc.dao.TweetRepository;
import com.drozdz.mateusz.hsbc.domain.Tweet;
import com.drozdz.mateusz.hsbc.domain.User;
import com.drozdz.mateusz.hsbc.util.InvalidMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Drożdż on 30.04.17.
 */
@Service
public class DefaultTweetService implements TweetService {

    private static final Comparator<Tweet> TWEET_COMPARATOR = Comparator.comparing(Tweet::getCreatedAt).reversed();
    public static final int MAX_MESSAGE_LENGTH = 140;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserService userService;

    @Override
    public void postTweet(String nick, String message) throws InvalidMessageException {
        if (message.length() > MAX_MESSAGE_LENGTH) {
            throw new InvalidMessageException("Message is too long");
        }
        User user = getOrCreateNewUser(nick);
        Tweet tweet = Tweet.createTweet(user, message);
        tweetRepository.addTweet(tweet);
    }

    private User getOrCreateNewUser(String nick) {
        Optional<User> existingUser = userService.getUser(nick);
        if (!existingUser.isPresent()) {
            User user = User.createUser(nick);
            userService.addUser(user);
            return user;
        } else {
            return existingUser.get();
        }
    }

    @Override
    public List<Tweet> getTweets(String nick) {
        Optional<User> user = userService.getUser(nick);
        return user.map(this::getUserTweets).orElse(Collections.emptyList());
    }

    private List<Tweet> getUserTweets(User user) {
        return tweetRepository.getUserTweets(user).stream()
                .sorted(TWEET_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tweet> getFolloweesTweets(String nick) {
        return userService.getFollowees(nick).stream()
                .map(this::getUserTweets)
                .flatMap(Collection::stream)
                .sorted(TWEET_COMPARATOR)
                .collect(Collectors.toList());
    }
}
