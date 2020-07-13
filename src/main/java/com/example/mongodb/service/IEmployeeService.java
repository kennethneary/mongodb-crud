package com.example.mongodb.service;

import com.example.mongodb.model.Employee;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IEmployeeService {
    Employee create(Employee employee);
    Employee findById(String id);
    List<Employee> findAll();
    Employee update(Employee employee, String id);
    boolean delete(String id);
    Iterable<Employee> getEmployeePaging(PageRequest pageRequest);
}