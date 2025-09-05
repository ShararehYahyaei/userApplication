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

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepositoy userRepositoy;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void userService_createUser_ShouldReturnUser() {
        User user = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();
        when(userRepositoy.save(Mockito.any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        Assertions.assertThat(createdUser).isNotNull();

    }


    @Test

    public void userService_GetAllUsers_ShouldReturnALlUsers() {

        User firstUser = User.builder()
                .firstName("amir")
                .lastName("soleimany")
                .email("soleimany@gmail.com")
                .password("123456")
                .build();

        User secondUser = User.builder()
                .firstName("ali")
                .lastName("mohammadi")
                .email("mohmmdi@gmail.com")
                .password("12322456")
                .build();
        List<User> fakeUsers = List.of(firstUser, secondUser);
        when(userRepositoy.findAll()).thenReturn(fakeUsers);
        List<User> allUsers = userService.findAllUsers();
        Assertions.assertThat(allUsers).isNotNull();
        Assertions.assertThat(allUsers.size()).isEqualTo(2);
        verify(userRepositoy, times(1)).findAll();

        Assertions.assertThat("amir").isEqualTo(allUsers.get(0).getFirstName());

    }


    @Test
    public void userService_GetUserById_ShouldReturnUser() {
        User user = User.builder()
                .firstName("amir")
                .lastName("soleimany")
                .email("soleimany@gmail.com")
                .password("123456")
                .build();

        when(userRepositoy.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        User createdUser = userService.findUserById(user.getId());
        Assertions.assertThat(createdUser).isNotNull();
        Assertions.assertThat(createdUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(createdUser.getFirstName()).isEqualTo("amir");


    }

    @Test
    public void userService_UpdateUser_ShouldUpdateUser() {
        User user = User.builder()
                .firstName("amir")
                .lastName("soleimany")
                .email("soleimany@gmail.com")
                .password("123456")
                .build();
        when(userRepositoy.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(userRepositoy.save(Mockito.any(User.class))).thenReturn(user);

        user.setFirstName("amirrrr");
        user.setLastName("soleiimany");

        User updatedUser = userService.updateUser(1L, user);
        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat("amirrrr").isEqualTo(updatedUser.getFirstName());
        verify(userRepositoy, times(2)).findById(1L);
        verify(userRepositoy, times(1)).save(any(User.class));

    }

    @Test
    public void userService_DeleteUser_ShouldDeleteUser() {
        User user = User.builder()
                .firstName("amir")
                .lastName("soleimany")
                .email("soleimany@gmail.com")
                .password("123456")
                .build();


        when(userRepositoy.findById(1L)).thenReturn(Optional.ofNullable(user));
        userService.deleteUserById(1L);
        verify(userRepositoy, times(1)).deleteById(1L);

    }
}
