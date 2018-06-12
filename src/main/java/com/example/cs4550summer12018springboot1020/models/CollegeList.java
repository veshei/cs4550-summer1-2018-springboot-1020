package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CollegeList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  @ManyToOne
  @JsonIgnore
  private User user;
  @ManyToMany(mappedBy = "collegeLists")
  @JsonIgnore
  private List<College> listOfColleges;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<College> getListOfColleges() {
    return listOfColleges;
  }

  public void setListOfColleges(List<College> listOfColleges) {
    this.listOfColleges = listOfColleges;
  }
}
