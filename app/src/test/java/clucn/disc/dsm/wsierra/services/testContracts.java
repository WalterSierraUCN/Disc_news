/*
 * Copyright (c) 2021. Walter Sierra Vega
 */

package clucn.disc.dsm.wsierra.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import clucn.disc.dsm.wsierra.model.News;

public final class testContracts {

    /**
     * Testing the constructor
     */
    @Test
    public void testConstructor(){

        //Call the constructor
        Contracts contracts = new ContractsImplFaker();
    Assertions.assertNotNull(contracts,"Contracts null");
    }

    @Test
    public void testSave(){
        Contracts contracts = new ContractsImplFaker();
        News news = new News("The Title",
                "The Source",
                "The Author",
                "The Url",
                "The Image Url",
                "The Description",
                "The Content",
                ZonedDateTime.now(ZoneId.of("-3")));

        // Save into backend
        contracts.save(news);

        //
        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {contracts.save(null);});


        // Verify list's size.
        Assertions.assertEquals(1,contracts.retrieveNews(10).size());

        Assertions.assertThrows(IllegalArgumentException.class, () ->
        {contracts.save(news);});
    }


}
