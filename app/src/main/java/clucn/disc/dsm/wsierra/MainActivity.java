package clucn.disc.dsm.wsierra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import clucn.disc.dsm.wsierra.databinding.ActivityMainBinding;
import clucn.disc.dsm.wsierra.model.NewsViewModel;

public class MainActivity extends AppCompatActivity {

    /**
     * The Logger
     */

    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    /**
     * The bindings
     */
    private ActivityMainBinding binding;

    /**
     * The New's view model
     */
    private NewsViewModel newsViewModel;

    /**
     * The news adapter.
     */
    private NewsAdapter newsAdapter;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the xml
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        // Set toolbar
        this.setSupportActionBar(this.binding.toolbar);

        // Builds the news' view model
        this.newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // Swipe.
        this.binding.amSrlRefresh.setOnRefreshListener(()->{
            log.debug("Refreshing news... ");
            this.newsViewModel.refresh();
        });

        // Instantiate the Adapter.
        this.newsAdapter = new NewsAdapter();

        // Configure the RecyclerView
        {
            // 1. Layout
            this.binding.amRvListNews.setLayoutManager(new LinearLayoutManager(this));

            // 2. Decoration
            this.binding.amRvListNews.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

            // 3. The Adapter.
            this.binding.amRvListNews.setAdapter(this.newsAdapter);
        }

        // Observe new's list
        this.newsViewModel.getNews().observe(this, news -> {
            log.debug("News: {}", news.size());

            // Notify the adapter with the list of news.
            this.newsAdapter.setNews(news);

            // Hide rotating circle
            this.binding.amSrlRefresh.setRefreshing(false);
        });


    }

    /**
     * OnStart
     */
    @Override
    protected void onStart(){
        super.onStart();
        log.debug("Onstart !!!");
    }


}