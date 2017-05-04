package com.drozdz.mateusz.hsbc.controllers;

import com.drozdz.mateusz.hsbc.domain.Tweet;
import com.drozdz.mateusz.hsbc.domain.User;
import com.drozdz.mateusz.hsbc.services.TweetService;
import com.drozdz.mateusz.hsbc.services.UserService;
import com.drozdz.mateusz.hsbc.util.InvalidMessageException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
@Api(
        description = "Controller responsible for managing users and their tweets"
)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @ResponseBody
    @RequestMapping(path = "/{nick}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String nick) {
        return ResponseEntity.ok((userService.getUser(nick).orElse(null)));
    }

    @ResponseBody
    @RequestMapping(path = "/{nick}/tweets", method = RequestMethod.GET)
    public ResponseEntity<List<Tweet>> getUserTweets(@PathVariable String nick) {
        List<Tweet> tweets = tweetService.getTweets(nick);
        return ResponseEntity.ok(tweets);
    }

    @ResponseBody
    @RequestMapping(path = "/{nick}/tweets", method = RequestMethod.POST)
    public ResponseEntity postTweet(@PathVariable String nick, @RequestBody String message) {
        try {
            tweetService.postTweet(nick, message);
            return ResponseEntity.ok().build();
        } catch (InvalidMessageException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(path = "/{nick}/followees", method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getFollowee(@PathVariable String nick) {
        return ResponseEntity.ok(userService.getFollowees(nick));
    }

    @RequestMapping(path = "/{nick}/followees", method = RequestMethod.POST)
    public void follow(@PathVariable String nick, @RequestBody String followee) {
        userService.follow(nick, followee);
    }

    @RequestMapping(path = "/{nick}/followees", method = RequestMethod.DELETE)
    public void unfollow(@PathVariable String nick, @RequestBody String followee) {
        userService.unfollow(nick, followee);
    }

    @ResponseBody
    @RequestMapping(path = "/{nick}/followers", method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getFollowers(@PathVariable String nick) {
        return ResponseEntity.ok(userService.getFollowers(nick));
    }
    
    @ResponseBody
    @RequestMapping(path = "/{nick}/followees/tweets", method = RequestMethod.GET)
    public ResponseEntity<List<Tweet>> getFolloweesTweets(@PathVariable String nick) {
        return ResponseEntity.ok(tweetService.getFolloweesTweets(nick));
    }

}

