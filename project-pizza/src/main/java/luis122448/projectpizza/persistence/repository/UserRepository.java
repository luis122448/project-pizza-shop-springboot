package luis122448.projectpizza.persistence.repository;

import luis122448.projectpizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
