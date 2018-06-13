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

  @DeleteMapping("api/question/{rId}")
  public void deleteRecommendation(@PathVariable("rId") int rId) {
    recommendationRepository.deleteById(rId);
  }

  @PostMapping("api/collegeCounselor/{ccId}/recommendation")
  public Recommendation createCollegeCounselorRecommendation(@PathVariable("ccId") int ccId,
                                    @RequestBody Recommendation newRecommendation) {
    Optional<CollegeCounselor> data = collegeCounselorRepository.findById(ccId);
    if (data.isPresent()) {
      CollegeCounselor collegeCounselor = data.get();
      newRecommendation.setCollegeCounselor(collegeCounselor);
      return recommendationRepository.save(newRecommendation);
    }
    return null;
  }

  @PostMapping("api/parent/{parentId}/recommendation")
  public Recommendation createParentRecommendation(@PathVariable("parentId") int parentId,
                                                   @RequestBody Recommendation newRecommendation) {
    Optional<Parent> data = parentRepository.findById(parentId);
    if (data.isPresent()) {
      Parent parent = data.get();
      newRecommendation.setParent(parent);
      return recommendationRepository.save(newRecommendation);
    }
    return null;
  }

  @GetMapping("api/student/{studentId}/recommendation")
  public List<Recommendation> findRecommendationsForStudent(@PathVariable("studentId") int studentId) {
    Optional<Student> data = studentRepository.findById(studentId);
    if (data.isPresent()) {
      Student student = data.get();
      return student.getRecommendations();
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
