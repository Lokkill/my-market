package ru.geekbrains.mymarket.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }
}
