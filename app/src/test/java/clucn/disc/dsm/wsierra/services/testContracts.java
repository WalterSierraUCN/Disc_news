/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra.services;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import clucn.disc.dsm.wsierra.BaseTest;
import clucn.disc.dsm.wsierra.model.News;

public final class testContracts extends BaseTest {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(testContracts.class);

    /**
     * Testing the constructor
     */
    @Test
    public void testConstructor(){

        //Call the constructor
        Contracts contracts = new ContractsImplFaker();
    Assertions.assertNotNull(contracts,"Contracts null");
    }

    /**
     * Testing the save
     */
    @Test
    public void testSave(){

        log.debug("Testing the save");
        // The Contracts Implementation
        Contracts contracts = new ContractsImplFaker();

        // ??
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {contracts.save(null);});

        // The Faker
        Faker faker = new Faker();

        final int N = 20;

        // Add 20 news to the backend.
        for(int i = 0; i<N; i++){
            News news = new News(
                    faker.book().title(),
                    faker.book().publisher(),
                    faker.book().author(),
                    faker.internet().url(),
                    faker.internet().url(),
                    faker.book().genre(),
                    faker.dune().quote(),
                    ZonedDateTime.now(ZoneId.of("-3")));

            log.info("TheNews: {}: {}.", i, toString(news));

            // Save into backend
            contracts.save(news);
        }

        // Verify list's size.
        Assertions.assertEquals(10,contracts.retrieveNews(10).size());
        Assertions.assertEquals(1,contracts.retrieveNews(1).size());

        // The size > the real size
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
           Assertions.assertEquals(100,contracts.retrieveNews(100).size());
        });

        // Negative or zero size
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contracts.retrieveNews(-100);
            contracts.retrieveNews(0);
        });

        // Test duplicates.
        News news = contracts.retrieveNews(1).get(0);
        Assertions.assertNotNull(news);

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
           contracts.save(news);
        });
        /*
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {contracts.save(news);});

         */

        log.debug("Done.");
    }


}
