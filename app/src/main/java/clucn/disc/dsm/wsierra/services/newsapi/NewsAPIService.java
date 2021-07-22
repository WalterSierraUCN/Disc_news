/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra.services.newsapi;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clucn.disc.dsm.wsierra.model.News;
import clucn.disc.dsm.wsierra.model.newsapi.Article;
import clucn.disc.dsm.wsierra.model.newsapi.NewsAPIResponse;
import clucn.disc.dsm.wsierra.services.Contracts;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 *
 */
public class NewsAPIService implements Contracts {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(NewsAPIService.class);

    /**
     * The news api
     */
    private final NewsApi newsApi;

    /**
     * The current zone
     */
    private static final ZoneId theZone = ZoneId.of("-4");

    public NewsAPIService(){

        this.newsApi = new Retrofit.Builder()
                .baseUrl(NewsApi.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                            .build().create(NewsApi.class);
    }

    /**
     * Get the list of news.
     * @param size
     * @return
     */
    @Override
    public List<News> retrieveNews(Integer size) {

        /**
         * The call to get the newsApi response
         */
        Call <NewsAPIResponse> theCall = this.newsApi.getTopHeadLines(size, NewsApi.Category.science);

    try {
      // Conects and gets the response in synchrony way.
      Response<NewsAPIResponse> theResponse = theCall.execute();

      // if .. unsuccessfull ..
      if (!theResponse.isSuccessful()) {
        log.error("Can't get the news: <{}> " + theResponse.errorBody().string());
        throw new RuntimeException("Can't get the news,     response code: ." + theResponse.code());
      }



        // .. read the NewsApiResponse from the body of the call.
        NewsAPIResponse newsAPIResponse = theResponse.body();

        // Nullity verification
        if (newsAPIResponse == null) {
          throw new RuntimeException("Body of NewsAPI was null");
        }

        // The result
        List<News> theNews = new ArrayList<>();

        // .. iterate over the list the article
        for (Article article : newsAPIResponse.articles) {

          // TODO: Convert to transformer pattern.
          News news =
              new News(
                  article.title,
                  article.source.name,
                  article.author,
                  article.url,
                  article.urlToImage,
                  article.description,
                  article.content,
                  parseDate(article.publishedAt));

                  // insert the news
                  theNews.add(news);
                    }
             return theNews;


        }catch (IOException ex){
            log.error("Can't get the news.", ex);
            throw new RuntimeException("Can't get the newss.", ex);
        }


    }

    /**
     * Convert the string date to ZonedDateTime
     * @param publishedAt used to convert.
     * @return the {@link ZonedDateTime}
     */
    private ZonedDateTime parseDate(String publishedAt) {
        return ZonedDateTime
                .parse(publishedAt)
                .withZoneSameInstant(theZone);
    }

    @Override
    public void save(News news) {
        throw new NotImplementedException("Method not implemented");
    }

    public interface NewsApi{

        /**
         * The base url
         */
        String BASE_URL = "https://newsapi.org/v2/";

        /**
         * The Api key
         */
        String API_KEY = "1f36d2851acc47c2ad6e7e308617f98d";

        /**
         *
         *  @return the Call with the link {@link NewsAPIResponse}
         */
        @Headers("X-API-Key: "+API_KEY)
        @GET("everything")
        Call<NewsAPIResponse> getEverything();

        /**
         * https://newsapi.org/docs/endpoints/top-headlines
         *
         * @param pageSize The number of results to return per page (request). 20 is the default, 100 is the maximum.
         * @return The call to get NewsAPIResponse
         */

        @Headers("X-Api-Key: "+ API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHeadLines(@Query("pagesize") int pageSize);

        @Headers("X-Api-Key: "+ API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHeadLines(@Query("pagesize") int pageSize,@Query("category") Category category);

        /**
         *  The category you want to get.
         *  Possible options: business, entertainment, general
         *  health, science, sports, technology.
         */
        enum Category{
            business, entertainment, general, health, science, sports, technology
        }


    }
}
