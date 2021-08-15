package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingsController {
    @GetMapping("/greeting-hello")
    String greeting(@RequestParam(name="name", required = false, defaultValue = "Class 401D2") String name, Model model) {
        System.out.println("The name is >>> " + name);
        model.addAttribute("name", name);
        model.addAttribute("age", 100);
        return "greeting";
    }

    @ResponseBody
    @GetMapping("/auth")
    User authentication() {
        return new User("baraa123", "foobar");
    }

    @ResponseBody
    @PostMapping("/users")
    User createUser(@RequestBody User user) {
        return user;
    }
}
