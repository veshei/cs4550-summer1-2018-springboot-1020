package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UndergraduateStatusPop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Integer fullTimePop;
  private Integer partTimePop;
  @OneToOne(mappedBy = "undergraduateStatusPop")
  @JsonIgnore
  private StudentDemographics studentDemographics;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getFullTimePop() {
    return fullTimePop;
  }

  public void setFullTimePop(Integer fullTimePop) {
    this.fullTimePop = fullTimePop;
  }

  public Integer getPartTimePop() {
    return partTimePop;
  }

  public void setPartTimePop(Integer partTimePop) {
    this.partTimePop = partTimePop;
  }

  public StudentDemographics getStudentDemographics() {
    return studentDemographics;
  }

  public void setStudentDemographics(StudentDemographics studentDemographics) {
    this.studentDemographics = studentDemographics;
  }
}
