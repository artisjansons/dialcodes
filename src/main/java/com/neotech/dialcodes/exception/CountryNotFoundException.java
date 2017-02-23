package com.neotech.dialcodes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Artis on 2/22/2017.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "error.country.notfound")
public class CountryNotFoundException extends RuntimeException {

}
