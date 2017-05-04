package com.drozdz.mateusz.hsbc.dao;

import com.drozdz.mateusz.hsbc.domain.User;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
public interface UserRepository {

    Set<User> getUsers();

    Optional<User> getUser(String nick);

    void addUser(User user);
}
