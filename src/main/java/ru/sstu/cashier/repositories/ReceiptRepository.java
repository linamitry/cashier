package ru.sstu.cashier.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sstu.cashier.models.entity.Receipt;
import ru.sstu.cashier.models.response.ReceiptUpdateResponse;

import java.util.List;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
    List<Receipt> findReceiptByCashierId(long id);

//    @Query(value = "select new ru.sstu.cashier.models.response.ReceiptUpdateResponse(rec.amount, pr.product_id, p.name, p.price, pr.receipt_id, count(pr.receipt_id)) from receipts rec" +
//            "join product_receipt pr on rec.id = pr.receipt_id" +
//            "join products p on p.id = pr.product_id" +
//            "where rec.cashier_id = :cashierId" +
//            "group by pr.product_id, rec.amount, pr.receipt_id, p.name, p.price")
//    List<ReceiptUpdateResponse> findReceiptsByCashier(@Param("cashierId") long cashierId);

//    @Query(value = "select rec.* from receipts rec " +
//            "join product_receipt pr on rec.id = pr.receipt_id " +
//            "join products p on p.id = pr.product_id " +
//            "where rec.cashier_id = :cashierId " +
//            "group by pr.product_id, rec.amount, pr.receipt_id, p.name, p.price", nativeQuery = true)
//    List<Receipt> findReceiptsByCashier(@Param("cashierId") long cashierId);

//    select rec.amount, pr.product_id, p.name, p.price, pr.receipt_id, count(pr.receipt_id) from receipts rec
//    join product_receipt pr on rec.id = pr.receipt_id
//    join products p on p.id = pr.product_id
//    where rec.cashier_id = 1
//    group by pr.product_id, rec.amount, pr.receipt_id, p.name, p.price

//    @Query(value = "select new ru.technodiasoft.common.dto.ContainerResponse(c.id, c.time, p.name, p.value) " +
//            "from Container c " +
//            "join Parameter p on c.id=p.container.id " +
//            "where c.time between :before and :after")
//    List<ContainerResponse> findParameters(@Param("before") LocalDateTime before,
//                                           @Param("after") LocalDateTime after,
//                                           Pageable pageable);

}
