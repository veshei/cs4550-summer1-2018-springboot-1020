package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.Review;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
  /**
   * Finds all reviews associated with the given college id.
   * @param collegeId the id of the college
   * @return a list of reviews associated with the college id
   */
  @Query("SELECT r FROM Review r WHERE r.collegeId=:collegeId")
  List<Review> findReviewsForCollege(@Param("collegeId") int collegeId);
}
