package io.lker.mailchimp.util;

import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.models.MCSubscriberDTO;
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

    private MCSubscriberDTO mappedUser;

    @BeforeEach
    void setUp() {
        MCSubscriber user = MCSubscriber.builder().emailAddress(EMAIL).firstName(FIRST_NAME)
                .lastName(LAST_NAME).build();

        SubscriberMapper subscriberMapper = Mappers.getMapper(SubscriberMapper.class);

        mappedUser = subscriberMapper.toMCSubscriberDTO(user);
    }

    @Test
    void mappedUserSameEmail() {
        assertEquals(mappedUser.getEmailAddress(), EMAIL);
    }
}