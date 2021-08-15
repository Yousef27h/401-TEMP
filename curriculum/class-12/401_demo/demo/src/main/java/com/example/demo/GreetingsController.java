package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingsController {
    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping("/greeting-hello")
    String greeting(@RequestParam(name="name", required = false, defaultValue = "Class 401D2") String name, Model model) {
        System.out.println("The name is >>> " + name);
        model.addAttribute("name", name);
        model.addAttribute("age", 100);
        return "greeting";
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        Greeting greetings = greetingRepository.save(greeting);
        System.out.println("the greeting is >>> " + greetings.getContent());
        model.addAttribute("greeting", greetings);
        return "result";
    }

//    @ResponseBody
//    @GetMapping("/auth")
//    Account authentication() {
//        return new Account("baraa123", "foobar");
//    }
//
//    @ResponseBody
//    @PostMapping("/accounts")
//    Account createAccount(@RequestBody Account account) {
//        return accountRepository.save(account);
//    }
//
//    @GetMapping("/accounts")
//    String getAccounts(Model model) {
//        model.addAttribute("accounts", accountRepository.findAll());
//        return "accounts";
//    }
}
