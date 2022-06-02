package com.example.newsapp.news.model.Bing;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BingNews implements Serializable {
    private String _type;
    private String webSearchUrl;
    List<BingNewsItems> value;
}
