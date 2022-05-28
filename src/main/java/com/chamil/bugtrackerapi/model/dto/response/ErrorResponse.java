package com.chamil.bugtrackerapi.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
    private String additionalInfo;
}
