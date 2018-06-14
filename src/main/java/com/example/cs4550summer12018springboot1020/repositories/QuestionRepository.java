package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.Question;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

  /**
   * Returns all questions for the given college id.
   * @param collegeId the id of the college
   * @return the list of all questions for the given college
   */
  @Query("SELECT q FROM Question q WHERE q.collegeId=:collegeId")
  List<Question> findQuestionsForCollege(@Param("collegeId") int collegeId);
}
