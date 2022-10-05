package com.solera.forum.services;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.User;
import com.solera.forum.repositories.ThreadRepository;
import com.solera.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThreadService {

    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    UserRepository userRepository;

    public ForumThread createThread(ForumThread thread, int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            return null;
        }
        thread.setUser(user.get());
        return threadRepository.save(thread);
    }

    public List<ForumThread> getThreads() {
        return threadRepository.findAll();
    }

    public void deleteThread(int id) {
        threadRepository.deleteById(id);
    }
}
