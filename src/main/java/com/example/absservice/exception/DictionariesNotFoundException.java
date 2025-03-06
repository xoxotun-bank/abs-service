package com.example.absservice.exception;

import org.springframework.http.*;

public class DictionariesNotFoundException extends DefaultException {

    private static final String DICTIONARIES_NOT_FOUND = "Данные о справочниках не найдены";

    private static final int DICTIONARIES_NOT_FOUND_CODE = 2005;

    private static final HttpStatus DICTIONARIES_NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public DictionariesNotFoundException() {
        super(
            DICTIONARIES_NOT_FOUND,
            DICTIONARIES_NOT_FOUND_CODE,
            DICTIONARIES_NOT_FOUND_STATUS
        );
    }

    public DictionariesNotFoundException(
        String message,
        int apiCode,
        HttpStatus httpStatus,
        Throwable cause
    ) {
        super(
            DICTIONARIES_NOT_FOUND,
            DICTIONARIES_NOT_FOUND_CODE,
            DICTIONARIES_NOT_FOUND_STATUS,
            cause
        );
    }

}
