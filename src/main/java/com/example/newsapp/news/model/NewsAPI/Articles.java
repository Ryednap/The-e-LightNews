package com.example.newsapp.news.model.NewsAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Articles {
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private Source source;
    private LocalDateTime publishedAt;

    @JsonIgnore
    private String sourceImageURL;
}
