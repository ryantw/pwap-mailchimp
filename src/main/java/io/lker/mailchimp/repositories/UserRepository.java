package io.lker.mailchimp.repositories;

import io.lker.mailchimp.models.MCSubscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MCSubscriber, Long> {
}
