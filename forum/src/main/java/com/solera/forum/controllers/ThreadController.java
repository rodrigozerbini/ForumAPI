package com.solera.forum.controllers;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.User;
import com.solera.forum.services.ThreadService;
import com.solera.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forum/threads")
public class ThreadController {

    @Autowired
    ThreadService threadService;

    @PostMapping
    public ResponseEntity<ForumThread> saveThread(@RequestBody ForumThread thread) {
        return new ResponseEntity<>(threadService.createThread(thread), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ForumThread> getThreads() {
        return threadService.getThreads();
    }

}
