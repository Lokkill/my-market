package ru.geekbrains.mymarket.dto;

import lombok.Data;
import ru.geekbrains.mymarket.model.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDto {
    private List<OrderItemDto> cart;
    private BigDecimal sum;

    public CartDto(Cart cart){
        this.cart = cart.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }
}
