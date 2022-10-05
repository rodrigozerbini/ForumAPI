package com.solera.forum.repositories;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
