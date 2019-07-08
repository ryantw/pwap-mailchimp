package io.lker.mailchimp.controllers;

import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.models.MCSubscriberDTO;
import io.lker.mailchimp.services.UserServiceImpl;
import io.lker.mailchimp.util.SubscriberMapper;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/subscribe")
@RestController
@Slf4j
public class SubscribeController {

    private final UserServiceImpl userService;
    private final SubscriberMapper subscriberMapper;


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public ResponseEntity<Set<MCSubscriberDTO>> findAll() {
        return ResponseEntity.ok(subscriberMapper.toMCSubscriberDTOs(userService.findAll()));
    }

    @PostMapping
    public ResponseEntity<MCSubscriberDTO> save(@RequestBody MCSubscriber user){
        final String MC_LIST = "9ac5e96108";
        log.info("Attempting to save new user.");

        if(StringUtils.isNotBlank(MC_LIST)){
            log.info("Saving mailchimp");
            user.setMcList(MC_LIST);
            user = userService.saveToMailchimp(user);
        } else {
            log.info("Saving normally");
            user = userService.save(user);
        }

        return ResponseEntity.ok(subscriberMapper.toMCSubscriberDTO(user));
    }
}
