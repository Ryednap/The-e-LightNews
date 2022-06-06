package com.example.newsapp.home;

import com.example.newsapp.news.api.BingApi;
import com.example.newsapp.news.api.NewsApi;
import com.example.newsapp.news.model.Bing.BingImage;
import com.example.newsapp.news.model.Bing.BingNewsItems;
import com.example.newsapp.news.model.NewsAPI.Articles;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class HomeService {
    private final BingApi bingApi;
    private final NewsApi newsApi;


    public Pair<Integer, Integer> getImageDimension(String urlQuery) throws IOException {
        URL url = new URL(urlQuery);
        BufferedImage image = ImageIO.read(url);
        return Pair.of(image.getHeight(), image.getWidth());
    }

    @Async("asyncExec")
    public CompletableFuture<List<BingNewsItems>> getNews (int imageWidth, int imageHeight) {
        List<BingNewsItems> bingNewsItemsModels = bingApi.filterNews();

        System.out.println(Thread.currentThread().getId());
        return CompletableFuture.completedFuture(bingNewsItemsModels.stream().filter(bingNewsItemsModel -> {
                    Pair<Integer, Integer> dimension = null;
                    try {
                        dimension = getImageDimension(bingNewsItemsModel.getImage().getThumbnail().getUrl());
                        System.out.println(dimension);
                    } catch (IOException e) {
                        log.error("Exception while checking image dimension : " + e.getMessage());
                        throw new RuntimeException(e);
                    }
                    return dimension.getFirst() >= imageHeight && dimension.getSecond() >= imageWidth;
                })
                .collect(Collectors.toList()));
    }

    private BingImage searchImage(String query) throws IOException {
        return bingApi.searchImage(query + " logo").get(0);
    }

    @Async("asyncExec")
    public CompletableFuture<List<Articles>> getTopHeadlines () throws IOException {
        System.out.println(Thread.currentThread().getId());
        var filterNews = newsApi.filterNews();
        var news = filterNews.subList(0, Math.min(filterNews.size(), 6));
        for (Articles articles : news) {
            articles.setSourceImageURL(searchImage(articles.getSource().getName()).getThumbnailUrl());
        }
        return CompletableFuture.completedFuture(news);
    }
}
