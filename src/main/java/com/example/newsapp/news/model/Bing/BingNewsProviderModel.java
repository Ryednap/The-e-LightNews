package com.example.newsapp.news.model.Bing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BingNewsProviderModel implements Serializable {
    private Long id;

    private String _type;
    private String name;

    private BingNewsImage image;

}
