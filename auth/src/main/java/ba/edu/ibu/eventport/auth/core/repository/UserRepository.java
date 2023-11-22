package ba.edu.ibu.eventport.auth.core.repository;

import java.util.Optional;

import ba.edu.ibu.eventport.auth.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsernameOrEmail(String username, String email);

  Optional<User> findByEmail(String email);
}
