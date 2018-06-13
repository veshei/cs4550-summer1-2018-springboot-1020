package com.example.cs4550summer12018springboot1020.models;

public enum Roles {
  STUDENT("Student"), PARENT("Parent"), COLLEGE_COUNSELOR("College Counselor"), ADMIN("Admin");

  private String value;

  Roles(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
