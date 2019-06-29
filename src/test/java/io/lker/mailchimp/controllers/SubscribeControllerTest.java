package io.lker.mailchimp.controllers;

import io.lker.mailchimp.converters.MCResponseToSubscriber;
import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.models.MCSubscriberDTO;
import io.lker.mailchimp.services.UserServiceImpl;
import io.lker.mailchimp.util.SubscriberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@ExtendWith(MockitoExtension.class)
class SubscribeControllerTest {

    @Mock
    UserServiceImpl userService;

    MCResponseToSubscriber mcResponseToSubscriber;
    SubscriberMapper subscriberMapper;

    @InjectMocks
    SubscribeController subscribeController;

    MockMvc mockMvc;

    Set<MCSubscriber> users;
    Set<MCSubscriberDTO> usersDto;

    @BeforeEach
    void setUp() throws Exception {
        users = new HashSet<>();
        users.add(MCSubscriber.builder().firstName("Bob").lastName("Builder").emailAddress("bb@a.com").build());
        users.add(MCSubscriber.builder().firstName("Albert").lastName("Parr").emailAddress("albert@b.com").build());
        users.add(MCSubscriber.builder().firstName("Shan").lastName("Bryan").emailAddress("shan@c.com").build());
        MockitoAnnotations.initMocks(this);
        //subscribeController = new SubscribeController(userService, mcResponseToSubscriber, subscriberMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(subscribeController).build();
    }


    @Test
    void findAllUsers() throws Exception {
        /*
        when(userService.findAll()).thenReturn(subscriberMapper.toMCSubscriberDTOs(users));
        MvcResult result = mockMvc.perform(get("/api/subscribe")).andReturn();
        log.debug("DEBUG: " + result.getResponse().getContentAsString());
        assertNotNull(result);
        */
    }

    @Test
    void save() {

    }
}