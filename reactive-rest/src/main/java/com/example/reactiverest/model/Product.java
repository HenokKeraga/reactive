package com.example.reactiverest.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
@Builder
public class Product {
    @Id
    private int id;
    private String name;
}
