package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class CollegeCounselor extends User {
  @OneToMany(mappedBy="collegeCounselor")
  @JsonIgnore
  private List<Student> students;
  @ManyToMany(mappedBy = "collegeCounselors")
  @JsonIgnore
  private List<Parent> parents;
  @OneToMany(mappedBy="collegeCounselor")
  @JsonIgnore
  private List<Recommendation> recommendations;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  public List<Parent> getParents() {
    return parents;
  }

  public void setParents(List<Parent> parents) {
    this.parents = parents;
  }

  public List<Recommendation> getRecommendations() {
    return recommendations;
  }

  public void setRecommendations(List<Recommendation> recommendations) {
    this.recommendations = recommendations;
  }
}
