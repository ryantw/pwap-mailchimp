package io.lker.mailchimp.services;

import io.lker.mailchimp.api.MailChimp;
import io.lker.mailchimp.converters.SubmitterToSubscriber;
import io.lker.mailchimp.exceptions.MCHttpBadResponse;
import io.lker.mailchimp.models.Submitter;
import io.lker.mailchimp.models.Subscriber;
import io.lker.mailchimp.repositories.SubmitterRepository;
import io.lker.mailchimp.repositories.SubscriberRepository;
import io.lker.mailchimp.util.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
public class DBManageServiceImpl implements DBManageService {

    private final SubscriberRepository subscriberRepository;
    private final SubmitterRepository submitterRepository;
    private final SubmitterToSubscriber submitterToSubscriber;

    @Autowired
    private Configuration configuration;

    public DBManageServiceImpl(SubscriberRepository subscriberRepository,
                               SubmitterRepository submitterRepository,
                               SubmitterToSubscriber submitterToSubscriber) {
        this.subscriberRepository = subscriberRepository;
        this.submitterRepository = submitterRepository;
        this.submitterToSubscriber = submitterToSubscriber;
    }

    @Override
    public Object save(Object submitter) {
        Submitter saveSubmission = saveLocal(submitter);

        if(saveSubmission == null)
            return null;

        Subscriber subscriber = saveToMailChimp(saveSubmission);

        return subscriber;
    }

    private Submitter saveLocal(Object submitter){
        Submitter saveSubmission = (Submitter) submitter;

        if(submitterRepository.existsByEmailAddress(saveSubmission.getEmailAddress()))
            return null;

        log.info("save Local");
        saveSubmission.setCreatedDate(LocalDateTime.now());
        saveSubmission.setLastAttempt(LocalDateTime.now());
        saveSubmission.setAttemptedTries(1);

        return submitterRepository.save(saveSubmission);
    }

    private Subscriber saveToMailChimp(Submitter submitter){
        MailChimp mailChimp = new MailChimp(configuration.getAPI_KEY());
        Subscriber saveSubscriber = null;
        String response = null;
        try {
            response = mailChimp.subscribeUserToList(submitter.getMcList(), submitter);
        } catch (MCHttpBadResponse mcHttpBadResponse) {
            mcHttpBadResponse.printStackTrace();
        }

        if(StringUtils.isNotBlank(response)){
            saveSubscriber = submitterToSubscriber.convert(response);
        }

        return subscriberRepository.save(saveSubscriber);
    }

    @Override
    public Set<Object> findAll() {
        return null;
    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public void deleteById(Object o) {

    }

}
