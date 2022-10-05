package com.solera.forum.repositories;

import com.solera.forum.models.ForumThread;
import com.solera.forum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<ForumThread, Integer> {
}
