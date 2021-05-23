package ru.geekbrains.mymarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mymarket.model.OrderItem;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal productPrice;
    private BigDecimal price;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.productPrice = orderItem.getProductPrice();
        this.price = orderItem.getPrice();
    }
}
