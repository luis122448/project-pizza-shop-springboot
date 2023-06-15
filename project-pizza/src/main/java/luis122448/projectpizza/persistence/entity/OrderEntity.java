package luis122448.projectpizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_PIZZA_ORDER")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer", nullable = false, length = 20)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private Double total;

    @Column(nullable = false, length = 1)
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    @OneToOne(fetch = FetchType.LAZY) // A Peticion del Usuario
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER) // Siempre
    @OrderBy("price DESC")
    private List<OrderItemEntity> items;

}
