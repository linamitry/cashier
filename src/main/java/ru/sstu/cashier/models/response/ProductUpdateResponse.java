package ru.sstu.cashier.models.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateResponse {
    private long id;
    private String name;
    private BigDecimal price;
    private long count;
}
