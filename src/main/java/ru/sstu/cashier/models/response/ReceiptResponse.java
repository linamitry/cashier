package ru.sstu.cashier.models.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ReceiptResponse {
    private long id;
    private BigDecimal amount;
    private List<ProductResponse> products;
    private CashierResponse cashier;
}
