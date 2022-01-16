package ru.sstu.cashier.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sstu.cashier.mapper.MapStructMapper;
import ru.sstu.cashier.models.dto.ReceiptDto;
import ru.sstu.cashier.models.response.ReceiptResponse;
import ru.sstu.cashier.models.response.ReceiptUpdateResponse;
import ru.sstu.cashier.services.ReceiptService;
import java.util.List;

@RestController
@RequestMapping("api/v1/receipt")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final MapStructMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReceiptResponse>> getAll() {
        var response = receiptService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{receiptId}")
    public ResponseEntity<ReceiptUpdateResponse> getById(@PathVariable long receiptId) {
        return ResponseEntity.ok(receiptService.getById(receiptId));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<ReceiptUpdateResponse>> getByCashier(@PathVariable long cashierId) {
        return ResponseEntity.ok(receiptService.getByCashier(cashierId));
    }

    @PostMapping
    public void create(@RequestBody ReceiptDto receiptDto) {
        log.error(receiptDto.toString());
        receiptService.create(receiptDto);
    }

    @PutMapping("{id}")
    public void update(@PathVariable long id, @RequestBody ReceiptDto receiptDto) {
        receiptService.update(id, receiptDto);
    }

}
