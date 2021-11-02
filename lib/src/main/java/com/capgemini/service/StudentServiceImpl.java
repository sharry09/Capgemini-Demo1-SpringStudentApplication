package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.capgemini.dao.StudentDao;
import com.capgemini.exception.NoSuchStudentException;
import com.capgemini.model.Student;
@Service
@Scope(scopeName = "singleton")

public class StudentServiceImpl implements StudentService {
	@Autowired
private StudentDao dao=null;
	@Transactional
	public void addStudent(Student student) {
		 dao.save(student);
		
	}

	public Student findStudentById(int studentId) throws NoSuchStudentException {
		if(dao.existsById(studentId)) {
		return	dao.findById(studentId).get();
		}
		throw new NoSuchStudentException(studentId+" Not Found");
	}

	public List<Student> findAllStudents() {
	    return dao.findAll();
	}

	@Override
	public Student modifyStudent(Student student) {
		return dao.save(student);
	}

	@Override
	public void removeStudent(int studentId) {
		
		dao.deleteById(studentId);
		
	}

	@Override
	public List<Student> findStudentByName(String studentName) {
	
		return dao.findByStudentNameIgnoreCase(studentName);
	}

//	@Override
//	public List<Student> findStudentByScore(double min, double max) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
