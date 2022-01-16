package ru.sstu.cashier.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sstu.cashier.exceptions.CashierException;
import ru.sstu.cashier.mapper.MapStructMapper;
import ru.sstu.cashier.models.dto.CashierLoginDto;
import ru.sstu.cashier.models.dto.CashierRegisterDto;
import ru.sstu.cashier.models.entity.Cashier;
import ru.sstu.cashier.models.response.CashierResponse;
import ru.sstu.cashier.repositories.CashierRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CashierService {
    private final CashierRepository cashierRepository;
    private final MapStructMapper mapper;

    public void register(CashierRegisterDto cashierRegisterDto) {
        var cashier = mapper.cashierRegisterDtoToCashier(cashierRegisterDto);
        if (cashierRepository.findByLogin(cashierRegisterDto.getLogin()).isPresent()) {
            throw new CashierException("login already present");
        }
        cashierRepository.save(cashier);
    }

    public CashierResponse login(CashierLoginDto cashierLoginDto) {
        var cashier = cashierRepository.findByLoginAndPassword(
                cashierLoginDto.getLogin(),
                cashierLoginDto.getPassword()
        ).orElseThrow(() -> new CashierException("Cashier not found"));

        return mapper.cashierToCashierResponse(cashier);
    }

    public Cashier getById(long id) {
        return cashierRepository.findById(id).orElseThrow(
                () -> new CashierException("Cashier not found")
        );
    }
}