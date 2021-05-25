/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra.model;
//import org.junit.jupiter.api.Test;


import com.github.javafaker.Faker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import clucn.disc.dsm.wsierra.BaseTest;

public final class testNews extends BaseTest {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(testNews.class);

    /**
     *  Testing the constructor.
     */

    @Test
    public void testConstructor(){

        log.debug("Starting the testConstructor");

        // Generate test data
        Faker faker = new Faker();

    // Test: valid data
    {
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

      log.info("TheNews: {}." + toString(news));


        // Testing the internal class
        Assertions.assertNotNull(news.getId());
        Assertions.assertNotNull(news.getPublishedAt());
        // TODO: Complete the test.
    }

    // Test: no valid data
    {
      // Illegal data
      Assertions.assertThrows(
          IllegalArgumentException.class,
          () -> {
            // Published At
            new News(
                null,
                "The Source",
                "The Author",
                "The Url",
                "The Imagen Url",
                "The Description",
                "The Content",
                null);
            // Source null
              new News(
                      "The Title",
                      null,
                      "The Author",
                      "The Url",
                      "The Imagen Url",
                      "The Description",
                      "The Content",
                      ZonedDateTime.now(ZoneId.of("-3")));
              // Source size < 4
              new News(
                      "The Title",
                      "The",
                      "The Author",
                      "The Url",
                      "The Imagen Url",
                      "The Description",
                      "The Content",
                      ZonedDateTime.now(ZoneId.of("-3")));

          });

        // Title validation
        News news =
                new News(
                        null,
                        "The Source",
                        "The Author",
                        "The Url",
                        "The Imagen Url",
                        "The Description",
                        "The Content",
                        ZonedDateTime.now(ZoneId.of("-3")));
                Assertions.assertEquals("No Title", news.getTitle());
        }
    }
}
