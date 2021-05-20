/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

/**
 * @author Walter Sierra Vega
 */
package clucn.disc.dsm.wsierra.services;

import java.util.ArrayList;
import java.util.List;

import clucn.disc.dsm.wsierra.model.News;

public final class ContractsImplFaker implements Contracts {

    /**
     * Get the list of news.
     * @param size
     * @return
     */
    @Override
    public List<News> retrieveNews(Integer size) {
        return new ArrayList<>();
    }

    /**
     * Save one News to the system.
     * @param news
     */
    @Override
    public void save(News news) {

    }
}
