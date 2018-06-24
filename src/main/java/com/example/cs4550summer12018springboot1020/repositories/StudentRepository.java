package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.CollegeCounselor;
import com.example.cs4550summer12018springboot1020.models.Parent;
import com.example.cs4550summer12018springboot1020.models.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
  /**
   * Returns the parent associated with the student of the given student id.
   * @param studentId the id of the student
   * @return the parent associated with the student
   */
  @Query("SELECT s.parent FROM Student s WHERE s.id=:studentId")
  Optional<Parent> findParentForStudent(@Param("studentId") int studentId);

  @Query("SELECT s.collegeCounselor FROM Student s WHERE s.id=:studentId")
  Optional<CollegeCounselor> findCounselorForStudent(@Param("studentId") int studentId);
}
