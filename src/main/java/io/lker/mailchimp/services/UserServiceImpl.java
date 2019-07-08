package io.lker.mailchimp.services;

import io.lker.mailchimp.api.MailChimp;
import io.lker.mailchimp.converters.MCResponseToSubscriber;
import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.repositories.UserRepository;
import io.lker.mailchimp.util.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MCResponseToSubscriber mcResponseToSubscriber;

    @Autowired
    private Configuration configuration;

    public UserServiceImpl(UserRepository userRepository, MCResponseToSubscriber mcResponseToSubscriber) {
        this.userRepository = userRepository;
        this.mcResponseToSubscriber = mcResponseToSubscriber;
    }

    @Override
    public Set<MCSubscriber> findAll() {
        Set<MCSubscriber> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        log.info("Size:" + users.size());
        return users;
    }

    @Override
    public MCSubscriber findById(Long aLong) {
        // Make own exception
        return userRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(aLong)));
    }

    @Override
    public MCSubscriber save(MCSubscriber object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(MCSubscriber object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public Optional<MCSubscriber> findByEmailAddress(String emailAddress) {
        return Optional.ofNullable(userRepository.findByEmailAddress("%" + emailAddress + "%"))
                .orElseThrow(() -> new ResourceNotFoundException(emailAddress + ", not found!"));
    }

    @Override
    public boolean existsByEmailAddress(String emailAddress) {
        return userRepository.existsByEmailAddress(emailAddress);
    }

    public MCSubscriber saveToMailchimp(MCSubscriber object){
        if(existsByEmailAddress(object.getEmailAddress())){
            log.error("Email exists.");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Exists");
        }

        MailChimp mailChimp = new MailChimp(configuration.getAPI_KEY());
        String response = mailChimp.subscribeUserToList(object.getMcList(), object);

        if(StringUtils.isNotBlank(response)){
            object = mcResponseToSubscriber.convert(response);
            object.setMcSuccess(true);
        } else {
            object.setMcSuccess(false);
        }

        return userRepository.save(object);
    }
}
