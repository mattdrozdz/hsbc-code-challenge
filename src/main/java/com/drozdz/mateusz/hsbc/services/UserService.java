package com.drozdz.mateusz.hsbc.services;

import com.drozdz.mateusz.hsbc.domain.User;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
public interface UserService {

    void addUser(User user);

    Set<User> getUsers();

    Optional<User> getUser(String nick);

    Set<User> getFollowees(String nick);

    Set<User> getFollowers(String nick);

    void follow(String nick, String followeeNick);

    void unfollow(String nick, String followeeNick);
}
