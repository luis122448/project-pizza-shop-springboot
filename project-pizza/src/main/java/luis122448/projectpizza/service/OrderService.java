package luis122448.projectpizza.service;

import jakarta.persistence.criteria.Order;
import luis122448.projectpizza.persistence.entity.OrderEntity;
import luis122448.projectpizza.persistence.projection.OrderSummary;
import luis122448.projectpizza.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final String DELIVERY = "B";
    private static final String CARRYOUT = "C";
    private static final String ON_SIZE = "S";
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> tmp  = this.orderRepository.findAll();
        // A Peticion del Usuario, mostrara el Detalle de los Clientes
        tmp.forEach(data -> System.out.println(data.getCustomer().getName()));
        return tmp;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY,CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer){
        return this.orderRepository.findCustomerOrders(idCustomer);
    }

    public OrderSummary getOrderSummary(int orderId){
        return this.orderRepository.findSummary(orderId);
    }

}
