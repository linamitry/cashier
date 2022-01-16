package ru.sstu.cashier.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sstu.cashier.models.entity.Cashier;
import java.util.Optional;

@Repository
public interface CashierRepository extends CrudRepository<Cashier, Long> {
    Optional<Cashier> findByLogin(String login);

    Optional<Cashier> findByLoginAndPassword(String login, String password);
}
