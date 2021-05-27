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

    package net.openhft.hashing{
        class LongHashFunction{
            ...
        }
    }
    package com.github.javafaker{
        class Faker{
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
             News --* LongHashFunction : - **use**
    }

    package services #ccccff {
        interface Contracts <<interface>>{
            +  retrieveNews(size: Integer)  List<News>
            +  save(news: News) : void
        }
        Contracts ..> News: <<use>>

        class ContractsImplFaker {
                - listNews : List<News>
            }

            ContractsImplFaker ..|> Contracts
            ContractsImplFaker ..> Faker : <<use>>
    }


}

@enduml
```


## License
[MIT](https://choosealicense.com/licenses/mit/)