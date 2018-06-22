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
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping("api/collegeList/{cId}")
  public CollegeList updateCollegeList(@PathVariable("cId") int cId,
                                       @RequestBody CollegeList newCollegeList,
                                       HttpSession session) {
    Optional<CollegeList> data = collegeListRepository.findById(cId);
    User currentUser = (User) session.getAttribute("currentUser");
    if(data.isPresent()) {
      CollegeList collegeList = data.get();
      if (collegeList.getName() != null) {
        collegeList.setName(newCollegeList.getName());
      }
      if (newCollegeList.getListOfColleges() != null) {
        for (int i = 0; i < newCollegeList.getListOfColleges().size(); i++) {
          if (collegeList.getListOfColleges() != null) {
            if (!collegeList.getListOfColleges().contains(newCollegeList.getListOfColleges().get(i))) {
              collegeList.getListOfColleges().add(newCollegeList.getListOfColleges().get(i));
              int index = currentUser.getCollegeLists().indexOf(collegeList);
              currentUser.getCollegeLists().get(index).setListOfColleges(collegeList.getListOfColleges());
            }
          }
          else {
            collegeList.setListOfColleges(newCollegeList.getListOfColleges());
            int index = currentUser.getCollegeLists().indexOf(collegeList);
            currentUser.getCollegeLists().get(index).setListOfColleges(collegeList.getListOfColleges());
          }
        }
      }
      collegeListRepository.save(collegeList);
      return collegeList;
    }
    return null;
  }

  @DeleteMapping("api/collegeList/{cId}")
  public void deleteCollegeList(@PathVariable("cId") int cId,
                                HttpSession session) {
    User currentUser = (User) session.getAttribute("currentUser");
    Optional<CollegeList> data = collegeListRepository.findById(cId);
    if (data.isPresent()) {
      CollegeList collegeList = data.get();
      currentUser.getCollegeLists().remove(collegeList);
    }
    collegeListRepository.deleteById(cId);
  }

  @PostMapping("api/user/collegeList")
  public CollegeList createCollegeList(@RequestBody CollegeList newCollegeList,
                                       HttpSession session) {
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser != null) {
      newCollegeList.setUser(currentUser);
      currentUser.getCollegeLists().add(newCollegeList);
      return collegeListRepository.save(newCollegeList);
    }
    return null;
  }

  @GetMapping("api/user/collegeList")
  public List<CollegeList> findCollegeListForUser(HttpSession session) {
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser != null) {
      return currentUser.getCollegeLists();
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
