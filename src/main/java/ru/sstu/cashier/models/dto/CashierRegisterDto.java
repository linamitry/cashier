package ru.sstu.cashier.models.dto;

import lombok.Data;

@Data
public class CashierRegisterDto {
    private String name;
    private String login;
    private String password;
}
