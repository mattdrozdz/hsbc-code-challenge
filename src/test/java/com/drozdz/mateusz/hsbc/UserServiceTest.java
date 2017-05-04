package com.drozdz.mateusz.hsbc;

import com.drozdz.mateusz.hsbc.dao.UserRepository;
import com.drozdz.mateusz.hsbc.domain.User;
import com.drozdz.mateusz.hsbc.services.DefaultUserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

/**
 * Created by Mateusz Drożdż on 30.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultUserService userService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(userRepository.getUser(Mockito.anyString())).thenReturn(Optional.of(User.createUser("nick")));

    }

    @Test
    public void testGetUser() throws Exception {
        User user = userService.getUser("aaa").orElse(null);
        Assertions.assertThat(user).isNotNull().hasFieldOrPropertyWithValue("nick", "nick");
    }
}
