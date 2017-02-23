package com.neotech.dialcodes.data.dto;

/**
 * Created by Artis on 2/21/2017.
 */
public class CountryDto {

    private String name;
    private String dialCode;

    public CountryDto() {
    }

    public CountryDto(String name, String dialCode) {
        this.name = name;
        this.dialCode = dialCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }
}
