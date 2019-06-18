package com.pinwheelsandpearlsboutique.mailchimp.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Subscriber {
    // Match var names w/ field names

    private String email_address;
    private String email_type;
    private String status;
    private Object merge_fields;
    private Object interests;
    private String language;
    private Boolean vip;
    private Object location;
    private Object[] marketing_permissions;
    private String ip_signup;
    private String timestamp_signup;
    private String ip_opt;
    private String timestamp_opt;
    private Object[] tags;

}
