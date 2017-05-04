package com.drozdz.mateusz.hsbc.domain;

import java.util.Date;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
public class Tweet {

    private final User user;
    private final String messsage;
    private final Date createdAt;

    private Tweet(User user, String messsage) {
        this.user = user;
        this.messsage = messsage;
        this.createdAt = new Date();
    }

    public User getUser() {
        return user;
    }

    public String getMesssage() {
        return messsage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public static Tweet createTweet(User user, String message) {
        return new Tweet(user, message);
    }
}
