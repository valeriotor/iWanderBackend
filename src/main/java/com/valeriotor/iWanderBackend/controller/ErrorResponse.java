package com.valeriotor.iWanderBackend.controller;

public class ErrorResponse {
    private final Error error;
    private final String defaultMessage;

    public ErrorResponse(Error error) {
        this(error, null);
    }

    public ErrorResponse(Error error, String defaultMessage) {
        this.error = error;
        this.defaultMessage = defaultMessage;
    }

    public Error getError() {
        return error;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
