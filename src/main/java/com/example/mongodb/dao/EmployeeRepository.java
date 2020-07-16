package com.example.mongodb.dao;

import com.example.mongodb.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findAllByIdNotNullOrderByNameAsc(final Pageable page);
}
