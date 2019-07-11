package io.lker.mailchimp.services;

import io.lker.mailchimp.models.Submitter;
import io.lker.mailchimp.repositories.SubmitterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SubmitterServiceImplTest {

    @Mock
    SubmitterRepository submitterRepository;

    @InjectMocks
    SubmitterServiceImpl userService;

    Submitter user;

    @BeforeEach
    void setUp() {
        user = Submitter.builder().id(1L).emailAddress("r@r.com")
                .build();
    }

    @Test
    void findAll() {
        Set<Submitter> users = new HashSet<>();
        users.add(Submitter.builder().id(1L).build());
        users.add(Submitter.builder().id(2L).build());
        when(submitterRepository.findAll()).thenReturn(users);

        Set<Submitter> returnedUsers = userService.findAll();
        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
    }

    @Test
    void findById() {
        Optional<Submitter> u = Optional.of(user);
        when(submitterRepository.findById(anyLong())).thenReturn(u);
        Submitter returnedUser = userService.findById(1L);
        assertNotNull(returnedUser);
        verify(submitterRepository, times(1)).findById(anyLong());
        assertEquals(Long.valueOf(1L), returnedUser.getId());
    }

    @Test
    void save() {
        when(submitterRepository.save(any())).thenReturn(user);
        Submitter userSaved = userService.save(user);
        assertNotNull(userSaved);
        verify(submitterRepository).save(any());
        verify(submitterRepository, times(1)).save(any());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
        Long id = Long.valueOf(1L);
        userService.deleteById(id);
        verify(submitterRepository, times(1)).deleteById(anyLong());
    }
}