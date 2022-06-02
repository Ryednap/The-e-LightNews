package com.example.newsapp.news.api;

import com.example.newsapp.news.model.Bing.BingImage;
import com.example.newsapp.news.model.Bing.BingNewsItems;
import com.example.newsapp.news.model.Bing.BingNews;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BingApi {

    private BingNews searchNews() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://bing-news-search1.p.rapidapi.com/news?count=100&originalImg=true&safeSearch=Off&textFormat=Raw")
                .get()
                .addHeader("X-BingApis-SDK", "true")
                .addHeader("X-RapidAPI-Host", "bing-news-search1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "d6217bed1fmsh3401f63869c0870p1a266ejsn8b87b818226f")
                .build();
        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // For registering date-time
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return new BingNews();
        }

        return objectMapper.readValue(responseBody.string(), BingNews.class);
    }

    public List<BingImage> searchImage(String query) throws IOException {
        OkHttpClient client = new OkHttpClient();
        query = query.replaceAll(" ", "%20");
        Request request = new Request.Builder()
                .url("https://bing-image-search1.p.rapidapi.com/images/search?q=%s&count=3".formatted(query))
                .get()
                .addHeader("X-RapidAPI-Host", "bing-image-search1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "b2b05c4d7amsh71b603bc4cdac6cp113663jsn3b826c254487")
                .build();

        Response response = client.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();


        ResponseBody responseBody = response.body();
        if (responseBody == null) return new ArrayList<>();
        return mapper.readValue(responseBody.string(), Wrapper.class).getValue();
    }

    public List<BingNewsItems> filterNews() {
        try {
            List<BingNewsItems> bingNewsItemsModels = searchNews().getValue();
            return bingNewsItemsModels.stream()
                    .filter(bingNewsItemsModel -> bingNewsItemsModel.getImage()!=null && bingNewsItemsModel.getImage().getThumbnail()!=null)
                    .collect(Collectors.toList());
        } catch (IOException ie) {
            log.error("Exception during api call to bing News Search : " + ie.getMessage());
            return new ArrayList<>();
        }
    }


}

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
class Wrapper {
    @JsonProperty("value")
    List<BingImage> value;
};
