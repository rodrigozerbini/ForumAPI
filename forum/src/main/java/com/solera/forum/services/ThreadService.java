package com.solera.forum.services;

import com.google.gson.Gson;
import com.solera.forum.models.ForumThread;
import com.solera.forum.models.User;
import com.solera.forum.repositories.ThreadRepository;
import com.solera.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {

    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity createThread(ForumThread thread, int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"msg\":\"User not found.\"}");
        }
        else if (titleExists(thread.getTitle())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"msg\":\"Thread with this title already exists.\"}");
        }
        else {
            thread.setUser(user.get());
            threadRepository.save(thread);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"msg\":\"Thread created successfully.\"}");
        }
    }

    private boolean titleExists(String title) {
        List<ForumThread> threads = threadRepository.findAll();
        for(ForumThread thread : threads) {
            if(thread.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public List<ForumThread> getThreads() {
        return threadRepository.findAll();
    }

    public ResponseEntity deleteThread(int id) {
        Optional<ForumThread> thread = threadRepository.findById(id);
        if(thread.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\":\"Thread not found.\"}");
        }
        threadRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("{\"msg\":\"Thread deleted successfully.\"}");
    }
}
