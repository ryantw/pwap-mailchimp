package io.lker.mailchimp.services;

import io.lker.mailchimp.models.MCSubscriber;
import io.lker.mailchimp.repositories.UserRepository;
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
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    MCSubscriber user;

    @BeforeEach
    void setUp() {
        user = MCSubscriber.builder().id(1L).firstName("Test")
                .lastName("Last").emailAddress("r@r.com")
                .build();
    }

    @Test
    void findAll() {
        Set<MCSubscriber> users = new HashSet<>();
        users.add(MCSubscriber.builder().id(1L).firstName("One").build());
        users.add(MCSubscriber.builder().id(2L).firstName("Two").build());
        when(userRepository.findAll()).thenReturn(users);

        Set<MCSubscriber> returnedUsers = userService.findAll();
        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
    }

    @Test
    void findById() {
        Optional<MCSubscriber> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);
        MCSubscriber returnedUser = userService.findById(1L);
        assertNotNull(returnedUser);
        verify(userRepository, times(1)).findById(anyLong());
        assertEquals(Long.valueOf(1L), returnedUser.getId());
    }

    @Test
    void save() {
        when(userRepository.save(any())).thenReturn(user);
        MCSubscriber userSaved = userService.save(user);
        assertNotNull(userSaved);
        verify(userRepository).save(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
        Long id = Long.valueOf(1L);
        userService.deleteById(id);
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}