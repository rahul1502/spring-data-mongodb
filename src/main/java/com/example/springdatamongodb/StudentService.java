package com.example.springdatamongodb;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final MongoTemplate mongoTemplate;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void insertStudent(Student student) {

//        Address address = new Address(
//                "Canada",
//                "Toronto",
//                "M9A"
//        );
//        Student student = new Student(
//                "Rahul",
//                "Vala",
//                "rvala@gmail.com",
//                Gender.FEMALE,
//                address,
//                List.of("Computer Science", "Maths"),
//                BigDecimal.TEN,
//                LocalDateTime.now()
//        );

        String email = student.getEmail();

        // Method 1
        usingMongoTemplateAndQuery(studentRepository, mongoTemplate, student, email);

        // Method 2
        usingMongoQueryMethod(studentRepository, student, email);
    }

    private void usingMongoQueryMethod(StudentRepository studentRepository, Student student, String email) {
        studentRepository.findStudentsByEmail(email)
                .ifPresentOrElse(s -> {
                    System.out.println(student + " already exists");
                }, () -> {
                    // Insert Student record
                    System.out.println("Inserting student " + student);
                    studentRepository.insert(student);
                });
    }

    private void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate, Student student, String email) {
        // Create a Query to Find Student Object from email and Execute it using MongoTemplate

        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        List<Student> students = mongoTemplate.find(query, Student.class);

        if(students.size() > 1) {
            throw new IllegalStateException("Found multiple Students with email: " + email);
        }

        if(students.isEmpty()) {
            // Insert Student record
            System.out.println("Inserting student " + student);
            studentRepository.insert(student);
        }
        else {
            System.out.println(student + " already exists");
        }
    }
}
