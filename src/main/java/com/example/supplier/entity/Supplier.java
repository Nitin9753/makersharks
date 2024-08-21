package com.example.supplier.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("suppliers_collection")
public class Supplier{
    @Id
    private String id;
    private String company_name;
    private String website;
    private String location;
    private String nature_of_business;
    private List<String> manufacturing_processes;
}