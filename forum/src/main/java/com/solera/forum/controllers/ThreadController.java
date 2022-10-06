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

    @GetMapping
    public List<ForumThread> getThreads() {
        return threadService.getThreads();
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity saveThread(@RequestBody ForumThread thread,
                                                  @PathVariable(value = "userId") int userId) {
        return threadService.createThread(thread, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteThread(@PathVariable int id) {
        threadService.deleteThread(id);
        return new ResponseEntity("Thread deleted successfully.", HttpStatus.OK);
    }

}
