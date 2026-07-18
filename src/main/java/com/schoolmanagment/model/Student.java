package com.schoolmanagment.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="students")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Version
    private Integer version;


    @Column(name="student_name")
    private String studentName;


    @Column(unique=true)
    private String email;


    private String regDate;


    private String courseName;



    public Student(){}


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id=id;
    }


    public Integer getVersion(){
        return version;
    }


    public void setVersion(Integer version){
        this.version=version;
    }


    public String getStudentName(){
        return studentName;
    }


    public void setStudentName(String studentName){
        this.studentName=studentName;
    }


    public String getEmail(){
        return email;
    }


    public void setEmail(String email){
        this.email=email;
    }


    public String getRegDate(){
        return regDate;
    }


    public void setRegDate(String regDate){
        this.regDate=regDate;
    }


    public String getCourseName(){
        return courseName;
    }


    public void setCourseName(String courseName){
        this.courseName=courseName;
    }
}