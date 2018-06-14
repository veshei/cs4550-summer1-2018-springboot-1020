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

import javax.servlet.http.HttpSession;

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

  @PostMapping("api/user/collegeList")
  public CollegeList createCollegeList(@RequestBody CollegeList newCollegeList,
                                       HttpSession session) {
    User currentUser = (User) session.getAttribute("currentUser");
    newCollegeList.setUser(currentUser);
    return collegeListRepository.save(newCollegeList);
  }

  @GetMapping("api/user/collegeList")
  public List<CollegeList> findCollegeListForUser(HttpSession session) {
    User currentUser = (User) session.getAttribute("currentUser");
    return currentUser.getCollegeLists();
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
