package com.chamil.bugtrackerapi.exception;

public class APIException extends RuntimeException {
    private final ErrorCode errorCode;
    private String additionalInfo;

    public APIException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public APIException(ErrorCode errorCode, String additionalInfo) {
        this(errorCode);
        this.additionalInfo = additionalInfo;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
