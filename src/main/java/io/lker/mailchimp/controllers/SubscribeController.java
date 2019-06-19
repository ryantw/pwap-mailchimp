package io.lker.mailchimp.controllers;

import io.lker.mailchimp.api.MailChimp;
import io.lker.mailchimp.exceptions.MCHttpBadResponse;
import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.services.UserServiceImpl;
import io.lker.mailchimp.util.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public MCSubscriber save(@RequestBody MCSubscriber user){
        log.info("SAVING NEW USER");
        // Consider a converter
        MailChimp mailChimp = new MailChimp(configuration.getAPI_KEY());
        try {
            mailChimp.subscribeUserToList("9ac5e96108", user);
        } catch (MCHttpBadResponse e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Not Authorized", e);
        }
        return userService.save(user);
    }
}
