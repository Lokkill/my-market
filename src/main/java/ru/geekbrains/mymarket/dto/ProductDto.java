package ru.geekbrains.mymarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.mymarket.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @Size(min = 4, max = 255, message = "Title size 4-255")
    private String title;
    @Min(value = 1, message = "Price minimum 1")
    private BigDecimal price;

    public ProductDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
