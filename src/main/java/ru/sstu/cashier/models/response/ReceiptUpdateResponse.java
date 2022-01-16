package ru.sstu.cashier.models.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReceiptUpdateResponse {
    List<ProductUpdateResponse> products;
    private long receiptId;
    private BigDecimal amount;
}
