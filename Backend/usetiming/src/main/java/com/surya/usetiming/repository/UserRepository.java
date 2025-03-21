package com.surya.usetiming.repository;

import com.surya.usetiming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
    List<User> findAll();
}
