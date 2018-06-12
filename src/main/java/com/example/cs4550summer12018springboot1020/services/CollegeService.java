package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.repositories.CollegeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollegeService {
  @Autowired
  CollegeRepository collegeRepository;
}
