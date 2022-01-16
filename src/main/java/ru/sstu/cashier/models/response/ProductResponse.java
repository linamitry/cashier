package ru.sstu.cashier.models.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductResponse {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
}
