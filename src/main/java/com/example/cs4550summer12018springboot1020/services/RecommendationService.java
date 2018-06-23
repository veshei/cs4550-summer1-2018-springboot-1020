package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.CollegeCounselor;
import com.example.cs4550summer12018springboot1020.models.Parent;
import com.example.cs4550summer12018springboot1020.models.Question;
import com.example.cs4550summer12018springboot1020.models.Recommendation;
import com.example.cs4550summer12018springboot1020.models.Student;
import com.example.cs4550summer12018springboot1020.models.User;
import com.example.cs4550summer12018springboot1020.repositories.CollegeCounselorRepository;
import com.example.cs4550summer12018springboot1020.repositories.ParentRepository;
import com.example.cs4550summer12018springboot1020.repositories.RecommendationRepository;
import com.example.cs4550summer12018springboot1020.repositories.StudentRepository;
import com.example.cs4550summer12018springboot1020.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecommendationService {
  @Autowired
  RecommendationRepository recommendationRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  ParentRepository parentRepository;
  @Autowired
  CollegeCounselorRepository collegeCounselorRepository;
  @Autowired
  StudentRepository studentRepository;

  @DeleteMapping("api/recommendation/{rId}")
  public void deleteRecommendation(@PathVariable("rId") int rId) {
    recommendationRepository.deleteById(rId);
  }

  @PostMapping("api/user/recommendation")
  public Recommendation createRecommendation(HttpSession session,
                                    @RequestBody Recommendation newRecommendation) {
    User currentUser = (User) session.getAttribute("currentUser");
    System.out.println(currentUser.getRole());
    if (currentUser.getRole().equals("COLLEGE_COUNSELOR")) {
      newRecommendation.setCollegeCounselor((CollegeCounselor) currentUser);
      return recommendationRepository.save(newRecommendation);
    }
    else if (currentUser.getRole().equals("PARENT")) {
      newRecommendation.setParent((Parent) currentUser);
      return recommendationRepository.save(newRecommendation);
    }
    else {
      throw new IllegalArgumentException();
    }
  }

  @GetMapping("api/student/recommendations")
  public List<Recommendation> findRecommendationsForStudent(HttpSession session) {
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser.getRole().equals("Student")) {
      Integer studentId = currentUser.getId();
      Optional<Student> data = studentRepository.findById(studentId);
      if (data.isPresent()) {
        Student student = data.get();
        return student.getRecommendations();
      }
    }
    else {
      throw new IllegalArgumentException();
    }
    return null;
  }

  @GetMapping("api/recommendation/{rId}")
  public Recommendation findRecommendationById(@PathVariable("rId") int rId) {
    Optional<Recommendation> data = recommendationRepository.findById(rId);
    if (data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @GetMapping("api/recommendation")
  public List<Recommendation> findAllRecommendations() {
    return (List<Recommendation>) recommendationRepository.findAll();
  }
}
