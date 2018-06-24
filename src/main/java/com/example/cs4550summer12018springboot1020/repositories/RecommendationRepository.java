package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.Recommendation;

import com.example.cs4550summer12018springboot1020.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends CrudRepository<Recommendation, Integer> {
    @Query("SELECT u FROM Recommendation u WHERE u.student.id = :studentId")
    Optional<List<Recommendation>> findByStudentId(
            @Param("studentId") int studentId);
}
