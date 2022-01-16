package ru.sstu.cashier.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sstu.cashier.mapper.MapStructMapper;
import ru.sstu.cashier.models.dto.ProductDto;
import ru.sstu.cashier.models.entity.Product;
import ru.sstu.cashier.models.response.ProductResponse;
import ru.sstu.cashier.services.CategoryService;
import ru.sstu.cashier.services.ProductService;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    private final ProductService productService;
    private final MapStructMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAll() {
        var response = productService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable long id) {
        var response = productService.getById(id);
        return ResponseEntity.ok(mapper.productToProductResponse(response));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getByCategory(@RequestParam String category) {
        var response = productService.findByCategory(category);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public void create(@RequestBody ProductDto productDto) {
        productService.create(productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }

    @PutMapping
    public void update(@RequestBody Product product) {
        productService.update(product);
    }

    @PostMapping("/get-products")
    public ResponseEntity<List<ProductResponse>> getProductsByIds(@RequestBody List<Long> productIds) {
        var products = productService.getAllByIds(productIds);
        var response =  mapper.productsToProductResponse(products);
        return ResponseEntity.ok(response);
    }
}
