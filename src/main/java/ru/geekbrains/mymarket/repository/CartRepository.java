package ru.geekbrains.mymarket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mymarket.dto.OrderItemDto;
import ru.geekbrains.mymarket.model.OrderItem;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<OrderItem, Long> {

}
