package io.lker.mailchimp.controllers;

import io.lker.mailchimp.api.MailChimp;
import io.lker.mailchimp.converters.MCResponseToSubscriber;
import io.lker.mailchimp.exceptions.MCHttpBadResponse;
import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.models.MCSubscriberDTO;
import io.lker.mailchimp.services.UserServiceImpl;
import io.lker.mailchimp.util.Configuration;
import io.lker.mailchimp.util.SubscriberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/subscribe")
@RestController
@Slf4j
public class SubscribeController {

    private final UserServiceImpl userService;
    private final MCResponseToSubscriber mcResponseToSubscriber;
    private final SubscriberMapper subscriberMapper;

    @Autowired
    private Configuration configuration;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public ResponseEntity<Set<MCSubscriberDTO>> findAll() {
        return ResponseEntity.ok(subscriberMapper.toMCSubscriberDTOs(userService.findAll()));
    }

    @PostMapping
    public MCSubscriber save(@RequestBody MCSubscriber user){
        final String MC_LIST = "9ac5e96108";
        log.info("Attempting to save new user.");

        try {
            if(userService.existsByEmailAddress(user.getEmailAddress())){
                log.error("Email Address already exists! Throw something and get outta here!");
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Exists");
            }

            user.setMcList(MC_LIST);
            MailChimp mailChimp = new MailChimp(configuration.getAPI_KEY());
            String response = mailChimp.subscribeUserToList(MC_LIST, user);

            if(response != null){
                user = mcResponseToSubscriber.convert(response);
                user.setMcSuccess(true);
            }
        } catch (MCHttpBadResponse e) {
            user.setMcSuccess(false);
            userService.save(user);
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Not Authorized", e);
        }
        return userService.save(user);
    }
}
