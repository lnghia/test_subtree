package com.example.demo.repositories;

import com.example.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users WHERE username=:username AND is_deleted=false", nativeQuery = true)
    Optional<UserEntity> findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE id=:id AND is_deleted=false", nativeQuery = true)
    Optional<UserEntity> findById(long id);
}
