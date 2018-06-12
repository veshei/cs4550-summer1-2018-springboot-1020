package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

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
  @OneToMany(mappedBy="user")
  @JsonIgnore
  private List<CollegeList> colleges;
  @OneToMany(mappedBy="user")
  @JsonIgnore
  private List<Review> reviews;
  @OneToMany(mappedBy="user")
  @JsonIgnore
  private List<Question> questions;
}


