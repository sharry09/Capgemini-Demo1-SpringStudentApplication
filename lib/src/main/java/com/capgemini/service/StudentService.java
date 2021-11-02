package com.capgemini.service;

import java.util.List;

import com.capgemini.exception.NoSuchStudentException;
import com.capgemini.model.Student;

public interface StudentService {
public void addStudent(Student student);
public Student findStudentById(int studentId)throws NoSuchStudentException;
public List<Student> findAllStudents();

public Student modifyStudent(Student student);
public void removeStudent(int studentId);
public List<Student> findStudentByName(String studentName);
//public List<Student> findStudentByScore(double min,double max);
}
