package luis122448.projectpizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.projectpizza.persistence.entity.pk.OrderItemPk;

@Getter
@Setter
@NoArgsConstructor
@IdClass(OrderItemPk.class)
@Entity
@Table(name = "TBL_ORDER_ITEM")
public class OrderItemEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza")
    private Integer idPizza;

    @Column(columnDefinition = "decimal(2,1)")
    private Double quantity;

    @Column(columnDefinition = "decimal(5,2)")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
    private PizzaEntity pizza;
}
