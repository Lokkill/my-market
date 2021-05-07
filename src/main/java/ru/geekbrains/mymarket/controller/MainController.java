package ru.geekbrains.mymarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/list")
public class MainController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.removeById(id);
    }

    @PutMapping("/{id}")
    public void saveProduct(@RequestBody Product product) {
        productService.saveOrUpdate(product);
    }
}
