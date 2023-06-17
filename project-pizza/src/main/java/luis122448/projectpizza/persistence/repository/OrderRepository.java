package luis122448.projectpizza.persistence.repository;

import jakarta.persistence.PreRemove;
import luis122448.projectpizza.persistence.entity.OrderEntity;
import luis122448.projectpizza.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM tbl_pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

    @Query(value = " SELECT po.id_order          AS idOrder," +
            "         cu.name                    AS customerName," +
            "         po.date                    AS orderDate," +
            "         po.total                   AS orderTotal," +
            "         GROUP_CONCAT(pi.name)     AS pizzaNames" +
            "    FROM tbl_pizza_order po" +
            "         INNER JOIN tbl_customer cu ON po.id_customer = cu.id_customer" +
            "         INNER JOIN tbl_order_item oi ON po.id_order = oi.id_order" +
            "         INNER JOIN tbl_pizza pi ON oi.id_pizza = pi.id_pizza" +
            "   WHERE po.id_order = :orderId" +
            "   GROUP BY po.id_order," +
            "         cu.name," +
            "         po.date," +
            "         po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);

    @Procedure(value = "prc_take_random_pizza_order", outputParameterName = "order_token")
    boolean saveRandomOrder(@Param("id_customer") String idCustomer,  @Param("method") String method);
}
