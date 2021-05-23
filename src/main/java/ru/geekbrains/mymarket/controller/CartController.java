package ru.geekbrains.mymarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.mymarket.dto.CartDto;
import ru.geekbrains.mymarket.dto.UserOrderDto;
import ru.geekbrains.mymarket.model.Cart;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDto getCart(){
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cart.addToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cart.clearCart();
    }

    @PostMapping("/checkout")
    public void checkout(@RequestBody @Validated UserOrderDto userOrder){
        cart.checkout(userOrder);
    }
}
