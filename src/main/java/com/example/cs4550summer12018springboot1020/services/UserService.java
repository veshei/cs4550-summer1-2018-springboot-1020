package com.example.cs4550summer12018springboot1020.services;

import com.example.cs4550summer12018springboot1020.models.User;
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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://cs4550-summer1-2018-react-1020.herokuapp.com/"},
        maxAge = 3600)
public class UserService {
  @Autowired
  UserRepository userRepository;

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

  @PostMapping("/api/register")
  public User register(@RequestBody User user, HttpSession session) {
    List<User> users = (List<User>) this.userRepository.findUserByUsername(user.getUsername());
    if (users.size() == 0) {
      session.setAttribute("currentUser", user);
      userRepository.save(user);
    }
    else {
      throw new IllegalStateException();
    }
    return user;
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
    return userRepository.save(user);
  }

  @PutMapping("api/user/{userId}")
  public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
    Optional<User> data = userRepository.findById(userId);
    if(data.isPresent()) {
      User user = data.get();
      if (newUser.getUsername() != null) {
        user.setUsername(newUser.getUsername());
      }
      if (newUser.getPassword() != null) {
        user.setPassword(newUser.getPassword());
      }
      if (newUser.getFirstName() != null) {
        user.setFirstName(newUser.getFirstName());
      }
      if (newUser.getLastName() != null) {
        user.setLastName(newUser.getLastName());
      }
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
}
