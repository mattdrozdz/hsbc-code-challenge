package com.drozdz.mateusz.hsbc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
public class User {

    private final String nick;

    @JsonIgnore
    private final Set<User> followers = new HashSet<>();
    @JsonIgnore
    private final Set<User> followees = new HashSet<>();

    private User(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<User> getFollowees() {
        return followees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(nick, user.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nick);
    }

    public static User createUser(String nick) {
        return new User(nick);
    }
}
