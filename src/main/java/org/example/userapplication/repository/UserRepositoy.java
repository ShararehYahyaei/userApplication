package org.example.userapplication.repository;

import org.example.userapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoy extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
