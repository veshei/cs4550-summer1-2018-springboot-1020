package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.Answer;
import com.example.cs4550summer12018springboot1020.models.Question;
import com.example.cs4550summer12018springboot1020.repositories.AnswerRepository;
import com.example.cs4550summer12018springboot1020.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Backend service for answers.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnswerService {
  @Autowired
  private AnswerRepository answerRepository;

  @Autowired
  private QuestionRepository questionRepository;

  /**
   * Returns all answers in the database.
   * @return all of the answers in the database
   */
  @GetMapping("/api/answer")
  public List<Answer> findAllAnswers() {
    return (List<Answer>) this.answerRepository.findAll();
  }

  /**
   * Returns all of the answers for a particular question id.
   * @param questionId the id of the question to get answers for
   * @return all of the answers for that particular question
   */
  @GetMapping("/api/question/{questionId}/answer")
  public List<Answer> findAnswersForQuestion(@PathVariable("questionId") int questionId) {
    Optional<Question> questionOptional = this.questionRepository.findById(questionId);
    if (questionOptional.isPresent()) {
      Question question = questionOptional.get();
      return question.getAnswers();
    }
    return new ArrayList<Answer>();
  }

  /**
   * Creates a new answer for the question of the given question id.
   * @param questionId the id of the question to post the answer to
   * @param answer the new answer
   * @return the new answer on successful save, null on failure
   */
  @PostMapping("/api/question/{questionId}/answer")
  public Answer createNewAnswer(@PathVariable("questionId") int questionId, @RequestBody Answer answer) {
    Optional<Question> questionOptional = this.questionRepository.findById(questionId);
    if (questionOptional.isPresent()) {
      Question question = questionOptional.get();
      answer.setQuestion(question);
      return this.answerRepository.save(answer);
    }
    return null;
  }

  /**
   * Deletes the answer of the given id.
   * @param answerId the id of the answer to delete
   */
  @DeleteMapping("/api/answer/{answerId}")
  public void deleteAnswerById(@PathVariable("answerId") int answerId) {
    this.answerRepository.deleteById(answerId);
  }

  /**
   * Updates the answer of the given id with the new given answer.
   * @param answerId the id of the answer to update
   * @param updatedAnswer the updated answer
   * @return the updated answer on success, null on failure
   */
  @PutMapping("/api/answer/{answerId}")
  public Answer updateAnswer(@PathVariable("answerId") int answerId, @RequestBody Answer updatedAnswer) {
    Optional<Answer> answerOptional = this.answerRepository.findById(answerId);
    if (answerOptional.isPresent()) {
      Answer answer = answerOptional.get();
      answer.updateAnswer(updatedAnswer);
      return this.answerRepository.save(answer);
    }
    return null;
  }

  /**
   * Returns the answer of the given id, if found
   * @param answerId the id of the answer
   * @return the answer associated with the given id, null if not found
   */
  @GetMapping("/api/answer/{answerId}")
  public Answer findAnswerById(@PathVariable("answerId") int answerId) {
    Optional<Answer> answerOptional = this.answerRepository.findById(answerId);
    if (answerOptional.isPresent()) {
      return answerOptional.get();
    }
    return null;
  }
}
