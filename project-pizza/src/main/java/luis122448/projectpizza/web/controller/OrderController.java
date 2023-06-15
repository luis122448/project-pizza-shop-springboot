package luis122448.projectpizza.web.controller;

import lombok.extern.slf4j.Slf4j;
import luis122448.projectpizza.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(this.orderService.getAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTodayOrders(){
        try {
            return ResponseEntity.ok(this.orderService.getTodayOrders());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<?> getByCustomer(@PathVariable("idCustomer") String idCustomer){
        try {
            return ResponseEntity.ok(this.orderService.getCustomerOrders(idCustomer));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/outside")
    public ResponseEntity<?> getOutsideOrders(){
        try {
            return ResponseEntity.ok(this.orderService.getOutsideOrders());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<?> getOrderSummary(@PathVariable("orderId") int orderId){
        try {
            return ResponseEntity.ok(this.orderService.getOrderSummary(orderId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
