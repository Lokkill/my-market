package ru.geekbrains.mymarket.model;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> list;

    @PostConstruct
    public void init(){
        list = new ArrayList<>();
    }

    public void addToCart(Product product){
        list.add(product);
    }

    public void clearCart(){
        list.clear();
    }

    public List<Product> getAllProductFromCart(){
        return list;
    }
}
