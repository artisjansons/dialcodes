package com.neotech.dialcodes.data.dto;

/**
 * Created by Artis on 2/21/2017.
 */
public class MessageDto {

    private String message;

    public MessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
