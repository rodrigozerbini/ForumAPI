package com.solera.forum;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solera.forum.controllers.UserController;
import com.solera.forum.models.User;
import com.solera.forum.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private UserService userService;

    User user0 = new User("user0@solera.com", "user0", "hello", null);
    User user1 = new User("user1@solera.com", "user1", "password", null);
    User user2 = new User("user2@solera.com", "user2", "PASSWORD23452345", null);

    @Test
    public void getUsers_sucess() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(user0, user1, user2));

        // Mocking the method getUsers to return the specific mockUsers when invoked
        Mockito.when(userService.getUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/forum/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].username", is("user2")));
    }

    @Test
    public void createUser() throws Exception {

        User mockUser = new User("manu@solera.com", "manuel", "mypassword", null);

        Mockito.when(userService.createUser(mockUser)).thenReturn(ResponseEntity
                .status(HttpStatus.CREATED)
                .body("{\"msg\":\"User created successfully.\"}"));

        String userJson = "{\"email\":\"manu@solera.com\",\"username\":\"manuel\",\"password\":\"mypassword\"}";

        // Send user as body to /forum/users/add
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/forum/users/add")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }
}
