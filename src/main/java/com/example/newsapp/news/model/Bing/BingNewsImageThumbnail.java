package com.example.newsapp.news.model.Bing;

import com.example.newsapp.news.model.NewsImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class BingNewsImageThumbnail extends NewsImage implements Serializable {

    @JsonProperty("contentUrl")
    private String url;
}
