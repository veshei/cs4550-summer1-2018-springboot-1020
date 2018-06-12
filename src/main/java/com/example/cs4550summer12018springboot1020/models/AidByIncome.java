package com.example.cs4550summer12018springboot1020.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AidByIncome {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @OneToOne(mappedBy = "aidByIncome")
  @JsonIgnore
  private StudentDemographics studentDemographics;
  private Integer thirtyThousandAid;
  private Integer seventyFiveThousandAid;
  private Integer greaterThanSeventyFiveAid;
}
