package ru.geekbrains.mymarket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/list")
public class MainController {

    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.removeById(id);
    }

    @PutMapping("/{id}")
    public void saveProduct(@RequestAttribute Product product) {
        productService.saveOrUpdate(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
        Product reqProduct = productService.saveOrUpdate(product);
        return new ResponseEntity<>(reqProduct, HttpStatus.CREATED);
    }
}
