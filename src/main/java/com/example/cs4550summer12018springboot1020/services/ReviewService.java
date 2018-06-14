package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.Review;
import com.example.cs4550summer12018springboot1020.models.User;
import com.example.cs4550summer12018springboot1020.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewService {
  @Autowired
  ReviewRepository reviewRepository;

  /**
   * Finds all reviews for the college of the given id.
   * @param collegeId the id of the college
   * @return a list of college reviews associated with the college id
   */
  @GetMapping("/api/review/college/{collegeId}")
  public List<Review> findReviewsForCollege(@PathVariable("collegeId") int collegeId) {
    return this.reviewRepository.findReviewsForCollege(collegeId);
  }

  /**
   * Creates a new review with the creator of the review being the currently logged in user.
   * @param review the new review
   * @return the review created on success, null on failure
   */
  @PostMapping("/api/review")
  public Review createReview(HttpSession session, @RequestBody Review review) {
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser != null) {
      review.setUser(currentUser);
      this.reviewRepository.save(review);
    }
    return null;
  }
}
