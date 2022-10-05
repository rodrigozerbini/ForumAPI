package com.solera.forum.services;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.Post;
import com.solera.forum.models.User;
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

    public ResponseEntity<String> createPost(Post post, int threadId) {
        Optional<ForumThread> thread = threadRepository.findById(threadId);
        if(thread.isEmpty()) {
            return new ResponseEntity<>("Thread not found.", HttpStatus.NOT_FOUND);
        }
        // check that there are no previous posts in this thread with the same title
        if(!titleExists(thread.get(), post.getTitle())) {
            if(!hasBannedWord(post)) {
                post.setThread(thread.get());
                postRepository.save(post);
                return new ResponseEntity<>("Post created successfully.", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Oooops! You cannot use banned words, motherfucker.", HttpStatus.BAD_REQUEST);

        }
        else {
            return new ResponseEntity<>("This title's post already exists in this thread. Change the title", HttpStatus.BAD_REQUEST);
        }
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
