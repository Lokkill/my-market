package ru.geekbrains.mymarket.controller;

import org.apache.logging.log4j.message.ObjectMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mymarket.dto.ProductDto;
import ru.geekbrains.mymarket.error_handling.InvalidDataException;
import ru.geekbrains.mymarket.error_handling.ResourceNotFoundException;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/list")
public class MainController {

    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1") int page){
        Page<Product> products = productService.findPage(page - 1, 10);
        Page<ProductDto> dtoPage = new PageImpl<>(products.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), products.getPageable(), products.getTotalElements());
        return dtoPage;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        productService.removeById(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@RequestAttribute ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found by id: " + id));
        return new ProductDto(product);
    }

    @PostMapping
    public ProductDto addNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult result){
        if (result.hasErrors()){
            throw new InvalidDataException(result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        return productService.createNewProduct(productDto);
    }
}
