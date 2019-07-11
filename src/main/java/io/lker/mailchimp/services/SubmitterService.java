package io.lker.mailchimp.services;

import io.lker.mailchimp.models.Submitter;

import java.util.Optional;

public interface SubmitterService extends CrudService<Submitter, Long> {
    Optional<Submitter> findByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);
}
