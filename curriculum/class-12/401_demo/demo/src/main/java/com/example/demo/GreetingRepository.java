package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    Greeting findGreetingByRating(int rating);

}
