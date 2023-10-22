package ba.edu.ibu.eventport.core.repository;

import java.util.Optional;

import ba.edu.ibu.eventport.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByEmailOrUsername(String email, String username);
}
