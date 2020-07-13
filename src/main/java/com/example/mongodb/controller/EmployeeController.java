package com.example.mongodb.controller;

import com.example.mongodb.model.Employee;
import com.example.mongodb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/paged")
    public ResponseEntity<Iterable<Employee>> getEmployeePaging(
            final @RequestParam(name = "page") int page,
            final @RequestParam(name = "size") int size) {
        return ResponseEntity.ok(employeeService.getEmployeePaging(PageRequest.of(page, size)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> all() {
        return ResponseEntity.ok(this.employeeService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Employee> createEmployee(@RequestBody final Employee employee) {
        return ResponseEntity.ok(this.employeeService.create(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@RequestBody final Employee employee,
                                                           @PathVariable final String id) {
        final Employee updatedEmployee = this.employeeService.update(employee, id);
        final HttpStatus httpStatus = updatedEmployee == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(updatedEmployee, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployee(@PathVariable final String id) {
        final Employee employee = this.employeeService.findById(id);
        final HttpStatus httpStatus = employee == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity(httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable final String id) {
        if (this.employeeService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
