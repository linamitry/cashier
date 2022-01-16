package ru.sstu.cashier.models.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryDto category;
}
