package io.lker.mailchimp.services;

import io.lker.mailchimp.models.MCSubscriber;

import java.util.Optional;

public interface UserService extends CrudService<MCSubscriber, Long> {
    Optional<MCSubscriber> findByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);
}
