package luis122448.projectpizza.web.controller;

import lombok.extern.slf4j.Slf4j;
import luis122448.projectpizza.persistence.entity.PizzaEntity;
import luis122448.projectpizza.service.PizzaService;
import luis122448.projectpizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Slf4j
@RestController
@RequestMapping("api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "8") int elements){
        try {
            return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/available")
//    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> getAvailable(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "8") int elements,
                                          @RequestParam(defaultValue = "price") String sortBy,
                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        try {
            return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/availablev2")
    public ResponseEntity<?> getAvailablePage(Pageable p){
        try {
            return ResponseEntity.ok(this.pizzaService.getAvailablePage(p));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name){
        try {
            return ResponseEntity.ok(this.pizzaService.getByName(name));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<?> getByWith(@PathVariable String ingredient){
        try {
            return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<?> getByWithOut(@PathVariable String ingredient){
        try {
            return ResponseEntity.ok(this.pizzaService.getWithOut(ingredient));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<?> getById(@PathVariable int idPizza){
        try {
            PizzaEntity tmp = this.pizzaService.getById(idPizza);
            if (isNull(tmp)){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(tmp);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<?> getCheapest(@PathVariable("price") double price){
        try {
            return ResponseEntity.ok(this.pizzaService.getCheapest(price));
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/price")
    public ResponseEntity<?> updatePrice(@RequestBody UpdatePizzaPriceDto t){
        try {
            if(this.pizzaService.exists(t.getPizzaId())){
                this.pizzaService.updatePrice(t);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Pizza no Existe!");
        } catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PizzaEntity t){
        try {
            if (t.getIdPizza() == null || !this.pizzaService.exists(t.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(t));
            }
            // HTTP CODE: 400
            // return ResponseEntity.badRequest().build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La Pizza ya Existe!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PizzaEntity t){
        try {
            if (t.getIdPizza() != null || this.pizzaService.exists(t.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(t));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Pizza no Existe!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<?> delete(@PathVariable int idPizza){
        try {
            if (this.pizzaService.exists(idPizza)) {
                this.pizzaService.delete(idPizza);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Pizza no Existe!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
