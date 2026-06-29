package com.example.AuthenticationModule.Service;

import com.example.AuthenticationModule.Model.LoginRequest;
import com.example.AuthenticationModule.Model.Student;
import com.example.AuthenticationModule.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
     private StudentRepository repo;


    public Student addStudent(Student student){
        return repo.save(student);
    }

    public List<Student> getStudents(){
        return repo.findAll();
    }

    public Student getStudent(LoginRequest loginrequest){
        Optional<Student> record=repo.findByEmail(loginrequest.getEmail());

        if(record.isEmpty())  return null;

        Student student =record.get();
        if(student.getPassword().equals(loginrequest.getPassword())) return student;

        return  null;

    }
}
