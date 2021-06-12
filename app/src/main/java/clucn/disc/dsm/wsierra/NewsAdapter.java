/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import clucn.disc.dsm.wsierra.databinding.RowNewsBinding;
import clucn.disc.dsm.wsierra.model.News;

public final class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    /**
     * DataSource
     */
    private List<News> theNews = new ArrayList<>();

    public NewsAdapter(){
        // Nothing
    }

    /**
     * Update data source.
     * @param news
     */
    public void setNews(final List<News> news){
        this.theNews = news;

        // Notify the RecyclerView.
        this.notifyDataSetChanged();
    }

    /**
     * Called when The RecyclerView need a fresh row of NewsViewholder
     * @param parent The ViewGroup where the new will be added
     * @param viewType The view of this type
     * @return the NewsViewHolder ready for action
     */
    @Override
    public NewsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        // Call the constructor inflating the layout
    return new NewsViewHolder(
            RowNewsBinding.inflate(
                    LayoutInflater.from(parent.getContext())));
    }

    /**
     * Called by the RecyclerView when need to display some data at specific position.
     *
     * @param holder holder to use to set the GUI data
     * @param position
     */
    @Override
    public void onBindViewHolder(final NewsAdapter.NewsViewHolder holder, final int position) {
        // Bind the ViewHolder + News at certain position.
        holder.bind(this.theNews.get(position));
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return theNews.size();
    }



    /**
     * ViewHolder's implementation.
     */
    public static class NewsViewHolder extends RecyclerView.ViewHolder{


        /**
         * The GUI of news.
         */
        private final RowNewsBinding rowNewsBinding;

        /**
         *
         * @param rowNewsBinding the layout to use.
         */
        public NewsViewHolder(final RowNewsBinding rowNewsBinding) {
            super(rowNewsBinding.getRoot());
            this.rowNewsBinding = rowNewsBinding;
        }

        public void bind(final News news){

            // Bind the title.
            this.rowNewsBinding.rnTvTitle.setText(news.getTitle());

            // Bind the author,
            this.rowNewsBinding.rnRvAuthor.setText(news.getAuthor());
        }
    }
}
