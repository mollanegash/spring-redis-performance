package com.schoolmanagment.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolmanagment.model.Student;


@Repository
public interface StuRepository 
        extends JpaRepository<Student, Long>{

}