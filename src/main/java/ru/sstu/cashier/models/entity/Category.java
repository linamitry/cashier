package ru.sstu.cashier.models.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.sstu.cashier.models.enums.OrderStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy="category")
    private List<Product> products;
}
