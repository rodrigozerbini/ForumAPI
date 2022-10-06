package com.solera.forum.services;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.Post;
import com.solera.forum.repositories.PostRepository;
import com.solera.forum.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ThreadRepository threadRepository;

    public ResponseEntity createPost(Post post, int threadId) {
        Optional<ForumThread> thread = threadRepository.findById(threadId);
        if(thread.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"msg\":\"Thread not found.\"}");
        }
        else if(titleExists(thread.get(), post.getTitle())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"msg\":\"Post with this title already exists in this thread.\"}");
        }
        else {
            post.setThread(thread.get());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"msg\":\"Post created successfully.\"}");
        }
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public ResponseEntity getPost(int id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\":\"Post not found.\"}");
        }
        return ResponseEntity.status(HttpStatus.OK).body(post.get());
    }

    public ResponseEntity deletePost(int id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"msg\":\"Post not found.\"}");
        }
        postRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("{\"msg\":\"Post deleted successfully.\"}");
    }

    public boolean titleExists(ForumThread thread, String title) {
        Set<String> postTitles = thread.getPosts().stream().map(post -> post.getTitle()).collect(Collectors.toSet());
        return postTitles.contains(title);
    }

    public boolean hasBannedWord(Post post) {
        String[] bodyWords = post.getBody().split("([.,!?:;'\\\"-]|\\s)+");
        List<String> bannedWords = new BannedWords().get();

        for(String word : bodyWords) {
            if(bannedWords.contains(word.toLowerCase()))
                return true;
        }
        return false;
    }
}