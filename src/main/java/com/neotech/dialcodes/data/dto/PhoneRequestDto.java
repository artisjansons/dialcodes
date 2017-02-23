package com.neotech.dialcodes.data.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Artis on 2/21/2017.
 */
public class PhoneRequestDto {

    @NotNull
    @Pattern(regexp = "\\(?\\+[1-9]{1,3}\\)?[0-9]{1,3}-?[0-9]{3,5}-?[0-9]{4}(-?[0-9]{3})?",
            message = "error.validation.phone.regex")
    private String phoneNumber;

    public PhoneRequestDto() {
    }

    public PhoneRequestDto(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
