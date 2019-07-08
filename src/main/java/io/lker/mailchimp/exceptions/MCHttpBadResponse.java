package io.lker.mailchimp.exceptions;


import org.springframework.http.HttpStatus;

public class MCHttpBadResponse extends Exception {

    private HttpStatus httpStatus;

    public MCHttpBadResponse(HttpStatus httpStatus){
        super("[MC]: Request not successful: " + httpStatus);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
