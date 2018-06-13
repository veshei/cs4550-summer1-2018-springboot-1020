package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.CollegeList;
import com.example.cs4550summer12018springboot1020.models.Question;
import com.example.cs4550summer12018springboot1020.models.User;
import com.example.cs4550summer12018springboot1020.repositories.QuestionRepository;
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
public class QuestionService {
  @Autowired
  QuestionRepository questionRepository;
  @Autowired
  UserRepository userRepository;

  @DeleteMapping("api/question/{qId}")
  public void deleteQuestion(@PathVariable("qId") int qId) {
    questionRepository.deleteById(qId);
  }

  @PostMapping("api/user/{userId}/question")
  public Question createQuestion(@PathVariable("userId") int userId,
                                       @RequestBody Question newQuestion) {
    Optional<User> data = userRepository.findById(userId);
    if (data.isPresent()) {
      User user = data.get();
      newQuestion.setUser(user);
      return questionRepository.save(newQuestion);
    }
    return null;
  }

  @GetMapping("api/user/{userId}/question")
  public List<Question> findQuestionForUser(@PathVariable("userId") int userId) {
    Optional<User> data = userRepository.findById(userId);
    if (data.isPresent()) {
      User user = data.get();
      return user.getQuestions();
    }
    return null;
  }

  @GetMapping("api/question/{qId}")
  public Question findQuestionById(@PathVariable("qId") int qId) {
    Optional<Question> data = questionRepository.findById(qId);
    if (data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @GetMapping("api/question")
  public List<Question> findAllQuestions() {
    return (List<Question>) questionRepository.findAll();
  }
}