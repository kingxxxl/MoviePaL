package com.example.moviepal.repository;

import com.example.moviepal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);

    @Query("SELECT u.id FROM User u where u.username = ?1")
    Optional<Integer> findUseridByUsername(String name);
}
