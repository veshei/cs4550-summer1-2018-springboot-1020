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
  private College college;
  @ManyToOne
  @JsonIgnore
  private Student student;
  @ManyToOne
  @JsonIgnore
  private Parent parent;
  @ManyToOne
  @JsonIgnore
  private CollegeCounselor collegeCounselor;
  private String description;
  @OneToMany(mappedBy="parent")
  @JsonIgnore
  private List<Recommendation> writtenRecommendations;

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

  public College getCollege() {
    return college;
  }

  public void setCollege(College college) {
    this.college = college;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Parent getParent() {
    return parent;
  }

  public void setParent(Parent parent) {
    this.parent = parent;
  }

  public CollegeCounselor getCollegeCounselor() {
    return collegeCounselor;
  }

  public void setCollegeCounselor(CollegeCounselor collegeCounselor) {
    this.collegeCounselor = collegeCounselor;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Recommendation> getWrittenRecommendations() {
    return writtenRecommendations;
  }

  public void setWrittenRecommendations(List<Recommendation> writtenRecommendations) {
    this.writtenRecommendations = writtenRecommendations;
  }
}
