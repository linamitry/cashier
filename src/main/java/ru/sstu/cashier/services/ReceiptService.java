package ru.sstu.cashier.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sstu.cashier.exceptions.CashierException;
import ru.sstu.cashier.mapper.MapStructMapper;
import ru.sstu.cashier.models.dto.ReceiptDto;
import ru.sstu.cashier.models.entity.Product;
import ru.sstu.cashier.models.entity.Receipt;
import ru.sstu.cashier.models.enums.OrderStatus;
import ru.sstu.cashier.models.response.ProductUpdateResponse;
import ru.sstu.cashier.models.response.ReceiptResponse;
import ru.sstu.cashier.models.response.ReceiptUpdateResponse;
import ru.sstu.cashier.repositories.ProductRepository;
import ru.sstu.cashier.repositories.ReceiptRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ProductRepository productRepository;
    private final CashierService cashierService;
    private final ProductService productService;
    private final MapStructMapper mapper;

    public List<ReceiptUpdateResponse> getByCashier(long cashierId) {
        return prepareUpdateResponse(receiptRepository.findReceiptByCashierId(cashierId));
    }

    public List<ReceiptResponse> getAll() {
        var receipts = receiptRepository.findAll();
        return mapper.receiptsToReceiptResponse((List<Receipt>) receipts);
    }

    public ReceiptUpdateResponse getById(long id) {
        return prepareUpdateResponse(Collections.singletonList(receiptRepository.findById(id).get())).stream().findFirst().get();
    }

    @Transactional
    public void create(ReceiptDto receiptDto) {
        var products = receiptToProductFlatList(receiptDto);
        var cashier = cashierService.getById(receiptDto.getCashierId());
        var amount = calculateAmount(products);
        var receipt = new Receipt(amount, products, cashier, OrderStatus.IN_PROGRESS, LocalDateTime.now());
        receiptRepository.save(receipt);
        products.forEach(product -> {
            product.getReceipts().add(receipt);
            productRepository.save(product);
        });
    }

    @Transactional
    public void update(long id, ReceiptDto receiptDto) {
        var receipt = receiptRepository.findById(id).orElseThrow(
                () -> new CashierException("receipt not found")
        );
        receipt.getProducts().forEach(product -> {
            product.getReceipts().remove(receipt);
            productRepository.save(product);
        });

        var products = receiptToProductFlatList(receiptDto);
        var amount = calculateAmount(products);
        receipt.setProducts(products);
        receipt.setAmount(amount);
        receiptRepository.save(receipt);

        products.forEach(product -> {
            product.getReceipts().add(receipt);
            productRepository.save(product);
        });
    }

    public void close(long id) {
        var receipt = receiptRepository.findById(id).orElseThrow(
                () -> new CashierException("receipt not found")
        );
        receipt.setStatus(OrderStatus.SUCCESS);
        receipt.setClosedAt(LocalDateTime.now());
    }

    public void rejected(long id) {
        var receipt = receiptRepository.findById(id).orElseThrow(
                () -> new CashierException("receipt not found")
        );
        receipt.setStatus(OrderStatus.REJECTED);
    }

    private BigDecimal calculateAmount(List<Product> products) {
        return products
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private List<Product> receiptToProductFlatList(ReceiptDto receiptDto) {
        return receiptDto.getProducts().stream().flatMap(it ->
                IntStream.range(0, it.getCount())
                        .mapToObj(i -> productService.getById(it.getProductId())))
                .collect(Collectors.toList());
    }

    private List<ReceiptUpdateResponse> prepareUpdateResponse(List<Receipt> receipts){
        return receipts.stream().map(receipt -> {
            var receiptUpdate = new ReceiptUpdateResponse();
            receiptUpdate.setReceiptId(receipt.getId());
            receiptUpdate.setAmount(receipt.getAmount());
            Map<Long, List<Product>> result = receipt.getProducts()
                    .stream()
                    .collect(Collectors.groupingBy(Product::getId));
            receiptUpdate.setProducts(result.entrySet().stream().map((e) -> {
                var productUpdate = new ProductUpdateResponse();
                productUpdate.setCount(e.getValue().size());
                productUpdate.setId(e.getKey());
                productUpdate.setName(e.getValue().stream().findFirst().get().getName());
                productUpdate.setPrice(e.getValue().stream().findFirst().get().getPrice());
                return productUpdate;
            }).collect(Collectors.toList()));
            return receiptUpdate;
        }).collect(Collectors.toList());
    }
}