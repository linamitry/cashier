package ru.sstu.cashier.models.dto;

import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@ToString
public class ReceiptDto {
    private List<ProductWithCountDto> products;
    private long cashierId;
}
