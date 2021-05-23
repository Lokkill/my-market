package ru.geekbrains.mymarket.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserOrderDto {
    private String username;
    private CartDto order;
}
