package ru.geekbrains.mymarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mymarket.dto.CartDto;
import ru.geekbrains.mymarket.dto.ProductDto;
import ru.geekbrains.mymarket.error_handling.ResourceNotFoundException;
import ru.geekbrains.mymarket.model.Cart;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {
    private final Cart cart;
    private final ProductService productService;

    @GetMapping("/cart")
    public CartDto getAllProductFromCart(){
        CartDto cartDto = new CartDto(cart);
        return cartDto;
    }

    @GetMapping("/cart/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id" + id));
        cart.addToCart(product);
    }

    @GetMapping("/cart/clear")
    public void clearCart(){
        cart.clearCart();
    }

}
