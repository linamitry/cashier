package ru.sstu.cashier.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateReceiptDto {
    private long cashierId;
    private List<ReceiptProductDto> products;
}
