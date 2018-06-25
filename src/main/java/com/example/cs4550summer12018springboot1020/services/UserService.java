package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.*;
import com.example.cs4550summer12018springboot1020.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://cs4550-summer1-2018-react-1020.herokuapp.com/"},
        maxAge = 3600)
public class UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  ParentRepository parentRepository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  CollegeCounselorRepository collegeCounselorRepository;

  @Autowired
  AdminUserRepository adminUserRepository;

  @PostMapping("/api/login")
  public User login(@RequestBody User credentials, HttpSession session, HttpServletResponse response) {
    List<User> userList = (List<User>) this.userRepository.findUserByUsername(credentials.getUsername());
    if (!userList.isEmpty()) {
      User user = userList.get(0);
      if (user.getPassword().equals(credentials.getPassword())) {
        session.setAttribute("currentUser", user);
        return user;
      }
    }
    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    return null;
  }

  @PostMapping("api/logout")
  public void logout (HttpSession session) {
    session.invalidate();
  }

  @GetMapping("/api/profile")
  public User profile(HttpSession session) {
    User currentUser = (User) session.getAttribute("currentUser");
    return currentUser;
  }

  @PutMapping("/api/profile")
  public User updateProfile(HttpSession session, @RequestBody User updatedUser) {
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser != null) {
      currentUser.updateUser(updatedUser);
      this.userRepository.save(currentUser);
      session.setAttribute("currentUser", currentUser);
      return updatedUser;
    }
    return null;
  }

  @PostMapping("/api/register/student")
  public User registerStudent(@RequestBody Student user, HttpSession session) {
    List<User> users = (List<User>) this.userRepository.findUserByUsername(user.getUsername());
    if (users.size() == 0) {
      session.setAttribute("currentUser", user);
      user.setRecommendations(new ArrayList<Recommendation>());

      return studentRepository.save(user);
    }
    else {
      return null;
    }
  }

  @PostMapping("/api/register/parent")
  public User registerParent(@RequestBody Parent user, HttpSession session) {
    List<User> users = (List<User>) this.userRepository.findUserByUsername(user.getUsername());
    if (users.size() == 0) {
      session.setAttribute("currentUser", user);

      return parentRepository.save(user);
    }
    else {
      return null;
    }
  }

  @PostMapping("/api/register/college_counselor")
  public User registerCollegeCounselor(@RequestBody CollegeCounselor user, HttpSession session) {
    List<User> users = (List<User>) this.userRepository.findUserByUsername(user.getUsername());
    if (users.size() == 0) {
      session.setAttribute("currentUser", user);

      return collegeCounselorRepository.save(user);
    }
    else {
      return null;
    }
  }

  @PostMapping("/api/register/admin")
  public User registerAdmin(@RequestBody AdminUser user, HttpSession session) {
    List<User> users = (List<User>) this.userRepository.findUserByUsername(user.getUsername());
    if (users.size() == 0) {
      session.setAttribute("currentUser", user);
      return adminUserRepository.save(user);
    } else {
      return null;
    }
  }



  @GetMapping("/api/username/{username}")
  public User findUserByUsername(@PathVariable("username") String username, HttpServletResponse response ) {
    List<User> users = (List<User>) userRepository.findUserByUsername(username);
    if (users.size() > 0) {
      return users.get(0);
    }
    else {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return null;
    }
  }

  @DeleteMapping("/api/user/{userId}")
  public void deleteUser(@PathVariable("userId") int id) {
    userRepository.deleteById(id);
  }

  @PostMapping("/api/user")
  public User createUser(@RequestBody User user) {
    if(user.getRole() == Roles.STUDENT) {
      Student student = (Student) user;
      student.setRecommendations(new ArrayList<Recommendation>());

      return studentRepository.save((Student) user);

    }
    else if(user.getRole() == Roles.PARENT) {
      return parentRepository.save((Parent) user);
    }
    else if(user.getRole() == Roles.COLLEGE_COUNSELOR){
      return collegeCounselorRepository.save((CollegeCounselor) user);
    }
    return null;
  }

  @PutMapping("api/user/{userId}")
  public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
    Optional<User> data = userRepository.findById(userId);
    if(data.isPresent()) {
      User user = data.get();
      user.updateUser(newUser);
      userRepository.save(user);
      return user;
    }
    return null;
  }

  @GetMapping("/api/user/{userId}")
  public User findUserByid(@PathVariable("userId") int userId) {
    Optional<User> data = userRepository.findById(userId);
    if (data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @GetMapping("/api/user")
  public List<User> findAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @GetMapping("/api/user/{username}/similar")
  public List<User> findUsersLikeUsername(@PathVariable("username") String username) {
    return this.userRepository.findUsersLikeUsername(username);
  }

  // Returns the parent of the given student id
  @GetMapping("/api/student/{userId}/parent")
  public Parent findParentForStudent(@PathVariable("userId") int userId) {
    Optional<Parent> parentOptional = this.studentRepository.findParentForStudent(userId);
    if (parentOptional.isPresent()) {
      return parentOptional.get();
    }
    return null;
  }

  // Returns the counselor of the given student id
  @GetMapping("/api/student/{userId}/counselor")
  public CollegeCounselor findCounselorForStudent(@PathVariable("userId") int userId) {
    Optional<CollegeCounselor> counselorOptional = this.studentRepository.findCounselorForStudent(userId);
    if (counselorOptional.isPresent()) {
      return counselorOptional.get();
    }
    return null;
  }

  // Returns the list of counselors associated with the parent of the given id
  @GetMapping("/api/parent/{userId}/counselor")
  public List<CollegeCounselor> findCounselorsForParent(@PathVariable("userId") int userId) {
    return this.parentRepository.findCounselorsForParent(userId);
  }

  // Creates a relation between the given student and parent, if possible
  @PostMapping("/api/student/{studentId}/parent/{parentId}")
  public void createStudentParentRelation(@PathVariable("studentId") int studentId,
                                                  @PathVariable("parentId") int parentId,
                                                  HttpServletResponse response) throws IOException {
    Optional<Student> optionalStudent = this.studentRepository.findById(studentId);
    Optional<Parent> optionalParent = this.parentRepository.findById(parentId);
    if (optionalStudent.isPresent() && optionalParent.isPresent()) {
      Student student = optionalStudent.get();
      Parent parent = optionalParent.get();
      // Check if the student currently doesn't have a parent
      if (student.getParent() == null) {
        student.setParent(parent);
        this.studentRepository.save(student);
        response.setStatus(200);
      } else {
        response.sendError(400, "Student already has a parent");
      }
    } else {
      response.sendError(400, "Could not find student and/or parent");
    }
  }

  // Creates a relation between the given student and counselor, if possible
  @PostMapping("/api/student/{studentId}/counselor/{counselorId}")
  public void createStudentCounselorRelation(@PathVariable("studentId") int studentId,
                                             @PathVariable("counselorId") int counselorId,
                                             HttpServletResponse response) throws IOException {
    Optional<Student> optionalStudent = this.studentRepository.findById(studentId);
    Optional<CollegeCounselor> optionalCounselor = this.collegeCounselorRepository.findById(counselorId);

    if (optionalStudent.isPresent() && optionalCounselor.isPresent()) {
      Student student = optionalStudent.get();
      CollegeCounselor counselor = optionalCounselor.get();
      if (student.getCollegeCounselor() == null) {
        student.setCollegeCounselor(counselor);
        this.studentRepository.save(student);
        response.setStatus(200);
      } else {
        response.sendError(400, "Student already has a counselor");
      }
    } else {
      response.sendError(400, "Could not find resulting users");
    }
  }

  // Creates a relation between the given parent and counselor, if possible
  @PostMapping("/api/parent/{parentId}/counselor/{counselorId}")
  public void createParentCounselorRelation(@PathVariable("parentId") int parentId,
                                            @PathVariable("counselorId") int counselorId,
                                            HttpServletResponse response) throws IOException {
    Optional<Parent> optionalParent = this.parentRepository.findById(parentId);
    Optional<CollegeCounselor> optionalCounselor = this.collegeCounselorRepository.findById(counselorId);
    if (optionalParent.isPresent() && optionalCounselor.isPresent()) {
      Parent parent = optionalParent.get();
      CollegeCounselor counselor = optionalCounselor.get();
      // Make sure the parent doesn't currently have the counselor in the list before adding
      if (!parent.getCollegeCounselors().contains(counselor)) {
        List<CollegeCounselor> currentCounselors = parent.getCollegeCounselors();
        currentCounselors.add(counselor);
        parent.setCollegeCounselors(currentCounselors);
        this.parentRepository.save(parent);
        response.setStatus(200);
      } else {
        response.sendError(400, "Parent/counselor relation already exists");
      }
    } else {
      response.sendError(400, "Could not find parent or counselor");
    }
  }

  @DeleteMapping("/api/student/{studentId}/parent/{parentId}")
  public void deleteStudentParentRelation(@PathVariable("studentId") int studentId,
                                          @PathVariable("parentId") int parentId,
                                          HttpServletResponse response) throws IOException {
    Optional<Student> optionalStudent = this.studentRepository.findById(studentId);
    Optional<Parent> optionalParent = this.parentRepository.findById(parentId);

    if (optionalStudent.isPresent() && optionalParent.isPresent()) {
      Student student = optionalStudent.get();
      Parent parent = optionalParent.get();

      if (student.getParent().getId() == parent.getId()) {
        student.setParent(null);
        this.studentRepository.save(student);
        response.setStatus(200);
      } else {
        response.sendError(400, "Student and parent ids do not match");
      }
    } else {
      response.sendError(400, "Student and/or parent not found");
    }
  }

  @DeleteMapping("/api/student/{studentId}/counselor/{counselorId}")
  public void deleteStudentCounselorRelation(@PathVariable("studentId") int studentId,
                                             @PathVariable("counselorId") int counselorId,
                                             HttpServletResponse response) throws IOException {
    Optional<Student> optionalStudent = this.studentRepository.findById(studentId);
    Optional<CollegeCounselor> optionalCounselor = this.collegeCounselorRepository.findById(counselorId);
    if (optionalStudent.isPresent() && optionalCounselor.isPresent()) {
      Student student = optionalStudent.get();
      CollegeCounselor counselor = optionalCounselor.get();

      if (student.getCollegeCounselor().getId() == counselor.getId()) {
        student.setCollegeCounselor(null);
        this.studentRepository.save(student);
        response.setStatus(200);
      } else {
        response.sendError(400, "Student and counselor ids do not match");
      }
    } else {
      response.sendError(400, "Student and/or parent not found");
    }
  }

  @DeleteMapping("/api/parent/{parentId}/counselor/{counselorId}")
  public void deleteParentCounselorRelation(@PathVariable("parentId") int parentId,
                                            @PathVariable("counselorId") int counselorId,
                                            HttpServletResponse response) throws IOException {
    Optional<Parent> optionalParent = this.parentRepository.findById(parentId);
    Optional<CollegeCounselor> optionalCounselor = this.collegeCounselorRepository.findById(counselorId);

    if (optionalParent.isPresent() && optionalCounselor.isPresent()) {
      Parent parent = optionalParent.get();
      CollegeCounselor counselor = optionalCounselor.get();
      // Remove the counselor from the parent's counselor list
      List<CollegeCounselor> collegeCounselorList = parent.getCollegeCounselors();
      collegeCounselorList.remove(counselor);
      this.parentRepository.save(parent);
      response.setStatus(200);
    } else {
      response.sendError(400, "Could not find parent and/or counselor");
    }
  }
}
