package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.Recommendation;

import org.springframework.data.repository.CrudRepository;

public interface RecommendationRepository extends CrudRepository<Recommendation, Integer> {
}
