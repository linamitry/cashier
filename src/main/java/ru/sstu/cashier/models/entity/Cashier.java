package ru.sstu.cashier.models.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cashiers")
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String login;

    private String password;

    @OneToMany(mappedBy="cashier")
    private List<Receipt> receipts;
}
