package com.drozdz.mateusz.hsbc;

import com.drozdz.mateusz.hsbc.controllers.UserController;
import com.drozdz.mateusz.hsbc.domain.User;
import com.drozdz.mateusz.hsbc.services.TweetService;
import com.drozdz.mateusz.hsbc.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

/**
 * Created by Mateusz Drożdż on 29.04.17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private TweetService tweetService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(Optional.of(User.createUser("nick")));

    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetUserByNick() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{nick}", "nick"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetUserTweets() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{nick}/tweets", "nick"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));


    }
}
