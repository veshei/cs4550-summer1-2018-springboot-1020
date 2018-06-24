package com.example.cs4550summer12018springboot1020.repositories;

import com.example.cs4550summer12018springboot1020.models.CollegeCounselor;
import com.example.cs4550summer12018springboot1020.models.Parent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ParentRepository extends CrudRepository<Parent, Integer> {
  /**
   * Returns a list of counselors associated with the parent of the given id.
   */
  @Query("SELECT p.collegeCounselors FROM Parent p WHERE p.id=:parentId")
  List<CollegeCounselor> findCounselorsForParent(@PathVariable("parentId") int parentId);
}
