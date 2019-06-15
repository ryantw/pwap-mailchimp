package com.pinwheelsandpearlsboutique.mailchimp.services;

import com.pinwheelsandpearlsboutique.mailchimp.models.PWAPUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends CrudService<PWAPUser, Long> {

}
