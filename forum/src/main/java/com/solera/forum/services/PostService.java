package com.solera.forum.services;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.Post;
import com.solera.forum.models.User;
import com.solera.forum.repositories.PostRepository;
import com.solera.forum.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ThreadRepository threadRepository;

    public Post createPost(Post post, int threadId) {
        Optional<ForumThread> thread = threadRepository.findById(threadId);
        if(thread.isEmpty()) {
            return null;
        }
        post.setThread(thread.get());
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPost(int id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void deletePost(int id) {
        postRepository.deleteById(id);
    }
}
