package io.lker.mailchimp.repositories;

import io.lker.mailchimp.models.Submitter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmitterRepository extends CrudRepository<Submitter, Long> {
    Optional<Submitter> findByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);

}
