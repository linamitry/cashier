package ru.sstu.cashier.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sstu.cashier.models.dto.CategoryDto;
import ru.sstu.cashier.models.dto.ProductDto;
import ru.sstu.cashier.models.response.CategoryResponse;
import ru.sstu.cashier.models.response.ProductResponse;
import ru.sstu.cashier.services.CategoryService;
import ru.sstu.cashier.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public void create(@RequestBody CategoryDto categoryDto) {
        categoryService.create(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        var response = categoryService.getAll();
        return ResponseEntity.ok(response);
    }

}
