package ru.sstu.cashier.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sstu.cashier.exceptions.CashierException;
import ru.sstu.cashier.mapper.MapStructMapper;
import ru.sstu.cashier.models.dto.CategoryDto;
import ru.sstu.cashier.models.entity.Category;
import ru.sstu.cashier.models.response.CategoryResponse;
import ru.sstu.cashier.models.response.ProductResponse;
import ru.sstu.cashier.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MapStructMapper mapper;

    public void create(CategoryDto categoryDto) {
        var category = mapper.categoryDtoToCategory(categoryDto);
        categoryRepository.save(category);
    }

    public Category getByName(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseThrow(
                () -> new CashierException("category not found")
        );
    }

    public List<ProductResponse> getProductByCategory(String categoryName) {
        var category =  categoryRepository.findByName(categoryName).orElseThrow(
                () -> new CashierException("category not found")
        );
        return mapper.productsToProductResponse(category.getProducts());
    }

    public List<CategoryResponse> getAll() {
        var categories = categoryRepository.findAll();
        return mapper.categoriesToCategoryResponse((List<Category>) categories);
    }
}