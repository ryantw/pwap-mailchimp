package io.lker.mailchimp.util;

import io.lker.mailchimp.models.Submitter;
import io.lker.mailchimp.models.SubmitterDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
class SubscriberMapperTest {
    private final String EMAIL = "r@r.com";
    private final String FIRST_NAME = "Rude";
    private final String LAST_NAME = "Dude";

    private SubmitterDTO mappedUser;

    @BeforeEach
    void setUp() {
        Submitter user = Submitter.builder().emailAddress(EMAIL).build();

        SubscriberMapper subscriberMapper = Mappers.getMapper(SubscriberMapper.class);

        mappedUser = subscriberMapper.toMCSubscriberDTO(user);
    }

    @Test
    void mappedUserSameEmail() {
        assertEquals(mappedUser.getEmailAddress(), EMAIL);
    }
}