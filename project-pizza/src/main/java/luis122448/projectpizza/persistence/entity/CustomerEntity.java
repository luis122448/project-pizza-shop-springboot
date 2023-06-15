package luis122448.projectpizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBl_CUSTOMER")
public class CustomerEntity {
    @Id
    @Column(name = "id_customer",nullable = false, length = 20)
    private String idCustomer;

    @Column(length = 60)
    private String name;

    @Column(length = 100)
    private String address;

    @Column(length = 50)
    private String email;

    @Column(name = "phone_number",length = 20)
    private String phoneNumber;
}
