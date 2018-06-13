package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.CollegeList;
import com.example.cs4550summer12018springboot1020.models.User;
import com.example.cs4550summer12018springboot1020.repositories.CollegeListRepository;
import com.example.cs4550summer12018springboot1020.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CollegeListService {
  @Autowired
  CollegeListRepository collegeListRepository;
  @Autowired
  UserRepository userRepository;

  @DeleteMapping("api/collegeList/{cId}")
  public void deleteCollegeList(@PathVariable("cId") int cId) {
    collegeListRepository.deleteById(cId);
  }

  @PostMapping("api/user/{userId}/collegeList")
  public CollegeList createCollegeList(@PathVariable("userId") int userId,
                                      @RequestBody CollegeList newCollegeList) {
    Optional<User> data = userRepository.findById(userId);
    if (data.isPresent()) {
      User user = data.get();
      newCollegeList.setUser(user);
      return collegeListRepository.save(newCollegeList);
    }
    return null;
  }

  @GetMapping("api/user/{userId}/collegeList")
  public List<CollegeList> findCollegeListForUser(@PathVariable("userId") int userId) {
    Optional<User> data = userRepository.findById(userId);
    if (data.isPresent()) {
      User user = data.get();
      return user.getCollegeLists();
    }
    return null;
  }

  @GetMapping("api/collegeList/{cId}")
  public CollegeList findCollegeListById(@PathVariable("cId") int cId) {
    Optional<CollegeList> data = collegeListRepository.findById(cId);
    if (data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @GetMapping("api/collegeList")
  public List<CollegeList> findAllCollegeLists() {
    return (List<CollegeList>) collegeListRepository.findAll();
  }
}
