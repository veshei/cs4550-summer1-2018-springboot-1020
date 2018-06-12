package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Student extends User {
  private int schoolYear;
  @ManyToOne
  @JsonIgnore
  private Parent parent;
  @ManyToOne
  @JsonIgnore
  private CollegeCounselor collegeCounselor;
  @OneToMany(mappedBy="student")
  @JsonIgnore
  private List<Recommendation> recommendations;

  public int getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(int schoolYear) {
    this.schoolYear = schoolYear;
  }

  public Parent getParent1() {
    return parent;
  }

  public void setParent1(Parent parent1) {
    this.parent = parent1;
  }

  public CollegeCounselor getCollegeCounselor() {
    return collegeCounselor;
  }

  public void setCollegeCounselor(CollegeCounselor collegeCounselor) {
    this.collegeCounselor = collegeCounselor;
  }
}
