package io.lker.mailchimp.util;

import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.models.MCSubscriberDTO;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface SubscriberMapper {

    MCSubscriberDTO toMCSubscriberDTO(MCSubscriber subscriber);
    Set<MCSubscriberDTO> toMCSubscriberDTOs(Set<MCSubscriber> subscribers);
    MCSubscriber toMCSubscriber(MCSubscriberDTO mcSubscriberDTO);
}
