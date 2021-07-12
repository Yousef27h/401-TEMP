package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "java_greetings")
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="greeting_content", length=50, nullable=false, unique=true)
    private String content;

    private int rating;

    public Greeting() {
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
