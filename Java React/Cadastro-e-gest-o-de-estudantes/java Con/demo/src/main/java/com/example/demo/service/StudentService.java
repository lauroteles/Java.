package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Exception.StudentAlreadyExistsException;
import com.example.demo.Exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        if (studentAlreadyExist(student.getEmail())){
            throw  new StudentAlreadyExistsException(student.getEmail()+ " already exists!");
        }
        return studentRepository.save(student);
    }

    private boolean studentAlreadyExist(String email) {
        
        return studentRepository.findByEmail(email).isPresent();
    }


    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }



    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return  studentRepository.save(st);

        }).orElseThrow(()-> new StudentNotFoundException("Sorry, this student could not e"));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
        .orElseThrow(()-> new StudentNotFoundException("Student not found, try a different ID"+id));
    }

    @Override
    public void deleteStudent(Long id) {
    if  (!studentRepository.existsById(id)) {
        throw new StudentNotFoundException("Try a differente ID Student not found");}
        studentRepository.deleteById(id);    
    }
    


}