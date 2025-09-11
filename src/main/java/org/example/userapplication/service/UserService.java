package org.example.userapplication.service;

import org.example.userapplication.dto.UserDto;
import org.example.userapplication.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User findUserByEmail(String email);
    User findUserById(Long id);
    String deleteUserById(Long id);
    User updateUser(Long id, UserDto user);
    List<User> findAllUsers();

}
