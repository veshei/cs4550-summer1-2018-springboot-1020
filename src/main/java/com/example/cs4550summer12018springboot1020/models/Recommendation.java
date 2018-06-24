package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Recommendation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private Integer collegeId;
  @ManyToOne
  private Student student;

  @ManyToOne
  private User recommender;
  private String description;


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

  public Integer getCollegeId() {
    return collegeId;
  }

  public void setCollegeId(Integer collegeId) {
    this.collegeId = collegeId;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public User getRecommender() {
    return recommender;
  }

  public void setRecommender(User recommender) {
    this.recommender = recommender;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
