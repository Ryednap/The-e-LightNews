package com.example.newsapp.news.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class NewsImage {
    private String url;
    private Integer width;
    private Integer height;
}
