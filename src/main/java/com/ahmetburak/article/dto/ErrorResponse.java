package com.ahmetburak.article.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by ahmetburakozturk on 21.08.2022
 **/
@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> errors;

    private record ValidationError(String field, String message) {
    }


}
