package io.lker.mailchimp.repositories;

import io.lker.mailchimp.models.MCSubscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<MCSubscriber, Long> {
    Optional<MCSubscriber> findByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);

}
