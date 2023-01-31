package com.ishingarov.catus.exception;

public class ProccessingErrorException extends RuntimeException {
    public ProccessingErrorException(String errorMessage) {
        super(errorMessage);
    }

    public ProccessingErrorException() {
        super();
    }
}
