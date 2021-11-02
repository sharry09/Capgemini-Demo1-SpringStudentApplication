package com.capgemini.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student,Integer>{


@Query(value="Select s From Student s Where LOWER(s.studentName) LIKE LOWER(concat('%', :name, '%'))")
public List<Student> findByStudentNameIgnoreCase(@Param("name") String studentName);

	
}
//Jpa Data module will provide implementation class for Student Dao Interface