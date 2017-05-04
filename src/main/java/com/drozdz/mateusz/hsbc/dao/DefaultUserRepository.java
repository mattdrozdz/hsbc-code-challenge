package com.drozdz.mateusz.hsbc.dao;

import com.drozdz.mateusz.hsbc.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Mateusz Drożdż on 30.04.17.
 */
@Repository
public class DefaultUserRepository implements UserRepository {

    private final Set<User> users = new HashSet<>();

    @Override
    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    @Override
    public Optional<User> getUser(String nick) {
        return users.stream().filter(user -> user.getNick().equals(nick)).findAny();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
