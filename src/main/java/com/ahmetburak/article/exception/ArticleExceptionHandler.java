package com.ahmetburak.article.exception;

import com.ahmetburak.article.dto.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by AhmetBurak on 23.12.2021
 **/
@ControllerAdvice
public class ArticleExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String TRACE = "trace";

    @Value("${print-stack.trace:false}")
    private boolean printStackTrace;

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandlerDataIntegrity(DataIntegrityViolationException ex, WebRequest request) {
        logger.error(ex.getMessage());
        return buildErrorResponse(ex, ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex, WebRequest request) {
        ex.printStackTrace();
        logger.error(ex.getMessage());
        return buildErrorResponse(ex, ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = buildErrorResponse(ex, ex.getMessage(), status, request);
        return new ResponseEntity<>(errorResponseResponseEntity, HttpStatus.BAD_REQUEST);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errorFieldMap = ex.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getCode));
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = buildErrorResponse(ex, String.valueOf(errorFieldMap), status, request);
        return new ResponseEntity<>(errorResponseResponseEntity, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), Objects.isNull(message) ? exception.getMessage() : message);

        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }
}