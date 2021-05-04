package ru.geekbrains.mymarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.service.ProductService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private ProductService productService;

    @GetMapping("/list")
    public String showAllProducts(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id){
        productService.removeById(id);
        return "redirect:/list";
    }

    @PutMapping("/{id}")
    public String saveProduct(@RequestBody Product product){
        productService.saveOrUpdate(product);
        return "redirect:/list";
    }
}
