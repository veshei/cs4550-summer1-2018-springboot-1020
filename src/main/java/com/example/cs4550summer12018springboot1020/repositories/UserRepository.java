package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
  @Query("SELECT u FROM User u WHERE u.username=:username")
  Iterable<User> findUserByUsername(
      @Param("username") String username);

  @Query("SELECT u FROM User u WHERE u.username like %:username%")
  List<User> findUsersLikeUsername(@Param("username") String username);
}
