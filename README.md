# The Disc News

The DISC News its an Android Application.

## Domain Model

```
@startuml

package externals* #ffcccc{

    package org.threeten.bp{

        class ZonedDateTime{
            ...
        }
    }

}

package clucn.disc.dsn.wsierra{

    package model #ccffcc{
        class News <<entity>> {
            -id: Long
            - title: String
            - source: String
            - author: String
            - url: String
            - urlImage: String
            - description: String
            - content: String
            + News(..)
            +getId(): String
            +getTitle(): String
            +getSource(): String
            +getAuthor(): String
            +getUrl(): String
            +getUrlImage(): String
            +getDescription(): String
            +getContent(): String
             }

             News *--> "1" ZonedDateTime : - publishedAt
    }

    package services #ccccff {
        interface Contracts <<interface>>{
            +  retrieveNews(size: Integer)  List<News>
            +  save(news: News) : void
        }
        Contracts ..> News: <<use>>
    }
}

@enduml
```


## License
[MIT](https://choosealicense.com/licenses/mit/)