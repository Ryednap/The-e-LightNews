package com.example.newsapp.home;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping()
    public ModelAndView getHomePage() throws IOException, ExecutionException, InterruptedException {
        CompletableFuture<?> topHeadlines = homeService.getTopHeadlines();
        CompletableFuture<?> news = homeService.getNews(100, 100);

        CompletableFuture.allOf(topHeadlines, news).join();

        return new ModelAndView("home")
                .addObject("topNews", topHeadlines.get())
                .addObject("newsItems", news.get());
    }

}

