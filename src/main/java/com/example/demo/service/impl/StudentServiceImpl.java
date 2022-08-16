package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(long id) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			return student.get();
		} else {
			throw new ResourceNotFoundException("Student", "Id", id);
		}
	}

	@Override
	public Student updateStudent(Student student, long id) {
		// check if student is already present in the database

		Student existingStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));

		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());

		// save student in the database

		studentRepository.save(existingStudent);

		return existingStudent;
	}

	@Override
	public void deleteStudent(long id) {
		// TODO Auto-generated method stub

		// checking if the student exists

		studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));

		studentRepository.deleteById(id);
	}

}
