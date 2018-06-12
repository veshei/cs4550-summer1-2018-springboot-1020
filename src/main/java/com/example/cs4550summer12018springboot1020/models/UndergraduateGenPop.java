package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UndergraduateGenPop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne(mappedBy = "undergraduateGenPop")
  @JsonIgnore
  private StudentDemographics studentDemographics;
  private Integer firstGenPop;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public StudentDemographics getStudentDemographics() {
    return studentDemographics;
  }

  public void setStudentDemographics(StudentDemographics studentDemographics) {
    this.studentDemographics = studentDemographics;
  }

  public Integer getFirstGenPop() {
    return firstGenPop;
  }

  public void setFirstGenPop(Integer firstGenPop) {
    this.firstGenPop = firstGenPop;
  }
}
