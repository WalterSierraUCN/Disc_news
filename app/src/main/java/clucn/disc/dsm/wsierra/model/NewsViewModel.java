/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;

import clucn.disc.dsm.wsierra.services.Contracts;
import clucn.disc.dsm.wsierra.services.ContractsImplFaker;
import clucn.disc.dsm.wsierra.services.newsapi.NewsAPIService;

/**
 * The News View Model
 */

public final class NewsViewModel extends AndroidViewModel{

    /**
     * The Logger
     */

    private static final Logger log = LoggerFactory.getLogger(NewsViewModel.class);

    /**
     *  The Contract.
     */

    private final Contracts contracts = new NewsAPIService();

    /**
     *  The News's list
     */

    private MutableLiveData<List<News>> theNews;

    /**
     * The List of News
     */

    public MutableLiveData<List<News>> getNews(){

        return this.theNews;
    }

    /**
     * The Constructor
     * @param application
     */

    public NewsViewModel(final Application application) {
        super(application);

        this.theNews = new MutableLiveData<>();
    }

    /**
     *  Refresh (get) news from backend
     */

    public void refresh(){

        // Show message if theNews are empty
        if (this.theNews.getValue() == null || this.theNews.getValue().size() == 0){
           log.warn("No news, fetching from contracts");
       }
        {

            // Background thread
            Executors.newSingleThreadExecutor().execute(()-> {

            List<News> news = this.contracts.retrieveNews(50);

            new Handler(Looper.getMainLooper()).post(() ->{

                // Get news from backend
                this.theNews.setValue(news);
            });
        });

        }
    }
}
