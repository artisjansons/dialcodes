package com.neotech.dialcodes.data.service;

import com.neotech.dialcodes.data.DataContainer;
import com.neotech.dialcodes.data.model.Country;
import com.neotech.dialcodes.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Artis on 2/21/2017.
 */
@Service
public class CountryService {

    private final DataContainer dataContainer;

    @Autowired
    public CountryService(DataContainer dataContainer) {
        this.dataContainer = dataContainer;
    }

    public Country getCountryByPhoneNumber(String phoneNumber) {
        Optional<Country> country = dataContainer.getCountries().stream()
                .filter(c -> phoneNumber.startsWith(c.getDialCode())).findFirst();

        if (!country.isPresent()) {
            throw new CountryNotFoundException();
        }

        return country.get();
    }
}
