package com.example.AuthenticationModule.Controller;


import com.example.AuthenticationModule.Model.LoginRequest;
import com.example.AuthenticationModule.Model.Student;
import com.example.AuthenticationModule.Service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class Registration {
    @Autowired
    StudentService service;

//    @GetMapping("/logged")
//    public List<Student> getStudents(){
//        return service.getStudents();
//    }

    @PostMapping("/registration")
    @CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
    public Student addStudent(@RequestBody Student student ,HttpSession session){
         Student s1= service.addStudent(student);
        if (s1!=null){
            session.setAttribute("student",student);
        }
        return s1;

    }

    @PostMapping("/login")
    @CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
    public ResponseEntity<Student> getStudent(@RequestBody LoginRequest loginRequest, HttpSession session){
        Student student=service.getStudent(loginRequest);
        System.out.println(student);
        if(student !=null) {
            session.setAttribute("student",student);
            System.out.println("PROFILE SESSION ID = " + session.getId());
            System.out.println("Student = " + session.getAttribute("student"));

            return ResponseEntity.ok(student);}

        else     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/profile")
    @CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
    public ResponseEntity<Student> profile(HttpSession session) {

        System.out.println("PROFILE SESSION ID = " + session.getId());

        Student student = (Student) session.getAttribute("student");

        System.out.println("Student = " + student);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(student);
    }

    @PostMapping("/logout")
    @CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();

        return ResponseEntity.ok()
                .build();
    }



}
