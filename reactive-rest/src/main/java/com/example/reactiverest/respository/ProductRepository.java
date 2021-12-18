package com.example.reactiverest.respository;

import com.example.reactiverest.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {
}
