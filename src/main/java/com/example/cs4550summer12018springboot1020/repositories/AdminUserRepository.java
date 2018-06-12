package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.AdminUser;

import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<AdminUser, Integer> {
}
