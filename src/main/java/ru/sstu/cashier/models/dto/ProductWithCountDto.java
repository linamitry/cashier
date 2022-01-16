package ru.sstu.cashier.models.dto;

import lombok.Data;

@Data
public class ProductWithCountDto {
    private long productId;
    private int count;
}
