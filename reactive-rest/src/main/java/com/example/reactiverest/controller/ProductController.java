package com.example.reactiverest.controller;

import com.example.reactiverest.model.Product;
import com.example.reactiverest.service.ProductService;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/getAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping(value = "/getAll2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getAll2() throws InterruptedException {
        return productService.getAll2();
    }
}

