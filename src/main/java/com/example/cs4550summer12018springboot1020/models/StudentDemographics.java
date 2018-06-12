package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class StudentDemographics {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private Integer year;
  @OneToOne(mappedBy = "studentDemographics")
  @JsonIgnore
  private College college;
  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private UndergraduateStatusPop undergradPop;
  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private UndergraduateRacePop undergradRacePop;
  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private AidByIncome aidByIncome;
  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private UndergraduateGenPop undergraduateGenPop;
  private Integer fafsaSubmissions;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public College getCollege() {
    return college;
  }

  public void setCollege(College college) {
    this.college = college;
  }

  public UndergraduateStatusPop getUndergradPop() {
    return undergradPop;
  }

  public void setUndergradPop(UndergraduateStatusPop undergradPop) {
    this.undergradPop = undergradPop;
  }

  public UndergraduateRacePop getUndergradRacePop() {
    return undergradRacePop;
  }

  public void setUndergradRacePop(UndergraduateRacePop undergradRacePop) {
    this.undergradRacePop = undergradRacePop;
  }

  public AidByIncome getAidByIncome() {
    return aidByIncome;
  }

  public void setAidByIncome(AidByIncome aidByIncome) {
    this.aidByIncome = aidByIncome;
  }

  public UndergraduateGenPop getUndergraduateGenPop() {
    return undergraduateGenPop;
  }

  public void setUndergraduateGenPop(UndergraduateGenPop undergraduateGenPop) {
    this.undergraduateGenPop = undergraduateGenPop;
  }

  public Integer getFafsaSubmissions() {
    return fafsaSubmissions;
  }

  public void setFafsaSubmissions(Integer fafsaSubmissions) {
    this.fafsaSubmissions = fafsaSubmissions;
  }
}
