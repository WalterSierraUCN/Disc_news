/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

/**
 * @author Walter Sierra Vega
 */
package clucn.disc.dsm.wsierra.services;

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
        // Nothing here
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

        // Error! Warning!
        return Collections.unmodifiableList(this.listNews);
    }

    /**
     * Save one News to the system.
     * @param news
     */
    @Override
    public void save(News news) {

        if(news == null){
            throw new IllegalArgumentException("Need news != null");
        }
        for(News n : this.listNews){

            if( n != null && n.getId().equals(news.getId())){
                throw new IllegalArgumentException("News already in the list");
            }
        }
        this.listNews.add(news);
    }
}
