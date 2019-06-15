package com.pinwheelsandpearlsboutique.mailchimp.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@org.springframework.context.annotation.Configuration
@PropertySource("classpath:mailchimp.properties")
public class Configuration {

    @Value("${API_KEY}")
    private String API_KEY;

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }
}
