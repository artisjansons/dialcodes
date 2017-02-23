package com.neotech.dialcodes.web;

import com.neotech.dialcodes.data.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Artis on 2/21/2017.
 */

@ControllerAdvice
public class ValidationHandler {

    private final MessageSource messageSource;

    @Autowired
    public ValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageDto processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        FieldError error = result.getFieldError();

        String message;

        try {
            message = messageSource.getMessage(error.getDefaultMessage(), null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            // fallback to default message
            message = error.getField().concat(" ").concat(error.getDefaultMessage());
        }

        return new MessageDto(message);
    }
}
