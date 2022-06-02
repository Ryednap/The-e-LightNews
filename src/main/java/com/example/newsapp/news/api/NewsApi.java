package com.example.newsapp.news.api;

import com.example.newsapp.news.model.NewsAPI.Articles;
import com.example.newsapp.news.model.NewsAPI.Wrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;

import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.util.List;

@Component
public class NewsApi {
    private Wrapper search() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://newsapi.org/v2/top-headlines?country=in&apiKey=ce320ff1e3494d1ca206407d12a1492d")
                .get()
                .addHeader("X-Api-Key", "ce320ff1e3494d1ca206407d12a1492d")
                .addHeader("pageSize", String.valueOf(10))
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        if (responseBody == null) return new Wrapper();
        return objectMapper.readValue(responseBody.string(), Wrapper.class);
    }

    public List<Articles> filterNews () throws IOException {
        return search().getArticles().stream().filter(articles -> articles.getUrlToImage() != null & articles.getUrl() != null)
                .toList();
    }


}
