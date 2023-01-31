package com.ishingarov.catus.exception;

public class DeletionErrorException extends RuntimeException {
    public DeletionErrorException(String errorMessage) {
        super(errorMessage);
    }
}
