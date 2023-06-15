package luis122448.projectpizza.service;

import luis122448.projectpizza.persistence.entity.PizzaEntity;
import luis122448.projectpizza.persistence.repository.PizzaPagSortRepository;
import luis122448.projectpizza.persistence.repository.PizzaRepository;
import luis122448.projectpizza.service.dto.UpdatePizzaPriceDto;
import luis122448.projectpizza.service.exception.EmailApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

//    private final JdbcTemplate jdbcTemplate;
//
//    public PizzaService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<PizzaEntity> getAll(){
//        return this.jdbcTemplate.query("SELECT * FROM TBL_PIZZA WHERE available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class));
//    }
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements){
//        return this.pizzaRepository.findAll();
        Pageable p = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(p);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        Sort s = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable p = PageRequest.of(page, elements, s);
//        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaPagSortRepository.findByAvailableTrue(p);
    }

    public Page<PizzaEntity> getAvailablePage(Pageable p){
        return this.pizzaPagSortRepository.findByAvailableTrue(p);
    }

    public List<PizzaEntity> getWith(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithOut(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public PizzaEntity getById(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity getByName(String name) {
//        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElse(null);
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La Pizza not found!"));
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    @Transactional(noRollbackFor = EmailApiException.class) // No Apply for exception
    public void updatePrice(UpdatePizzaPriceDto t){
        this.pizzaRepository.updatePrice(t);
//        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException();
    }
}
