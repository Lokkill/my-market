package ru.geekbrains.mymarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mymarket.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
