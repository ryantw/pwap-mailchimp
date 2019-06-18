package com.pinwheelsandpearlsboutique.mailchimp.controllers;

import com.pinwheelsandpearlsboutique.mailchimp.api.MailChimp;
import com.pinwheelsandpearlsboutique.mailchimp.models.PWAPUser;
import com.pinwheelsandpearlsboutique.mailchimp.services.UserServiceImpl;
import com.pinwheelsandpearlsboutique.mailchimp.util.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/subscribe")
@RestController
@Slf4j
public class SubscribeController {

    private final UserServiceImpl userService;
    @Autowired
    private Configuration configuration;

    public SubscribeController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @PostMapping
    public PWAPUser save(@RequestBody PWAPUser user){
        log.info("SAVING NEW USER");
        // Consider a converter
        MailChimp mailChimp = new MailChimp(configuration.getAPI_KEY());
        mailChimp.subscribeUserToList("9ac5e96108", user);
        return userService.save(user);
    }
}
