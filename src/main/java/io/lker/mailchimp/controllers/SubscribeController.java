package io.lker.mailchimp.controllers;

import io.lker.mailchimp.models.Submitter;
import io.lker.mailchimp.models.SubmitterDTO;
import io.lker.mailchimp.services.DBManageServiceImpl;
import io.lker.mailchimp.services.SubmitterServiceImpl;
import io.lker.mailchimp.util.SubscriberMapper;
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

    private final DBManageServiceImpl dbManageService;
    private final SubmitterServiceImpl submitterService;
    private final SubscriberMapper subscriberMapper;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public ResponseEntity<Set<SubmitterDTO>> findAll() {
        return ResponseEntity.ok(subscriberMapper.toMCSubscriberDTOs(submitterService.findAll()));
    }

    @PostMapping
    public ResponseEntity<SubmitterDTO> save(@RequestBody Submitter user){
        final String MC_LIST = "9ac5e96108";
        log.info(String.format("Attempting to subscribe: %s", user.getEmailAddress()));
        user.setMcList(MC_LIST);
        try {
            Object submitter = dbManageService.save(user);
            if(submitter != null){
                user.setMcSuccess(true);
            }
        } catch (Exception e){
            log.error(String.format("Subscribing: %s, FAILED", user.getEmailAddress()));
            e.printStackTrace();
        }

        return ResponseEntity.ok(subscriberMapper.toMCSubscriberDTO(user));
    }
}
