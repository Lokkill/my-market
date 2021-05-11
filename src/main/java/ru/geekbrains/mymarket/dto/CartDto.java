package ru.geekbrains.mymarket.dto;

import lombok.Data;
import ru.geekbrains.mymarket.model.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private List<ProductDto> cart;

    public CartDto(Cart cart){
        this.cart = cart.getAllProductFromCart().stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
