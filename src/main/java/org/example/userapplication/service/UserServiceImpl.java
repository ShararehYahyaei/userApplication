package org.example.userapplication.service;


import org.example.userapplication.dto.UserDto;
import org.example.userapplication.model.User;
import org.example.userapplication.repository.UserRepositoy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepositoy userRepositoy;


    public UserServiceImpl(UserRepositoy userRepositoy) {
        this.userRepositoy = userRepositoy;
    }

    @Override
    public User createUser(User user) {
        return userRepositoy.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepositoy.findByEmail(email);
    }


    @Override
    public User findUserById(Long id) {
        return userRepositoy.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @Override
    public String deleteUserById(Long id) {
        if (userRepositoy.findById(id).isPresent()) {
            userRepositoy.deleteById(id);
            return "User Deleted Successfully";
        }
        return "User Not Found";

    }

    @Override
    public User updateUser(Long id, UserDto user) {

        if (userRepositoy.findById(id).isPresent()) {
            User userToUpdate = userRepositoy.findById(id).get();
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            return userRepositoy.save(userToUpdate);
        }

        throw new RuntimeException("User Not Found");

    }

    @Override
    public List<User> findAllUsers() {
        return userRepositoy.findAll();
    }
}
