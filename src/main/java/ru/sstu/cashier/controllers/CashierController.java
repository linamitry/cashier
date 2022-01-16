package ru.sstu.cashier.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sstu.cashier.mapper.MapStructMapper;
import ru.sstu.cashier.models.dto.CashierLoginDto;
import ru.sstu.cashier.models.dto.CashierRegisterDto;
import ru.sstu.cashier.models.entity.Cashier;
import ru.sstu.cashier.models.response.CashierResponse;
import ru.sstu.cashier.models.response.ProductResponse;
import ru.sstu.cashier.services.CashierService;

import java.util.Map;

@RestController
@RequestMapping("api/v1/cashier")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CashierController {
    private final CashierService cashierService;
    private final MapStructMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CashierRegisterDto cashierRegisterDto) {
        cashierService.register(cashierRegisterDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/login")
    public ResponseEntity<CashierResponse> login(@RequestBody CashierLoginDto cashierLoginDto) {
        var response = cashierService.login(cashierLoginDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashierResponse> getById(@PathVariable long id) {
        var response = cashierService.getById(id);
        return ResponseEntity.ok(mapper.cashierToCashierResponse(response));
    }
}
