package org.example.userapplication.repository;


import org.example.userapplication.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UseRepositoryTest {

    @Autowired
    private UserRepositoy userRepsitory;


    @Test
    public void userRepository_SaveUser_ShouldSaveUser() {
        User user = User.builder()
                .firstName("ali")
                .lastName("chen")
                .email("chen@gmail.com")
                .password("123456")
                .build();

        User userSave = userRepsitory.save(user);
        Assertions.assertThat(userSave).isNotNull();           // object is saved
        Assertions.assertThat(userSave.getId()).isGreaterThan(0); // id is generated
        Assertions.assertThat(userSave.getFirstName()).isEqualTo("ali"); // fields are correct
    }


}



