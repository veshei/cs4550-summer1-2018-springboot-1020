package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Parent extends User {
  @OneToMany(mappedBy="parent")
  @JsonIgnore
  private List<Student> students;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  @JsonIgnore
  private List<CollegeCounselor> collegeCounselors;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public List<CollegeCounselor> getCollegeCounselors() {
    return collegeCounselors;
  }

  public void setCollegeCounselors(List<CollegeCounselor> collegeCounselors) {
    this.collegeCounselors = collegeCounselors;
  }
}
