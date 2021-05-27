/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra.model;

import net.openhft.hashing.LongHashFunction;

import org.threeten.bp.ZonedDateTime;

public final class News {

    /*
     * Unique Id.
     */
    private final Long id;

    /*
     *¨The title.
     * Restrictions:
     * - not null
     * - size > 2
     */
    private String title;
    /**
     * The Source
     */
    private final String source;
    /**
     * The Author
     */
    private final String author;
    /**
     * The Url
     */
    private final String url;
    /**
     * The Url Image
     */
    private final String ulrImage;
    /**
     * The description
     */
    private final String description;
    /**
     * The content
     */
    private final String content;
    /**
     * The Date of Publish.
     */
    private final ZonedDateTime publishedAt;

    /** The Constructor.
     *
     * @param title
     * @param source
     * @param author
     * @param url
     * @param ulrImage
     * @param description
     * @param content
     * @param publishedAt
     */

    public News(String title, String source, String author, String url, String ulrImage, String description, String content, ZonedDateTime publishedAt) {
        // FIXME: add the hash ( title + source + author)



        //Title replace
        this.title= (title != null && title.length() > 0) ? title: "No Title";


        // Source validation
        if(source==null){
            throw new IllegalArgumentException("Source was null");
        }

        if( source.length() <= 4){
            throw new IllegalArgumentException("Source size was <= 4 [" + source +"]");
        }
        this.source = source;
        //Author
        this.author = ( author !=null ) ? author:"No Author";

        // Hash call.
        this.id = LongHashFunction.xx().hashChars(this.getTitle() + "|" + this.getSource() + "|" + this.getAuthor()
        );

        this.url = url;
        this.ulrImage = ulrImage;
        this.description = description;
        this.content = content;

        //PublishedAt Validation
        if(publishedAt == null){
            throw new IllegalArgumentException("The PublishedAt needed!");
        }
        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getUlrImage() {
        return ulrImage;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }
}
