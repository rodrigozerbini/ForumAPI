package com.solera.forum.services;

import com.solera.forum.models.ForumThread;
import com.solera.forum.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadService {

    @Autowired
    ThreadRepository threadRepository;

    public ForumThread createThread(ForumThread thread) {
        return threadRepository.save(thread);
    }

    public List<ForumThread> getThreads() {
        return threadRepository.findAll();
    }
}
