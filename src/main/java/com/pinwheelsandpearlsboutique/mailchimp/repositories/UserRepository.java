package com.pinwheelsandpearlsboutique.mailchimp.repositories;

import com.pinwheelsandpearlsboutique.mailchimp.models.PWAPUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<PWAPUser, Long> {
}
