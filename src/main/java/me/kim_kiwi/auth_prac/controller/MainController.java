package me.kim_kiwi.auth_prac.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @GetMapping("/")
    public String home() {
        return "home: public";
    }
    @GetMapping("/user")
    public String user() {
        return "user: user+ only";
    }
    @GetMapping("admin")
    public String admin() {
        return "admin: admin only";
    }
}
