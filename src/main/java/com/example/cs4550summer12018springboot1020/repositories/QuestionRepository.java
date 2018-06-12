package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.Question;

import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
