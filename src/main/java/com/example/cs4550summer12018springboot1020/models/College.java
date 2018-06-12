package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class College {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String operatingStatus;
  private String nonProfitStatus;
  private DegreeType degreeTypes;
  @OneToMany(mappedBy="college")
  @JsonIgnore
  private List<ProgramTypes> programTypes;
  private String religiousAffiliation;
  private String acceptanceRate;
  private Integer schoolRevenue;
  private Integer avgSATScore;
  private Integer avgACTScore;
  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private StudentDemographics studentDemographics;
  private Integer tuition;
  private Integer avgCost;
  private Integer studentFederalLoans;
  private Integer studentDebt;
  private Integer studentPellGrants;
  private Integer firstYearCompletion;
  private Integer graduationRate;
  private Integer avgPostEarnings;
  @OneToMany(mappedBy="college")
  @JsonIgnore
  private List<Review> listOfReviews;
  @OneToMany(mappedBy="college")
  @JsonIgnore
  private List<Recommendation> listOfRecommendations;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable
  @JsonIgnore
  private List<CollegeList> collegeLists;
  @OneToMany(mappedBy="college")
  @JsonIgnore
  private List<Question> listOfQuestions;

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

  public String getOperatingStatus() {
    return operatingStatus;
  }

  public void setOperatingStatus(String operatingStatus) {
    this.operatingStatus = operatingStatus;
  }

  public String getNonProfitStatus() {
    return nonProfitStatus;
  }

  public void setNonProfitStatus(String nonProfitStatus) {
    this.nonProfitStatus = nonProfitStatus;
  }

  public DegreeType getDegreeTypes() {
    return degreeTypes;
  }

  public void setDegreeTypes(DegreeType degreeTypes) {
    this.degreeTypes = degreeTypes;
  }

  public List<ProgramTypes> getProgramTypes() {
    return programTypes;
  }

  public void setProgramTypes(List<ProgramTypes> programTypes) {
    this.programTypes = programTypes;
  }

  public String getReligiousAffiliation() {
    return religiousAffiliation;
  }

  public void setReligiousAffiliation(String religiousAffiliation) {
    this.religiousAffiliation = religiousAffiliation;
  }

  public String getAcceptanceRate() {
    return acceptanceRate;
  }

  public void setAcceptanceRate(String acceptanceRate) {
    this.acceptanceRate = acceptanceRate;
  }

  public Integer getSchoolRevenue() {
    return schoolRevenue;
  }

  public void setSchoolRevenue(Integer schoolRevenue) {
    this.schoolRevenue = schoolRevenue;
  }

  public Integer getAvgSATScore() {
    return avgSATScore;
  }

  public void setAvgSATScore(Integer avgSATScore) {
    this.avgSATScore = avgSATScore;
  }

  public Integer getAvgACTScore() {
    return avgACTScore;
  }

  public void setAvgACTScore(Integer avgACTScore) {
    this.avgACTScore = avgACTScore;
  }

  public StudentDemographics getStudentDemographics() {
    return studentDemographics;
  }

  public void setStudentDemographics(StudentDemographics studentDemographics) {
    this.studentDemographics = studentDemographics;
  }

  public Integer getTuition() {
    return tuition;
  }

  public void setTuition(Integer tuition) {
    this.tuition = tuition;
  }

  public Integer getAvgCost() {
    return avgCost;
  }

  public void setAvgCost(Integer avgCost) {
    this.avgCost = avgCost;
  }

  public Integer getStudentFederalLoans() {
    return studentFederalLoans;
  }

  public void setStudentFederalLoans(Integer studentFederalLoans) {
    this.studentFederalLoans = studentFederalLoans;
  }

  public Integer getStudentDebt() {
    return studentDebt;
  }

  public void setStudentDebt(Integer studentDebt) {
    this.studentDebt = studentDebt;
  }

  public Integer getStudentPellGrants() {
    return studentPellGrants;
  }

  public void setStudentPellGrants(Integer studentPellGrants) {
    this.studentPellGrants = studentPellGrants;
  }

  public Integer getFirstYearCompletion() {
    return firstYearCompletion;
  }

  public void setFirstYearCompletion(Integer firstYearCompletion) {
    this.firstYearCompletion = firstYearCompletion;
  }

  public Integer getGraduationRate() {
    return graduationRate;
  }

  public void setGraduationRate(Integer graduationRate) {
    this.graduationRate = graduationRate;
  }

  public Integer getAvgPostEarnings() {
    return avgPostEarnings;
  }

  public void setAvgPostEarnings(Integer avgPostEarnings) {
    this.avgPostEarnings = avgPostEarnings;
  }

  public List<Review> getListOfReviews() {
    return listOfReviews;
  }

  public void setListOfReviews(List<Review> listOfReviews) {
    this.listOfReviews = listOfReviews;
  }

  public List<Recommendation> getListOfRecommendations() {
    return listOfRecommendations;
  }

  public void setListOfRecommendations(List<Recommendation> listOfRecommendations) {
    this.listOfRecommendations = listOfRecommendations;
  }

  public List<CollegeList> getCollegeLists() {
    return collegeLists;
  }

  public void setCollegeLists(List<CollegeList> collegeLists) {
    this.collegeLists = collegeLists;
  }

  public List<Question> getListOfQuestions() {
    return listOfQuestions;
  }

  public void setListOfQuestions(List<Question> listOfQuestions) {
    this.listOfQuestions = listOfQuestions;
  }
}
