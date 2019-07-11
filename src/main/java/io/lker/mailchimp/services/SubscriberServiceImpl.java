package io.lker.mailchimp.services;

import io.lker.mailchimp.models.Subscriber;
import io.lker.mailchimp.repositories.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Set<Subscriber> findAll() {
        Set<Subscriber> users = new HashSet<>();
        subscriberRepository.findAll().forEach(users::add);
        log.info("Size: " + users.size());
        return users;
    }

    @Override
    public Subscriber findById(Long aLong) {
        return subscriberRepository.findById(aLong)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(aLong)));
    }

    @Override
    public Subscriber save(Subscriber object) {
        return subscriberRepository.save(object);
    }

    @Override
    public void delete(Subscriber object) {
        subscriberRepository.delete(object);

    }

    @Override
    public void deleteById(Long aLong) {
        subscriberRepository.deleteById(aLong);

    }
}
