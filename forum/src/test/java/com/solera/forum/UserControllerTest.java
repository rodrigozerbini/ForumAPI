package com.solera.forum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solera.forum.models.User;
import com.solera.forum.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserControllerTest.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserRepository userRepository;

    User user1 = new User("user1@solera.com", "user1", "hello", null);
    User user2 = new User("user2@solera.com", "user2", "password", null);
    User user3 = new User("user3@solera.com", "user3", "PASSWORD23452345", null);

    @Test
    public void getUsers_success() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/forum/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].username", is("user2")));
    }
}
