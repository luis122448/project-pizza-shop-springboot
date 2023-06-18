package luis122448.projectpizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import luis122448.projectpizza.persistence.entity.pk.UserRolePk;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(UserRolePk.class)
@Table(name = "tbl_user_role")
public class UserRoleEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "granted_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;
}
