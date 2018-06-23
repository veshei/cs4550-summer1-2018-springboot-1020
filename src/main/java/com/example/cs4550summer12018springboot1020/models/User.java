package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private Timestamp dateOfBirth;
  private Roles role;
  @OneToMany(mappedBy="user", cascade= CascadeType.REMOVE)
  private List<CollegeList> collegeLists;
  @OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
  @JsonIgnore
  private List<Review> reviews;
  @OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
  @JsonIgnore
  private List<Question> questions;
  @OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
  @JsonIgnore
  private List<Answer> answers;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Timestamp getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Timestamp dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles roles) {
    this.role = roles;
  }

  public List<CollegeList> getCollegeLists() {
    return collegeLists;
  }

  public void setCollegeLists(List<CollegeList> collegeLists) {
    this.collegeLists = collegeLists;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  /**
   * Updates this user's fields with the given user's fields. Does not override with any fields that are null in the
   * new user object.
   * @param updatedUser the user object that contains the updated information
   */
  public void updateUser(User updatedUser) {
    if (updatedUser.username != null) {
      this.username = updatedUser.username;
    }

    if (updatedUser.password != null) {
      this.password = updatedUser.password;
    }

    if (updatedUser.firstName != null) {
      this.firstName = updatedUser.firstName;
    }

    if (updatedUser.lastName != null) {
      this.lastName = updatedUser.lastName;
    }

    if (updatedUser.dateOfBirth != null) {
      this.dateOfBirth = updatedUser.dateOfBirth;
    }

    if (updatedUser.role != null) {
      this.role = updatedUser.role;
    }

    if (updatedUser.collegeLists != null) {
      this.collegeLists = updatedUser.collegeLists;
    }

    if (updatedUser.reviews != null) {
      this.reviews = updatedUser.reviews;
    }

    if (updatedUser.questions != null) {
      this.questions = updatedUser.questions;
    }
    if (updatedUser.answers != null) {
      this.answers = updatedUser.answers;
    }
  }
}


