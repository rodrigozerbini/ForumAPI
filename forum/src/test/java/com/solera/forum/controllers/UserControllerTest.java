package com.solera.forum.controllers;

import com.solera.forum.models.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    @Order(1)
    void getUsers() {
        List<User> users = userController.getUsers();
        Assert.isTrue(users.size() == 3, "Incorrect number of users");
    }

    @Test
    @Order(2)
    void getUserById() {
        int id = 1;
        User user = userController.getUser(id).getBody();
        Assert.isTrue(user.getUsername().equals("alex"), "Incorrect user retrieved");
    }

    @Test
    @Order(3)
    void createUSer() {
        User user = new User("rapha@solera.com", "rapha", "password", null);

        ResponseEntity<User> userResponse = userController.saveUser(user);
        Assert.isTrue(userResponse.getStatusCodeValue() == 201, "User could not be created");
    }

//    @Test
//    @Order(4)
//    void deleteUser() {
//        int id = 7;
//        userController.deleteUser(7);
//        Assert.isTrue(userController.getUser(7).getStatusCodeValue() == 500, "Error in deleting");
//    }
}
