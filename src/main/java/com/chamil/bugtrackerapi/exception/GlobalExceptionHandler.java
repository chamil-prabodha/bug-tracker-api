package com.chamil.bugtrackerapi.exception;

import com.chamil.bugtrackerapi.model.dto.response.BugTrackerResponse;
import com.chamil.bugtrackerapi.model.dto.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {APIException.class})
    protected ResponseEntity<Object> handleAPIException(APIException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(String.valueOf(e.getErrorCode().getStatus().value()));
        errorResponse.setMessage(e.getMessage());
        errorResponse.setAdditionalInfo(e.getAdditionalInfo());
        BugTrackerResponse<Object> apiResponse = new BugTrackerResponse<>(false, null, errorResponse);
        return handleExceptionInternal(e, apiResponse, new HttpHeaders(), e.getErrorCode().getStatus(), request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleUnknownException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorResponse.setMessage("An unknown exception occurred");
        errorResponse.setAdditionalInfo(e.getMessage());
        BugTrackerResponse<Object> apiResponse = new BugTrackerResponse<>(false, null, errorResponse);
        return handleExceptionInternal(e, apiResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setMessage("Arguments are not valid");
        errorResponse.setAdditionalInfo(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        BugTrackerResponse<Object> apiResponse = new BugTrackerResponse<>(false, null, errorResponse);
        return handleExceptionInternal(e, apiResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
