package io.lker.mailchimp.exceptions;


public class MCHttpBadResponse extends RuntimeException {

    private int httpStatus;

    public MCHttpBadResponse(int httpStatus){
        super("Request not successful: " + httpStatus);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
