/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

/**
 * @author Walter Sierra Vega
 */
package clucn.disc.dsm.wsierra.services;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import clucn.disc.dsm.wsierra.model.News;

public final class ContractsImplFaker implements Contracts {
    /**
     * The List of News.
     */
    private final List<News> listNews = new ArrayList<>();

    /**
     * The Constructor.
     */
    public ContractsImplFaker() {
        // Generate test data
        Faker faker = new Faker();
        int N = 20;

        for(int i = 0; i < N;i++){

            // Test: valid data
                News news =
                        new News(
                               faker.book().title(),
                                faker.book().publisher(),
                                faker.book().author(),
                                faker.internet().url(),
                                faker.internet().url(),
                                faker.book().genre(),
                                faker.dune().quote(),
                                ZonedDateTime.now(ZoneId.of("-3")));
                this.save(news);
        }
    }

    /**
     * Get the list of news.
     * @param size
     * @return
     */
    @Override
    public List<News> retrieveNews(final Integer size) {

        // Preconditions
        if(size <= 0){
            throw new IllegalArgumentException("Size cannot be 0 or negative");

        }

        // Check the list
        if(size > this.listNews.size()){
            throw new IndexOutOfBoundsException("Size > The current size");
        }

        // Return the unmodifiable list
        return Collections.unmodifiableList(
                this.listNews.subList(this.listNews.size()-size, this.listNews.size())
        );
    }

    /**
     * Save one News to the system.
     * @param news
     */
    @Override
    public void save(News news) {

        // Nullity test
        if(news == null){
            throw new IllegalArgumentException("Need news != null");
        }

        // No duplicates allowed
        for(News n : this.listNews){

            if( n != null && n.getId().equals(news.getId())){
                throw new IllegalArgumentException("News already in the list");
            }
        }

        // Insert news at the end
        this.listNews.add(news);

        // Sort the list by publishedAt
        this.listNews.sort((a,b) -> b.getPublishedAt().compareTo(a.getPublishedAt()));

    }
}
