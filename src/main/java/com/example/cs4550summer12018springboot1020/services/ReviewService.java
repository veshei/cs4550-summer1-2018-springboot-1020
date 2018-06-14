package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.Question;
import com.example.cs4550summer12018springboot1020.models.Review;
import com.example.cs4550summer12018springboot1020.models.User;
import com.example.cs4550summer12018springboot1020.repositories.ReviewRepository;
import com.example.cs4550summer12018springboot1020.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
  public Review createReviewForLoggedInUser(HttpSession session, @RequestBody Review review) {
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser != null) {
      review.setUser(currentUser);
      this.reviewRepository.save(review);
      return review;
    }
    return null;
  }

  @Autowired
  UserRepository userRepository;

  @DeleteMapping("api/review/{reviewId}")
  public void deleteReview(@PathVariable("reviewId") int reviewId) {
    reviewRepository.deleteById(reviewId);
  }

  @PostMapping("api/user/{userId}/review")
  public Review createReview(@PathVariable("userId") int userId,
                                 @RequestBody Review newReview) {
    Optional<User> data = userRepository.findById(userId);
    if (data.isPresent()) {
      User user = data.get();
      newReview.setUser(user);
      return reviewRepository.save(newReview);
    }
    return null;
  }

  @GetMapping("api/user/{userId}/review")
  public List<Review> findReviewForUser(@PathVariable("userId") int userId) {
    Optional<User> data = userRepository.findById(userId);
    if (data.isPresent()) {
      User user = data.get();
      return user.getReviews();
    }
    return null;
  }

  @GetMapping("api/review/{reviewId}")
  public Review findReviewById(@PathVariable("reviewId") int reviewId) {
    Optional<Review> data = reviewRepository.findById(reviewId);
    if (data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @GetMapping("api/review")
  public List<Review> findAllReview() {
    return (List<Review>) reviewRepository.findAll();
  }
}
