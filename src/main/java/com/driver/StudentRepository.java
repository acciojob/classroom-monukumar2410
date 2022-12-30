package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class StudentRepository {
    
    private HashMap<String, Student> studentMap = new HashMap<>();
    private HashMap<String, Teacher> teacherMap = new HashMap<>();
    private HashMap<String, List<String>> teacherStudentMap = new HashMap<>();

    public void addStudent(Student student){

        studentMap.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher){

        teacherMap.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student , String teacher){

        List<String> students = new ArrayList<>();

        if(teacherStudentMap.containsKey(teacher))
        students = teacherStudentMap.get(teacher);

        if(!students.contains(student))
        students.add(student);

        teacherStudentMap.put(teacher, students);
    }

    public Student getStudentByName(String name){

        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name){

        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String name){

         List<String> students = new ArrayList<>();

         if(teacherStudentMap.containsKey(name))
         students = teacherStudentMap.get(name);

         return students;

    }

    public List<String> getAllStudents(){

        List<String> allStudents = new ArrayList<>();

        for(String name : studentMap.keySet()){
            allStudents.add(name);
        }

        return allStudents;
    }

    public void deleteTeacherByName(String name){

        List<String> students = new ArrayList<>();

        if(teacherStudentMap.containsKey(name)){
        students = teacherStudentMap.get(name);
        teacherStudentMap.remove(name);
        }

        for(String s : studentMap.keySet()){
            if(students.contains(s))
            studentMap.remove(s);
        }

        if(teacherMap.containsKey(name))
        teacherMap.remove(name);
    }

    public void deleteAllTeachers(){

        List<String> students = new ArrayList<>();

        for(String teacher : teacherStudentMap.keySet()){
            students = teacherStudentMap.get(teacher);

            for(String student : studentMap.keySet()){

                if(students.contains(student))
                studentMap.remove(student);
            }
        }
        teacherMap.clear();
        teacherStudentMap.clear();
    }

}
