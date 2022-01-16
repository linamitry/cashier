package ru.sstu.cashier.models.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReceiptUpdateDto {
    private LocalDateTime createdAt;
    private long cashier;
}
