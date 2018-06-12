package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UndergraduateRacePop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne(mappedBy = "undergraduateRacePop")
  @JsonIgnore
  private StudentDemographics studentDemographics;
  private Integer whitePop;
  private Integer asianPop;
  private Integer nonResidentPop;
  private Integer nphiPop;
  private Integer hispanicPop;
  private Integer mixedPop;
  private Integer blackPop;
  private Integer unknownPop;

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

  public Integer getWhitePop() {
    return whitePop;
  }

  public void setWhitePop(Integer whitePop) {
    this.whitePop = whitePop;
  }

  public Integer getAsianPop() {
    return asianPop;
  }

  public void setAsianPop(Integer asianPop) {
    this.asianPop = asianPop;
  }

  public Integer getNonResidentPop() {
    return nonResidentPop;
  }

  public void setNonResidentPop(Integer nonResidentPop) {
    this.nonResidentPop = nonResidentPop;
  }

  public Integer getNphiPop() {
    return nphiPop;
  }

  public void setNphiPop(Integer nphiPop) {
    this.nphiPop = nphiPop;
  }

  public Integer getHispanicPop() {
    return hispanicPop;
  }

  public void setHispanicPop(Integer hispanicPop) {
    this.hispanicPop = hispanicPop;
  }

  public Integer getMixedPop() {
    return mixedPop;
  }

  public void setMixedPop(Integer mixedPop) {
    this.mixedPop = mixedPop;
  }

  public Integer getBlackPop() {
    return blackPop;
  }

  public void setBlackPop(Integer blackPop) {
    this.blackPop = blackPop;
  }

  public Integer getUnknownPop() {
    return unknownPop;
  }

  public void setUnknownPop(Integer unknownPop) {
    this.unknownPop = unknownPop;
  }
}
