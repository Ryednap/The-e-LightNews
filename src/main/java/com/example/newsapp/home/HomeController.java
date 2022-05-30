package com.example.newsapp.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ModelAndView getHomePage() {
        System.out.println("Hello World");
        return new ModelAndView("home");
    }
}
