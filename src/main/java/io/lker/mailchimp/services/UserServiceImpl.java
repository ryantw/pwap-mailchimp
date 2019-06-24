package io.lker.mailchimp.services;

import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<MCSubscriber> findAll() {
        Set<MCSubscriber> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
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
}
