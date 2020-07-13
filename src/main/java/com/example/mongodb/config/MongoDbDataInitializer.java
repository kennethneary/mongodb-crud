package com.example.mongodb.config;

import com.example.mongodb.dao.EmployeeRepository;
import com.example.mongodb.model.Employee;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Component
public class MongoDbDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        final Supplier<String> idSupplier = getIdSequenceSupplier();
        final Supplier<Integer> salarySupplier = getSalarySupplier();
        final Faker faker = new Faker();
        final int totalEmployees = 10;
        final List<Employee> employees = Stream.generate(() -> faker.name().fullName())
                .limit(totalEmployees)
                .map(name -> new Employee(idSupplier.get(), name, salarySupplier.get()))
                .collect(Collectors.toList());

        employeeRepository.deleteAll();
        employeeRepository.saveAll(employees);
    }

    private Supplier<String> getIdSequenceSupplier() {
        return new Supplier<String>() {
            Long l = 0L;

            @Override
            public String get() {
                return String.format("%05d", l++);
            }
        };
    }

    private Supplier<Integer> getSalarySupplier() {
        return new Supplier<Integer>() {
            final Random rand = new Random();

            final int maxSalary = 100_000;
            final int minSalary = 30_000;
            final int salaryRange = ((maxSalary - minSalary) + 1 )+ minSalary;

            @Override
            public Integer get() {
                return rand.nextInt(salaryRange);
            }
        };
    }
}
