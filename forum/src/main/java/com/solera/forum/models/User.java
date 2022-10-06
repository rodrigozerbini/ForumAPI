package com.solera.forum.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ForumThread> threads = new HashSet<>();



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ForumThread> getThreads() {
        return threads;
    }

    public void setThreads(HashSet<ForumThread> threads) {
        this.threads = threads;
    }

    public User(String email, String username, String password, Set<ForumThread> threads) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.threads = threads;
    }

    public User() {
    }
}
