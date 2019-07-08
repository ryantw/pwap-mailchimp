package io.lker.mailchimp.exceptions;

import io.lker.mailchimp.api.models.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String error = "Malformed JSON request.";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    // Handle mailchimp errors, even return them -- not helpful to user
    @ExceptionHandler(MCHttpBadResponse.class)
    protected ResponseEntity<Object> handleMailChimpError(MCHttpBadResponse ex){
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
    /*
    @ExceptionHandler(MCHttpBadResponse.class)
    protected ResponseEntity<Object> handleConflict(
            MCHttpBadResponse ex, WebRequest request) {

        log.error("Exception throw.");
        ApiError errors = new ApiError();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(ex.getHttpStatus());

        return handleExceptionInternal(ex, errors,
                new HttpHeaders(), errors.getStatus(), request);
    }
    */

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
