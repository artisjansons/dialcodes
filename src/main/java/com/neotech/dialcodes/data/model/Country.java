package com.neotech.dialcodes.data.model;

import javax.validation.constraints.NotNull;

/**
 * Created by Artis on 2/20/2017.
 */
public class Country {

    @NotNull
    private String name;
    @NotNull
    private String dialCode;

    public Country() {
    }

    public Country(String name, String dialCode) {
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
