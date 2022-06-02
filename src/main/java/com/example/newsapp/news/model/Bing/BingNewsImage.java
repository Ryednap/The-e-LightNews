package com.example.newsapp.news.model.Bing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class BingNewsImage implements Serializable {
    private String _type;

    private BingNewsImageThumbnail thumbnail;

    private Boolean isLicensed;
}
