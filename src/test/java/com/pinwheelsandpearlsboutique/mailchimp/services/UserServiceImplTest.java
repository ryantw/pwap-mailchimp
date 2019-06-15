package com.pinwheelsandpearlsboutique.mailchimp.services;

import com.pinwheelsandpearlsboutique.mailchimp.models.PWAPUser;
import com.pinwheelsandpearlsboutique.mailchimp.repositories.UserRepository;
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

    PWAPUser user;

    @BeforeEach
    void setUp() {
        user = PWAPUser.builder().id(1L).firstName("Test")
                .lastName("Last").emailAddress("r@r.com")
                .build();
    }

    @Test
    void findAll() {
        Set<PWAPUser> users = new HashSet<>();
        users.add(PWAPUser.builder().id(1L).firstName("One").build());
        users.add(PWAPUser.builder().id(2L).firstName("Two").build());
        when(userRepository.findAll()).thenReturn(users);

        Set<PWAPUser> returnedUsers = userService.findAll();
        assertNotNull(returnedUsers);
        assertEquals(2, returnedUsers.size());
    }

    @Test
    void findById() {
        Optional<PWAPUser> u = Optional.of(user);
        when(userRepository.findById(anyLong())).thenReturn(u);
        PWAPUser returnedUser = userService.findById(1L);
        assertNotNull(returnedUser);
        verify(userRepository, times(1)).findById(anyLong());
        assertEquals(Long.valueOf(1L), returnedUser.getId());
    }

    @Test
    void save() {
        when(userRepository.save(any())).thenReturn(user);
        PWAPUser userSaved = userService.save(user);
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