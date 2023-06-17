package luis122448.projectpizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.projectpizza.persistence.entity.audit.AuditPizzaListener;
import luis122448.projectpizza.persistence.entity.audit.AuditableEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

// @Data ( No use recommended ) ( HashCode, Equals )

@Getter
@Setter
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@Entity
@Table(name = "TBL_PIZZA")
public class PizzaEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false , length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "tinyint")
    private Boolean vegetarian;

    @Column(columnDefinition = "tinyint")
    private Boolean vegan;

    @Column(columnDefinition = "tinyint", nullable = false)
    private Boolean available;

    @Override
    public String toString() {
        return "PizzaEntity{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", vegetarian=" + vegetarian +
                ", vegan=" + vegan +
                ", available=" + available +
                '}';
    }
}
