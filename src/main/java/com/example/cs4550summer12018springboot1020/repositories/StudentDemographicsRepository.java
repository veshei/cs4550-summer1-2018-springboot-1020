package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.StudentDemographics;

import org.springframework.data.repository.CrudRepository;

public interface StudentDemographicsRepository extends CrudRepository<StudentDemographics, Integer> {
}
