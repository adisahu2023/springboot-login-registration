package com.example.AuthenticationModule.Service;

import com.example.AuthenticationModule.Model.LoginRequest;
import com.example.AuthenticationModule.Model.Student;
import com.example.AuthenticationModule.Repository.StudentRepository;
import com.example.AuthenticationModule.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
     private StudentRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Student addStudent(Student student){
        String encoded = passwordEncoder.encode(student.getPassword());
        student.setPassword(encoded);
        return repo.save(student);
    }

    public List<Student> getStudents(){
        return repo.findAll();
    }

    public Student getStudent(LoginRequest loginrequest){
        Optional<Student> record=repo.findByEmail(loginrequest.getEmail());

        if(!record.isEmpty() && passwordEncoder.matches(loginrequest.getPassword(),record.get().getPassword()) )
        return record.get();
        else
        return  null;

    }
}
