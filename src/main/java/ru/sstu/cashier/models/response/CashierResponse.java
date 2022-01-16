package ru.sstu.cashier.models.response;

import lombok.Data;

@Data
public class CashierResponse {
    private long id;
    private String name;
    private String login;
    private String password;
}
