package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Represents an answer to a question.
 */
@Entity
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private String answer;
  @ManyToOne
  private User user;

  @ManyToOne
  @JsonIgnore
  private Question question;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
  /**
   * Updates the fields of this answer with the non-null fields of the given answer except for id.
   * @param updatedAnswer the new answer that contains the updated information.
   */
  public void updateAnswer(Answer updatedAnswer) {
    if (updatedAnswer.title != null) {
      this.title = updatedAnswer.title;
    }

    if (updatedAnswer.answer != null) {
      this.answer = updatedAnswer.answer;
    }

    if (updatedAnswer.question != null) {
      this.question = updatedAnswer.question;
    }

    if (updatedAnswer.user != null) {
      this.user = updatedAnswer.user;
    }
  }
}
