package com.solera.forum.controllers;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.Post;
import com.solera.forum.services.PostService;
import com.solera.forum.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forum/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @PostMapping("/{threadId}/add")
    public ResponseEntity<Post> savePost(@RequestBody Post post,
                                         @PathVariable(value = "threadId") int threadId) {
        return new ResponseEntity<>(postService.createPost(post, threadId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) {
        postService.deletePost(id);
        return new ResponseEntity("Post deleted successfully.", HttpStatus.OK);
    }
}