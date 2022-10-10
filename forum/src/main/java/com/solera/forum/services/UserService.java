package com.solera.forum.services;

import com.solera.forum.repositories.UserRepository;
import com.solera.forum.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity createUser(User user) {
        if(emailAlreadyExists((user.getEmail())))  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"msg\":\"There is already an account associated with this email.\"}");
        }
        User newUser = userRepository.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("{\"msg\":\"User created successfully.\"}");
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity getUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\":\"User not found.\"}");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    public ResponseEntity deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\":\"User not found.\"}");
        }
        userRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("{\"msg\":\"User deleted successfully.\"}");
    }

    public boolean emailAlreadyExists(String email) {
        List<User> users = getUsers();
        for(User user : users){
            if(user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    public ResponseEntity logIn(String email, String password) {
        List<User> users = userRepository.findAll();
        for(User user : users) {
            String userEmail = user.getEmail();
            if(userEmail.equals(email)) {
                if(user.getPassword().equals(password)) {
                    return ResponseEntity.status(HttpStatus.OK).body("{\"msg\":\"Logged in successfully.\"}");
                }
                else ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\":\"Login credentials incorrect.\"}");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\":\"Login credentials incorrect.\"}");
    }
}
