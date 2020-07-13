package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@Setter
public class Employee {
    @Id
    private String id;
    private String name;
    private long salary;
}
