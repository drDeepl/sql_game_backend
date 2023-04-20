package ru.sqlinvestigation.RestAPI.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.List;

public class BindingResultChecker {
    public void check(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = Collections.singletonList(bindingResult.getFieldError());
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new NotFoundException(errorMsg.toString());
        }
    }
}
