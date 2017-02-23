package com.neotech.dialcodes.unit.service;

import com.neotech.dialcodes.data.DataContainer;
import com.neotech.dialcodes.data.model.Country;
import com.neotech.dialcodes.data.service.CountryService;
import com.neotech.dialcodes.exception.CountryNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by Artis on 2/21/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

    @Mock
    DataContainer dataContainer;

    @InjectMocks
    private CountryService countryService;

    @Test
    public void testGetCountryByDialCode() throws Exception {
        Country country = new Country("Latvia", "371");
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(country);
        when(dataContainer.getCountries()).thenReturn(countries);

        Country actualCountry = countryService.getCountryByPhoneNumber("37112345678");
        assertThat(actualCountry.getName(), is(country.getName()));
    }

    @Test(expected = CountryNotFoundException.class)
    public void testGetCountryByNonExistingDialCode() throws Exception {
        when(dataContainer.getCountries()).thenReturn(new ArrayList<>());
        countryService.getCountryByPhoneNumber("+00012345678");
    }
}
