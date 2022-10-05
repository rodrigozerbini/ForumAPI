package com.solera.forum.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "threads", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    private String title;
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", nullable = false)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread(int id, String title, Date date, User user) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.user = user;
    }

    public Thread() {
    }
}