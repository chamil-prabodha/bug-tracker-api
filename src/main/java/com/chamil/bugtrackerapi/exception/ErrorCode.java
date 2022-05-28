package com.chamil.bugtrackerapi.exception;

public enum ErrorCode {
    NOT_FOUND("404", "Requested resource not found"),
    UNKNOWN_EXCEPTION("500", "An unknown error occurred");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
