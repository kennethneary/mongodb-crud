package com.example.mongodb.service;

import com.example.mongodb.dao.EmployeeRepository;
import com.example.mongodb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        final Iterable<Employee> iterable = employeeRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Employee update(Employee employee, String id) {
        final Employee updateEmployee = this.findById(id);
        if (updateEmployee == null) {
            return null;
        }
        updateEmployee.setName(employee.getName());
        updateEmployee.setSalary(employee.getSalary());
        return employeeRepository.save(updateEmployee);
    }

    @Override
    public boolean delete(String id) {
        final Employee delEmployee = employeeRepository.findById(id).orElse(null);
        if (delEmployee != null) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Iterable<Employee> getEmployeePaging(PageRequest pageRequest) {
        return employeeRepository.findAllByIdNotNullOrderByIdAsc(pageRequest);
    }
}