package com.drozdz.mateusz.hsbc.services;

import com.drozdz.mateusz.hsbc.dao.UserRepository;
import com.drozdz.mateusz.hsbc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Mateusz Drożdż on 30.04.17.
 */
@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public Set<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public Optional<User> getUser(String nick) {
        return userRepository.getUser(nick);
    }

    @Override
    public Set<User> getFollowees(String nick) {
        return getUser(nick).map(User::getFollowees).orElse(Collections.emptySet());
    }

    @Override
    public Set<User> getFollowers(String nick) {
        return getUser(nick).map(User::getFollowers).orElse(Collections.emptySet());
    }

    @Override
    public void follow(String nick, String followeeNick) {
        Optional<User> user = getUser(nick);
        Optional<User> followee = getUser(followeeNick);
        if (user.isPresent() && followee.isPresent()) {
            follow(user.get(), followee.get());
        }
    }

    private void follow(User user, User followee) {
        user.getFollowees().add(followee);
        followee.getFollowers().add(user);
    }

    @Override
    public void unfollow(String nick, String followeeNick) {
        Optional<User> user = getUser(nick);
        Optional<User> followee = getUser(followeeNick);
        if (user.isPresent() && followee.isPresent()) {
            unfollow(user.get(), followee.get());
        }
    }

    private void unfollow(User user, User followee) {
        user.getFollowees().remove(followee);
        followee.getFollowers().remove(user);
    }
}
