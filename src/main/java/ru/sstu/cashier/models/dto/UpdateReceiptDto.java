package ru.sstu.cashier.models.dto;

import lombok.Data;
import java.util.List;

@Data
public class UpdateReceiptDto {
    private long cashierId;
    private long receiptId;
    private List<ReceiptProductDto> products;
}
