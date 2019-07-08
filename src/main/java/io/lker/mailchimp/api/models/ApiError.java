package io.lker.mailchimp.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String debugMessage;

    public ApiError(){
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message){
        timestamp = LocalDateTime.now();
        this.error = message;
    }

    public ApiError(HttpStatus status, String message, Throwable ex){
        timestamp = LocalDateTime.now();
        this.status = status;
        this.error = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
}
