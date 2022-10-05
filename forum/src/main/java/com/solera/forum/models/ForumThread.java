package com.solera.forum.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "threads", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class ForumThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    private String createdAt;
    private String title;
    private String userEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public ForumThread(String title, String userEmail, String createdAt) {
        this.title = title;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }

    public ForumThread() {
    }
}