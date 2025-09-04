package org.example.userapplication.service;


import org.assertj.core.api.Assertions;
import org.example.userapplication.model.User;
import org.example.userapplication.repository.UserRepositoy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepositoy userRepositoy;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void userService_saveUser_ShouldReturnUser() {
        User user = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();
        Mockito.when(userRepositoy.save(Mockito.any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        Assertions.assertThat(createdUser).isNotNull();


    }


}
