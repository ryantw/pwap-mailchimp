package com.pinwheelsandpearlsboutique.mailchimp.services;

import com.pinwheelsandpearlsboutique.mailchimp.models.PWAPUser;
import com.pinwheelsandpearlsboutique.mailchimp.repositories.UserRepository;
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
    public Set<PWAPUser> findAll() {
        Set<PWAPUser> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public PWAPUser findById(Long aLong) {
        // Make own exception
        return userRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(aLong)));
    }

    @Override
    public PWAPUser save(PWAPUser object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(PWAPUser object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
