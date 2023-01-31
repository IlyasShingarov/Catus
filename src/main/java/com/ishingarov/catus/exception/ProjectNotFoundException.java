package com.ishingarov.catus.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public ProjectNotFoundException() {
        super();
    }
}
