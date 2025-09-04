package org.example.userapplication.repository;


import org.example.userapplication.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UseRepositoryTest {

    @Autowired
    private UserRepositoy userRepository;


    @Test
    public void userRepository_SaveUser_ShouldSaveUser() {
        User user = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();

        User userSave = userRepository.save(user);
        Assertions.assertThat(userSave).isNotNull();           // object is saved
        Assertions.assertThat(userSave.getId()).isGreaterThan(0); // id is generated
        Assertions.assertThat(userSave.getFirstName()).isEqualTo("ali"); // fields are correct
    }


    @Test
    public void userRepository_GetAllUsers_ShouldReturnALlUsers() {
        User firstUser = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();
        User secondUser = User.builder()
                .firstName("mohammad")
                .lastName("chenniii")
                .email("chennnn@gmail.com")
                .password("25632")
                .build();
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.size()).isEqualTo(2);
    }


    @Test
    public void userRepository_GetById_ShouldReturnUser() {
        User user = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();

        User userSave = userRepository.save(user);
        userRepository.findById(userSave.getId());
        Assertions.assertThat(userSave).isNotNull();
        Assertions.assertThat(userSave.getId()).isEqualTo(user.getId());
    }


    @Test

    public void userRepository_FIndByEmail_ShouldReturnUser() {
        User user = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();

        User userSave = userRepository.save(user);
        Assertions.assertThat(userSave).isNotNull();
        Assertions.assertThat(userSave.getId()).isGreaterThan(0);
        Assertions.assertThat(userSave.getEmail()).isEqualTo("chen@gmail.com");

    }

    @Test


    public void userRepository_UpdateUser_ShouldReturnUser() {
        User user = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();
        User userSave = userRepository.save(user);
        User userNew = userRepository.findById(userSave.getId()).get();
        userNew.setFirstName("mohammad");
        userNew.setLastName("chenniii");
        userNew.setEmail("chennnn@gmail.com");
        userNew.setPassword("25632");
        User save = userRepository.save(userNew);
        Assertions.assertThat(save.getFirstName()).isNotNull();
        Assertions.assertThat(save.getLastName()).isNotNull();
        Assertions.assertThat(save.getEmail()).isNotNull();
        Assertions.assertThat(save.getFirstName()).isEqualTo("mohammad");

    }
}



