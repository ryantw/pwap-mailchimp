package io.lker.mailchimp.util;

import io.lker.mailchimp.models.Submitter;
import io.lker.mailchimp.models.SubmitterDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface SubscriberMapper {

    SubmitterDTO toMCSubscriberDTO(Submitter subscriber);
    Set<SubmitterDTO> toMCSubscriberDTOs(Set<Submitter> subscribers);
    Submitter toMCSubscriber(SubmitterDTO submitterDTO);
}
