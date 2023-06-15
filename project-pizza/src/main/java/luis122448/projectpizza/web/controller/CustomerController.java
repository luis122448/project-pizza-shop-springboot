package luis122448.projectpizza.web.controller;

import lombok.extern.slf4j.Slf4j;
import luis122448.projectpizza.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getByPhone(@PathVariable("phone") String phone){
        try {
            return ResponseEntity.ok(this.customerService.findByPhone(phone));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
