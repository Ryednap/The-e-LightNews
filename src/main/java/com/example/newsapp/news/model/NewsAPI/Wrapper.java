package com.example.newsapp.news.model.NewsAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
@ToString
@NoArgsConstructor
public class Wrapper {
    private String status;
    private Integer totalResults;
    private List<Articles> articles;
}
