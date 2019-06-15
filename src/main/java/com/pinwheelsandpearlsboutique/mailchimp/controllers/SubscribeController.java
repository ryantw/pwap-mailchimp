package com.pinwheelsandpearlsboutique.mailchimp.controllers;

import com.pinwheelsandpearlsboutique.mailchimp.models.PWAPUser;
import com.pinwheelsandpearlsboutique.mailchimp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SubscribeController {

    private final UserService userService;

    public SubscribeController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @PostMapping
    public PWAPUser save(@RequestBody PWAPUser user){
        log.info("SAVING NEW USER");
        return userService.save(user);
    }
}
