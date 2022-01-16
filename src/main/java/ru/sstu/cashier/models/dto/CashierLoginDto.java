package ru.sstu.cashier.models.dto;

import lombok.Data;

@Data
public class CashierLoginDto {
    private String login;
    private String password;
}
