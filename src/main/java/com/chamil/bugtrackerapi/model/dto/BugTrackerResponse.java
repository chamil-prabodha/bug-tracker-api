package com.chamil.bugtrackerapi.model.dto;

import lombok.Getter;

@Getter
public class BugTrackerResponse<T> {
    private final boolean success;
    private final T data;
    private ErrorResponse errorResponse;

    public BugTrackerResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public BugTrackerResponse(boolean success, T data, ErrorResponse errorResponse) {
        this(success, data);
        this.errorResponse = errorResponse;
    }
}
