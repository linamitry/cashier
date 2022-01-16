package ru.sstu.cashier.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sstu.cashier.exceptions.CashierException;
import ru.sstu.cashier.mapper.MapStructMapper;
import ru.sstu.cashier.models.dto.ProductDto;
import ru.sstu.cashier.models.entity.Product;
import ru.sstu.cashier.models.response.ProductResponse;
import ru.sstu.cashier.repositories.ProductRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final MapStructMapper mapper;

    @Transactional
    public void create(ProductDto productDto) {
        var category = categoryService.getByName(productDto.getCategory().getName());
        if (category != null) {
            Product product = mapper.productDtoToProduct(productDto);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    public List<ProductResponse> getAll() {
        var products = productRepository.findAll();
        return mapper.productsToProductResponse((List<Product>) products);

    }

    public Product getById(long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new CashierException("product not found")
        );
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(Product productUpdate) {
        var product = productRepository.findById(productUpdate.getId()).orElseThrow(
                () -> new CashierException("product not found")
        );
        product.setName(productUpdate.getName());
        product.setDescription(productUpdate.getDescription());
        product.setPrice(productUpdate.getPrice());
        var category = categoryService.getByName(productUpdate.getCategory().getName());
        if (category != null) {
            product.setCategory(category);
        }
        productRepository.save(product);
    }

    public List<Product> getAllByIds(List<Long> ids) {
        var productIterable = productRepository.findAllById(ids);
        return (List<Product>) productIterable;
    }

    public List<ProductResponse> findByCategory(String category) {
        var products = productRepository.findByCategoryName(category);
        return mapper.productsToProductResponse(products);
    }
}