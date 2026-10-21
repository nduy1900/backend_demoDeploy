package com.example.deployJAVA08.controller;

import com.example.deployJAVA08.entity.Product;
import com.example.deployJAVA08.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    // GET all
    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // POST
    @PostMapping
    public Product create(@RequestBody Product p) {
        return service.create(p);
    }

    // PUT
    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product p) {
        return service.update(id, p);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}