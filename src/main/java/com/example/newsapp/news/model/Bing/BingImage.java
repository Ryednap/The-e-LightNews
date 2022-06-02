package com.example.newsapp.news.model.Bing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BingImage {
    private String thumbnailUrl;
    private String contentUrl;
}
