package com.schoolmanagment.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.schoolmanagment.model.Student;
import com.schoolmanagment.repo.StuRepository;



@Service
public class StudentService {


    @Autowired
    private StuRepository repository;



    @Cacheable(value="students")
    public List<Student> getAllStudents(){

        System.out.println("Fetching students from database");

        return repository.findAll();

    }



    @Cacheable(value="students", key="#id")
    public Student getStudentById(Long id){

        System.out.println("Fetching student from database");

        return repository.findById(id)
                .orElse(null);

    }



    @CacheEvict(value="students", allEntries=true)
    public Student saveStudent(Student student){

        System.out.println("Saving student");

        return repository.save(student);

    }



    @CacheEvict(value="students", allEntries=true)
    public void deleteStudent(Long id){

        repository.deleteById(id);

    }



    @CacheEvict(value="students", allEntries=true)
    public Student updateStudent(Student student){

        return repository.save(student);

    }

}