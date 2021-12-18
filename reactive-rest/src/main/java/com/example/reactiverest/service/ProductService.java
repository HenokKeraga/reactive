package com.example.reactiverest.service;

import com.example.reactiverest.model.Product;
import com.example.reactiverest.respository.ProductRepository;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.BiFunction;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final DatabaseClient databaseClient;

    public ProductService(ProductRepository productRepository, DatabaseClient databaseClient) {
        this.productRepository = productRepository;
        this.databaseClient = databaseClient;
    }

    public Flux<Product> getAll() {
        return productRepository.findAll().delayElements(Duration.ofSeconds(5));
    }

    public Flux<Product> getAll2() {
        return databaseClient
                .sql("SELECT * from product")
                .map(mappingFunction)
                .all().delayElements(Duration.ofSeconds(5));
    }

    static BiFunction<Row, RowMetadata, Product> mappingFunction =
            (row, rowMetadata) -> Product.builder()
                    .id(row.get("ID", Integer.class))
                    .name(row.get("NAME", String.class))
                    .build();

}
