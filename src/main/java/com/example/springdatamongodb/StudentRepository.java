package com.example.springdatamongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    // <Class Name, Id datatype>

    // Method by MongoRepository to find records by a field
    Optional<Student> findStudentsByEmail(String email);

}
