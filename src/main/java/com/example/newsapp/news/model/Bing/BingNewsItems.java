package com.example.newsapp.news.model.Bing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class BingNewsItems implements Serializable {

    private String _type;
    private String name;

    private String url;

    private BingNewsImage image;

    private String description;

    private List<BingNewsProviderModel> provider;

    private LocalDateTime datePublished;
}
