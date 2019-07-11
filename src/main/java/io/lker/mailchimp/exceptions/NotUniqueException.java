package io.lker.mailchimp.exceptions;

public class NotUniqueException extends RuntimeException {

    public NotUniqueException(String field){
        super(String.format("%s: field must be unique.", field));
    }
}
