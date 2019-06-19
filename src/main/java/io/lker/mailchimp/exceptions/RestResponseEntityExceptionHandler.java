package io.lker.mailchimp.exceptions;

import io.lker.mailchimp.api.models.CustomErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MCHttpBadResponse.class)
    protected ResponseEntity<Object> handleConflict(
            MCHttpBadResponse ex, WebRequest request) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(ex.getHttpStatus());

        return handleExceptionInternal(ex, errors,
                new HttpHeaders(), HttpStatus.resolve(errors.getStatus()), request);
    }
}
