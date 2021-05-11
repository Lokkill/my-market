package ru.geekbrains.mymarket.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.mymarket.dto.ProductDto;
import ru.geekbrains.mymarket.error_handling.ResourceNotFoundException;
import ru.geekbrains.mymarket.model.Product;
import ru.geekbrains.mymarket.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findPage(int page, int pageSize){
        return productRepository.findAllBy(PageRequest.of(page, pageSize));
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

    @Transactional
    public ProductDto createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists, id: " + productDto.getId()));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        return new ProductDto(product);
    }
}
