package io.lker.mailchimp.services;

import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.repositories.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
}
