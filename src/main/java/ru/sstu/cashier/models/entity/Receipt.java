package ru.sstu.cashier.models.entity;

import lombok.*;
import ru.sstu.cashier.models.enums.OrderStatus;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "receipts")
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    public Receipt(BigDecimal amount,
                   List<Product> products,
                   Cashier cashier,
                   OrderStatus status,
                   LocalDateTime createdAt){
        this.amount = amount;
        this.cashier = cashier;
        this.products = products;
        this.status = status;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal amount;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime closedAt;

    @ManyToMany(mappedBy = "receipts")
    private List<Product> products;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "cashier_id")
    private Cashier cashier;
}
