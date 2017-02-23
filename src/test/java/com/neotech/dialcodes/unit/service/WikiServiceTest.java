package com.neotech.dialcodes.unit.service;

import com.neotech.dialcodes.data.model.Country;
import com.neotech.dialcodes.data.service.WikiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Created by Artis on 2/21/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.yml")
public class WikiServiceTest {

    private WikiService wikiService;

    @Value("${wiki.url}")
    String wikiUrl;

    @Before
    public void setUp() throws Exception {
        wikiService = new WikiService();
    }

    @Test
    public void testLoadCountries() throws Exception {
        List<Country> countryList = wikiService.loadCountries(wikiUrl);
        assertThat(countryList.size(), greaterThan(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadCountriesWithWrongUrl() throws Exception {
        List<Country> countryList = wikiService.loadCountries("wrong");
    }

    @Test(expected = RuntimeException.class)
    public void testLoadCountriesFromWrongSource() throws Exception {
        List<Country> countryList = wikiService.loadCountries("http://google.com");
    }
}
