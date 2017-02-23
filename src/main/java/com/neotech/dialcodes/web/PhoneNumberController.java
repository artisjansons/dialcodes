package com.neotech.dialcodes.web;

import com.neotech.dialcodes.data.dto.CountryDto;
import com.neotech.dialcodes.data.dto.PhoneRequestDto;
import com.neotech.dialcodes.data.model.Country;
import com.neotech.dialcodes.data.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Artis on 2/21/2017.
 */
@RestController
@RequestMapping("phone-number")
public class PhoneNumberController {

    private final CountryService countryService;

    @Autowired
    public PhoneNumberController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "lookup", method = RequestMethod.POST)
    public CountryDto getCountry(@RequestBody @Validated PhoneRequestDto phoneRequest) {
        Country country = countryService.getCountryByPhoneNumber(phoneRequest.getPhoneNumber().substring(1)); // substring "+" from number
        return new CountryDto(country.getName(), country.getDialCode());
    }

}
