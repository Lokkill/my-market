package ru.geekbrains.mymarket.controller;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mymarket.model.Cart;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Cart cart;
    private final ProductService productService;

    public CartController(Cart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getAllProductFromCart(){
        return cart.getAllProductFromCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cart.addToCart(productService.findById(id).get());
    }

    @GetMapping("/clear")
    public void clearCart(){
        cart.clearCart();
    }

}
