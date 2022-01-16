package ru.sstu.cashier.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.sstu.cashier.models.dto.CashierRegisterDto;
import ru.sstu.cashier.models.dto.CategoryDto;
import ru.sstu.cashier.models.dto.ProductDto;
import ru.sstu.cashier.models.entity.Category;
import ru.sstu.cashier.models.entity.Product;
import ru.sstu.cashier.models.entity.Receipt;
import ru.sstu.cashier.models.response.CashierResponse;
import ru.sstu.cashier.models.entity.Cashier;
import ru.sstu.cashier.models.response.CategoryResponse;
import ru.sstu.cashier.models.response.ProductResponse;
import ru.sstu.cashier.models.response.ReceiptResponse;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructMapper {
    Cashier cashierRegisterDtoToCashier(CashierRegisterDto cashierRegisterDto);

    CashierResponse cashierToCashierResponse(Cashier cashier);

    List<ProductResponse> productsToProductResponse(List<Product> products);

    ProductResponse productToProductResponse(Product product);

    Product productDtoToProduct(ProductDto productDto);

    Category categoryDtoToCategory(CategoryDto categoryDto);

    List<CategoryResponse> categoriesToCategoryResponse(List<Category> categories);

    List<ReceiptResponse> receiptsToReceiptResponse(List<Receipt> receipts);

    ReceiptResponse receiptToReceiptResponse(Receipt receipts);
}
