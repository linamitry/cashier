package ru.sstu.cashier.models.dto;

import lombok.Data;

@Data
public class ReceiptProductDto {
    private long productId;
    private int count;
}
