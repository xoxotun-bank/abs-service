package com.example.absservice.exception;

import org.springframework.http.*;

public class ClientNotFoundException extends DefaultException {

    private static final String CLIENT_NOT_FOUND = "Данные о клиенте с указанным идентификатором не найдены";

    private static final int CLIENT_NOT_FOUND_CODE = 2004;

    private static final HttpStatus CLIENT_NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;

    public ClientNotFoundException() {
        super(CLIENT_NOT_FOUND, CLIENT_NOT_FOUND_CODE, CLIENT_NOT_FOUND_STATUS);
    }

    public ClientNotFoundException(
        Throwable cause
    ) {
        super(CLIENT_NOT_FOUND, CLIENT_NOT_FOUND_CODE, CLIENT_NOT_FOUND_STATUS, cause);
    }

}
