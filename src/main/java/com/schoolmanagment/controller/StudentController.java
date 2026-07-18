package com.schoolmanagment.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.schoolmanagment.model.Student;
import com.schoolmanagment.service.StudentService;



@RestController
@RequestMapping("/student")
public class StudentController {



    @Autowired
    private StudentService service;



    //GET ALL

    @GetMapping("/students")
    public List<Student> getStudents(){

        return service.getAllStudents();

    }



    //GET ONE

    @GetMapping("/students/{id}")
    public Student getStudent(
            @PathVariable Long id){

        return service.getStudentById(id);

    }




    //CREATE

    @PostMapping("/students")
    public Student createStudent(
            @RequestBody Student student){


        return service.saveStudent(student);

    }




    //UPDATE

    @PutMapping("/students/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student){


        student.setId(id);

        return service.updateStudent(student);

    }




    //DELETE

    @DeleteMapping("/students/{id}")
    public String deleteStudent(
            @PathVariable Long id){

        service.deleteStudent(id);

        return "Deleted successfully";

    }


}