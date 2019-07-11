package io.lker.mailchimp.services;

import io.lker.mailchimp.converters.SubmitterToSubscriber;
import io.lker.mailchimp.models.Submitter;
import io.lker.mailchimp.repositories.SubmitterRepository;
import io.lker.mailchimp.util.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class SubmitterServiceImpl implements SubmitterService {

    private final SubmitterRepository submitterRepository;
    private final SubmitterToSubscriber submitterToSubscriber;

    @Autowired
    private Configuration configuration;

    public SubmitterServiceImpl(SubmitterRepository submitterRepository, SubmitterToSubscriber submitterToSubscriber) {
        this.submitterRepository = submitterRepository;
        this.submitterToSubscriber = submitterToSubscriber;
    }

    @Override
    public Set<Submitter> findAll() {
        Set<Submitter> users = new HashSet<>();
        submitterRepository.findAll().forEach(users::add);
        log.info("Size:" + users.size());
        return users;
    }

    @Override
    public Submitter findById(Long aLong) {
        // Make own exception
        return submitterRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(aLong)));
    }

    @Override
    public Submitter save(Submitter object) {
        return submitterRepository.save(object);
    }

    @Override
    public void delete(Submitter object) {
        submitterRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        submitterRepository.deleteById(aLong);
    }

    @Override
    public Optional<Submitter> findByEmailAddress(String emailAddress) {
        return Optional.ofNullable(submitterRepository.findByEmailAddress("%" + emailAddress + "%"))
                .orElseThrow(() -> new ResourceNotFoundException("Email Address"));
    }

    @Override
    public boolean existsByEmailAddress(String emailAddress) {
        return submitterRepository.existsByEmailAddress(emailAddress);
    }

}
