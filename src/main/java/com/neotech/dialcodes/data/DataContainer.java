package com.neotech.dialcodes.data;

import com.neotech.dialcodes.data.model.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artis on 2/21/2017.
 */

@Component
public class DataContainer {

    private List<Country> countries = new ArrayList<>();

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> c) {
        countries = c;
    }

}
