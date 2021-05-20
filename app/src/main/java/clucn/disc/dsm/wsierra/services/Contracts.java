    /*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra.services;

    import java.util.List;

    import clucn.disc.dsm.wsierra.model.News;

    public interface Contracts {
        /**
         * Get the list of news.
         * @param size
         * @return
         */
    List<News> retrieveNews(Integer size);

        /**
         * Save one News to the system.
         * @param news
         */
    void save(News news);
}
