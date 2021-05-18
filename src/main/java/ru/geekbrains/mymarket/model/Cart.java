package ru.geekbrains.mymarket.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.geekbrains.mymarket.dto.OrderItemDto;
import ru.geekbrains.mymarket.dto.UserOrderDto;
import ru.geekbrains.mymarket.error_handling.ResourceNotFoundException;
import ru.geekbrains.mymarket.repository.CartRepository;
import ru.geekbrains.mymarket.repository.UserRepository;
import ru.geekbrains.mymarket.service.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class Cart {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final List<Product> list;
    private List<OrderItem> items;
    private BigDecimal sum;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void addToCart(Long id){
        for (OrderItem orderItem : items){
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incQuantity();
                recalculate();
                return;
            }
        }
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        items.add(new OrderItem(product));
        recalculate();
    }

    public void recalculate(){
        sum = BigDecimal.ZERO;
        items.forEach(orderItem -> sum = sum.add(orderItem.getPrice()));
    }

    public void clearCart(){
        list.clear();
    }

    public List<OrderItem> getAllProductFromCart(){
        return Collections.unmodifiableList(items);
    }

    public void checkout(UserOrderDto userOrder) {
        User user = userRepository.findByUsername(userOrder.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        for (OrderItemDto oid : userOrder.getOrder().getCart()){

        }
//        userOrder.getOrder().getCart().forEach(orderItemDto -> cartRepository.save());
    }
}
