package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exception.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.service.StudentService;

@RestController
@RequestMapping(path="students")
public class StudentController {
@Autowired
private StudentService service = null;

// http://localhost:9090/student-api/students  Method  - Get

@GetMapping
public List<Student> getAllStudents(){
return service.findAllStudents();
}

// http://localhost:9090/student-api/students Method - Post

@PostMapping
 public ResponseEntity<String> saveStudent(@RequestBody Student student) {
	ResponseEntity<String> response=null;
	 service.addStudent(student);
	 if(student.getStudentId() !=0) {
		response=new ResponseEntity<String>(student.getStudentId()+" is created in the database", 
				HttpStatus.CREATED);
		}
	 return response;
	 }
 
//http://localhost:9090/student-api/students/100  Method  - Get
//studentId - Path variable
//100 will be the value of Path Variable

@GetMapping(path = "{studentId}")
public ResponseEntity<String> getStudentById(@PathVariable("studentId") int studentId)throws NoSuchStudentException {
		ResponseEntity<String> response=null;
		response=new ResponseEntity<String>(studentId+" is found in the database", 
				HttpStatus.CREATED);
		return response;
	
}

@PutMapping
public ResponseEntity<Student> modifyStudent(@RequestBody Student student) {
	student =service.modifyStudent(student);
	ResponseEntity<Student> response=new ResponseEntity<Student>(student,HttpStatus.OK);
	return response;
}

@DeleteMapping(path = "{studentId}")  
public ResponseEntity<String> deleteStudent(@PathVariable("studentId") int studentId)   
{  
service.removeStudent(studentId); 
ResponseEntity<String> response=new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
return response;
} 

//http://localhost:9090/student-api/students/searchByName/Abel
@GetMapping(path = "searchByName/{studentName}")
public ResponseEntity<List<Student>> getStudentByStudentName(@PathVariable("studentName")String studentName) {
	List<Student> list = service.findStudentByName(studentName);
    ResponseEntity<List<Student>> response=new ResponseEntity<List<Student>>(list,HttpStatus.OK);
    return response;
}

@ExceptionHandler(NoSuchStudentException.class)
@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR, reason="Student Not Found")
public void handlEexception() {
	
}


}

